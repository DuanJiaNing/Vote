package com.duan.vote.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/02/10.
 *
 * @author DuanJiaNing
 */
@Data
public class CommentSummaryDTO implements Serializable {

    private static final long serialVersionUID = -158282790346397758L;
    private Integer commentId;
    private Integer userId;
    private Integer topicId;
    private String content;
    private Integer vote;
    private Timestamp insertTime;

    private int myCommentVote;
    private int agreeVoteCount;
    private int disagreeVoteCount;
}
