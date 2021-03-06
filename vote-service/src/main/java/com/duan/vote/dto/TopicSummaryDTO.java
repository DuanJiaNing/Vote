package com.duan.vote.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2020/1/17.
 *
 * @author DuanJiaNing
 */
@Data
public class TopicSummaryDTO implements Serializable {

    private static final long serialVersionUID = -4848404894808120377L;
    private Integer topicId;
    private Timestamp insertTime;
    private String title;
    private String notes;

    private int voteCount;
    private int interestUserCount;
}
