package com.shanghai.logistics.models.entity;

import java.io.Serializable;

/**
 * @author chensong
 * @date 2019/2/18 11:49
 */
public class ApiResponse<T> implements Serializable {

    private T data;
    private int msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
