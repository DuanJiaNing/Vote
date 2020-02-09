package com.duan.vote.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
@Data
public class TopicStats implements Serializable {

    private static final long serialVersionUID = 5079232498533095887L;
    private Integer topicId;
    private Integer voteCount;
    private Integer interestUserCount;

}
