package com.appfame.topwallpaper.netutils.data;

public class ResultModel<T> {

    private int result_code;
    private String result_message;
    private T data;
    public int getCode() {
        return result_code;
    }

    public void setCode(int code) {
        this.result_code = code;
    }

    public String getDesc() {
        return result_message;
    }

    public void setDesc(String desc) {
        this.result_message = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk(){

//        if (data == null){
//            Logger.d("json Error ? data is null");
//            return false;
//        }
        return result_code == 1;
    }

}
