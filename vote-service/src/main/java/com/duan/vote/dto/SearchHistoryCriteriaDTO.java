package com.duan.vote.dto;

import com.duan.service.dto.PageCondition;
import lombok.Data;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Data
public class SearchHistoryCriteriaDTO extends PageCondition {

    private static final long serialVersionUID = -9159051714980748810L;
    private Integer userId;

}
