package com.duan.vote.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Data
public class SearchHistoryDTO implements Serializable {

    private static final long serialVersionUID = 3138611141075654793L;
    private Integer count;
    private String content;

}
