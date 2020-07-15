package com.appfame.topwallpaper.netutils;

import android.content.Context;
import android.net.ParseException;

import com.appfame.topwallpaper.netutils.listener.OnRequestListener;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class NetHelper {

    private static NetHelper netHelper;

    public static NetHelper getInstance() {
        if (netHelper == null) {
            netHelper = new NetHelper();
        }
        return netHelper;
    }

    public void showLoading(Context context) {

    }

    public void dismssDialog() {

    }

    public void RequestData(Call<ResponseBody> responsebody, OnRequestListener onRequestListener) {
        this.RequestData(null, responsebody, onRequestListener);
    }

    public void RequestData(final Context context, Call<ResponseBody> responsebody, final OnRequestListener onRequestListener) {
        if (context != null) {
            showLoading(context);
        }
        responsebody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (context != null) {
                    dismssDialog();
                }
                if (onRequestListener == null) {
                    return;
                }
                if (response.body() == null) {
                    onRequestListener.OnError("request error");
                }
                try {
                    onRequestListener.OnSuccess(response);
                } catch (Exception e) {
                    onRequestListener.OnError(e.getMessage());
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (context != null) {
                    dismssDialog();
                }
                if (onRequestListener != null) {
                    RequestError(t, onRequestListener);
                }
            }
        });
    }

    public void RequestError(Throwable e, OnRequestListener onRequestListener) {
        if (e instanceof HttpException) {     //   HTTP错误
            onRequestListener.OnError("http error");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onRequestListener.OnError("connect error");
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onRequestListener.OnError("time out");
        } else if (
                e instanceof JsonParseException ||
                e instanceof JSONException ||
                e instanceof ParseException) {   //  解析错误
            onRequestListener.OnError("json error");
        } else {
            onRequestListener.OnError("unknow error");
        }
    }
}
