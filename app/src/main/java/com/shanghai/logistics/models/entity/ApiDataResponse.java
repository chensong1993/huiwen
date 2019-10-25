package com.shanghai.logistics.models.entity;

import java.io.Serializable;

/**
 * @author chensong
 * @date 2019/2/18 11:49
 */
public class ApiDataResponse<T> implements Serializable {

    private T data;
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
