package com.duan.vote.dto;

import com.duan.service.dto.PageCondition;
import lombok.Data;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
@Data
public class TopicStatsCriteriaDTO extends PageCondition {

    private static final long serialVersionUID = 3003732595421962250L;
    private String userId;
    private String title;
    private Integer id;
    private String appId;
    private Integer status;

}
