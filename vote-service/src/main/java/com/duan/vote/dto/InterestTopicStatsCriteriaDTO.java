package com.duan.vote.dto;

import lombok.Data;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Data
public class InterestTopicStatsCriteriaDTO extends TopicStatsCriteriaDTO {

    private static final long serialVersionUID = 4936315363150659270L;
    private Integer ownerId;

}
