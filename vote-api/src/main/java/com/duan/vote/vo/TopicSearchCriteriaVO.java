package com.duan.vote.vo;

import com.duan.service.dto.PageCondition;
import lombok.Data;

/**
 * Created on 2020/1/12.
 *
 * @author DuanJiaNing
 */
@Data
public class TopicSearchCriteriaVO extends PageCondition {

    private static final long serialVersionUID = -5671018425314783510L;
    private String keyWord;
}
