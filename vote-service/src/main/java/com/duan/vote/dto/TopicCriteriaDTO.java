package com.duan.vote.dto;

import com.duan.service.dto.PageCondition;
import lombok.Data;

/**
 * Created on 2020/1/12.
 *
 * @author DuanJiaNing
 */
@Data
public class TopicCriteriaDTO extends PageCondition {

    private static final long serialVersionUID = 3003732595421962250L;
    private Integer userId;
    private String Keyword;

}
