package com.duan.vote.common;


import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@Data
public class ResultModel<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -2615507408225254664L;
    private String msg;
    private Integer code;
    private T data;

}
