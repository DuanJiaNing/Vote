package com.duan.vote.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/02/09.
 *
 * @author DuanJiaNing
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -2297699882560001150L;
    private Integer id;
    private Integer userId;
    private Integer topicId;
    private String content;
    private Integer vote;
    private Timestamp insertTime;
}
