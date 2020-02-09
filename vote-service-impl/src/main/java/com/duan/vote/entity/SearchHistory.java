package com.duan.vote.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Data
public class SearchHistory implements Serializable {

    private static final long serialVersionUID = 2151476222532896807L;
    private Integer id;
    private Integer userId;
    private Integer count;
    private String content;
    private Timestamp insertTime;

}
