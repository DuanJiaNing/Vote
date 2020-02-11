package com.duan.vote.dao;

import com.duan.service.dao.BaseDao;
import com.duan.vote.entity.CommentVote;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020/02/11.
 *
 * @author DuanJiaNing
 */
@Repository
public interface CommentVoteDao extends BaseDao<CommentVote> {

    CommentVote findUserVote(@Param("userId") Integer userId, @Param("commentId") Integer commentId);
}
