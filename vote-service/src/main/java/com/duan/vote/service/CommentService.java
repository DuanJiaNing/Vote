package com.duan.vote.service;


import com.duan.service.Service;
import com.duan.service.dto.PageCondition;
import com.duan.vote.dto.CommentCriteriaDTO;
import com.duan.vote.dto.CommentDTO;
import com.duan.vote.dto.CommentSummaryDTO;
import com.duan.vote.enums.Vote;
import com.duan.vote.exceptions.ServiceException;
import com.github.pagehelper.PageInfo;

/**
 * Created on 2020/02/09.
 *
 * @author DuanJiaNing
 */
public interface CommentService extends Service<CommentDTO> {

    CommentDTO add(String content, Integer topicId, Integer userId, Vote vote) throws ServiceException;

    PageInfo<CommentSummaryDTO> listSummary(CommentCriteriaDTO criteria);

    CommentSummaryDTO vote(Integer userId, Integer commentId, Vote vote) throws ServiceException;
}
