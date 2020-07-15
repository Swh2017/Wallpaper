package com.appfame.topwallpaper.netutils.listener;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface OnRequestListener {

    void OnSuccess(Response<ResponseBody> response);

    void OnError(String msg);
}
