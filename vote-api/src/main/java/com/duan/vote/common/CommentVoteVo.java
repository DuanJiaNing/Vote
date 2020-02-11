package com.duan.vote.common;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2020/02/11.
 *
 * @author DuanJiaNing
 */
@Data
public class CommentVoteVo implements Serializable {

    private static final long serialVersionUID = -3339855360959751965L;
    private Integer vote;
    private Integer commentId;
}
