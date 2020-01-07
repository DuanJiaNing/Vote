package com.duan.vote.common;


import java.io.Serializable;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
public class ResultModel<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -2615507408225254664L;

    private String msg;
    private Integer code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
