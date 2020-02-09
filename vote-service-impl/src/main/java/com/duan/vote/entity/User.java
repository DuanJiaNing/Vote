package com.duan.vote.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/1/12.
 *
 * @author DuanJiaNing
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1211656111779402143L;
    private Integer id;
    private String uid;
    private Timestamp insertTime;

}
