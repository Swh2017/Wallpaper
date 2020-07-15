package com.appfame.topwallpaper.netutils;

import android.util.Log;

import com.appfame.topwallpaper.netutils.data.ResultModel;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetFactory {

    private static Retrofit retrofit;
    private static final int Defailt_Time = 3 * 60 * 1000;
    private static final String HostUrl = "http://api-cn-wp-transparent2.app-fame.com/";
    private static RequestApi requestApi ;


    public static RequestApi getRequestApi() {
        if (requestApi == null) {
            synchronized (NetFactory.class) {
                requestApi = provideRetrofit().create(RequestApi.class);
            }
        }
        return requestApi;
    }

    /**
     * 初始化必要对象和参数
     *
     * @return
     */
    private static Retrofit provideRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Defailt_Time, TimeUnit.SECONDS)
                .writeTimeout(Defailt_Time, TimeUnit.SECONDS)
                .readTimeout(Defailt_Time, TimeUnit.SECONDS)
//                .addInterceptor(getHttpLoggingInterceptor())//Application拦截器
//                .addNetworkInterceptor(getRequestHeader())//Network拦截器
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(HostUrl)//设置网络请求的Url地址
//                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .client(client)
                .build();
        return retrofit;
    }

    /**
     * 日志拦截器
     *
     * @return
     */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("OkHttpLog", "log = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    /**
     * 请求头拦截器
     *
     * @return
     */
    private static Interceptor getRequestHeader() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request originalRequest = chain.request();
                okhttp3.Request.Builder builder = originalRequest.newBuilder();
//                builder.addHeader("Content-Type", "application/json; charset=utf-8");
//                builder.addHeader("token", "d08986536b5e3678119aac9b892439a8");
                okhttp3.Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
                okhttp3.Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }

    //通用请求构建
    public static <T> ResultModel<T> convertBody(Response<ResponseBody> response, Type typeOfT) {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            responseBody = response.errorBody();
        }
        if (responseBody != null) {
            JSONObject jsonObject = null;
            try {
                String resultString = responseBody.string();
                jsonObject = new JSONObject(resultString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (jsonObject != null) {
                ResultModel<T> resultModel = new ResultModel<>();
                resultModel.setCode(jsonObject.optInt("result_code"));
                resultModel.setDesc(jsonObject.optString("result_message"));
                try {
                    JSONObject dataJsonObject = jsonObject.optJSONObject("data");
                    if (dataJsonObject != null && typeOfT != null) {
                        Gson gson = new Gson();
                        T data = gson.fromJson(dataJsonObject.toString(), typeOfT);
                        resultModel.setData(data);
                    }
                } catch (Exception e) {
                    Logger.e("Json Error: ", e.getMessage());
                    e.printStackTrace();
                }
                return resultModel;
            }
        }
        return null;
    }
}
