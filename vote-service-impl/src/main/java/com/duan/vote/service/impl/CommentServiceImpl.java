package com.duan.vote.service.impl;

import com.duan.service.exceptions.InternalException;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.CommentDao;
import com.duan.vote.dao.CommentVoteDao;
import com.duan.vote.dto.CommentCriteriaDTO;
import com.duan.vote.dto.CommentDTO;
import com.duan.vote.dto.CommentSummaryDTO;
import com.duan.vote.entity.Comment;
import com.duan.vote.entity.CommentVote;
import com.duan.vote.enums.Vote;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.CommentService;
import com.duan.vote.utils.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2020/02/10.
 *
 * @author DuanJiaNing
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentVoteDao commentVoteDao;

    @Override
    public CommentDTO add(String content, Integer topicId, Integer userId, Vote vote) throws ServiceException {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTopicId(topicId);
        comment.setUserId(userId);
        comment.setVote(vote.getCode());
        if (commentDao.insert(comment) != 1) {
            throw new ServiceException("Fail to add comment", new InternalException("DB"));
        }

        return DataConverter.map(comment, CommentDTO.class);
    }

    @Override
    public PageInfo<CommentSummaryDTO> listSummary(CommentCriteriaDTO criteria) {
        criteria = Utils.checkPageCondition(criteria);
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<CommentSummaryDTO> pageList = commentDao.listSummary(criteria.getMyUserId(), criteria.getTopicId());
        return DataConverter.page(pageList, CommentSummaryDTO.class);
    }

    @Override
    public CommentSummaryDTO vote(Integer userId, Integer commentId, Vote vote) throws ServiceException {
        CommentVote cv = commentVoteDao.findUserVote(userId, commentId);
        if (cv != null) {
            throw new ServiceException("already voted");
        }

        CommentVote ncv = new CommentVote();
        ncv.setVote(vote.getCode());
        ncv.setUserId(userId);
        ncv.setCommentId(commentId);
        if (commentVoteDao.insert(ncv) != 1) {
            throw new ServiceException("Fail to vote", new InternalException("DB"));
        }

        return commentDao.getSummary(userId, commentId);
    }

    @Override
    public CommentDTO get(int id) {
        return DataConverter.map(commentDao.findById(id), CommentDTO.class);
    }
}
