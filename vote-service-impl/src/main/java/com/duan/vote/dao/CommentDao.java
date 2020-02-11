package com.duan.vote.dao;

import com.duan.service.dao.BaseDao;
import com.duan.vote.dto.CommentSummaryDTO;
import com.duan.vote.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/02/10.
 *
 * @author DuanJiaNing
 */
@Repository
public interface CommentDao extends BaseDao<Comment> {

    List<CommentSummaryDTO> listSummary(@Param("myUserId") Integer myUserId, @Param("topicId") Integer topicId);

    CommentSummaryDTO getSummary(@Param("myUserId") Integer myUserId, @Param("commentId") Integer commentId);
}
