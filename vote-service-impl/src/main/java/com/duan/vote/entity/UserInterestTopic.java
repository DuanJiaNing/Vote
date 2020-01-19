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
public class UserInterestTopic implements Serializable {

    private static final long serialVersionUID = 1211656111779402143L;
    private Integer id;
    private Integer userId;
    private String topicId;

    private Timestamp insertTime;

}
