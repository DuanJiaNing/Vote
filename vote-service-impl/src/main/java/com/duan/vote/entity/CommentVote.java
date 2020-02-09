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
public class CommentVote implements Serializable {

    private static final long serialVersionUID = 9193843657898950699L;
    private Integer id;
    private Integer userId;
    private Integer commentId;
    private Integer vote;
    private Timestamp insertTime;
}
