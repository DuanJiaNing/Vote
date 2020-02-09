package com.duan.vote.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/12/09.
 *
 * @author DuanJiaNing
 */
@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 9113223988064196313L;
    private Integer id;
    private Timestamp insertTime;
    private String content;
    private Integer topicId;
    private Integer userId;
    private Integer vote;
}
