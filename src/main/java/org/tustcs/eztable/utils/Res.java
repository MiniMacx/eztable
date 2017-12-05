package org.tustcs.eztable.utils;

import org.json.JSONObject;

/**
 * Created by polykickshaw on 17-6-6.
 */
public class Res<T> {
    private String msg;
    private int code = 0;
    private T data;

    public static String jsonToString(JSONObject jsonObject){
        return jsonObject.toString();
    }

    public Res() {
    }


    public Res(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public Res setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Res setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public Res setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Res{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
