package com.duan.vote.dto;

import com.duan.service.dto.PageCondition;
import lombok.Data;

/**
 * Created on 2020/02/10.
 *
 * @author DuanJiaNing
 */
@Data
public class CommentCriteriaDTO extends PageCondition {

    private static final long serialVersionUID = -6011936876118251813L;
    private Integer myUserId;
    private Integer topicId;

}
