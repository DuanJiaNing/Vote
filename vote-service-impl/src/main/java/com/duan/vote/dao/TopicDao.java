package com.duan.vote.dao;

import com.duan.service.dao.BaseDao;
import com.duan.vote.dto.TopicSummaryDTO;
import com.duan.vote.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@Repository
public interface TopicDao extends BaseDao<Topic> {

    List<TopicSummaryDTO> summary(@Param("title") String title, @Param("userId") Integer userId);

    List<TopicSummaryDTO> interestSummary(Integer userId);

    TopicSummaryDTO getSummary(Integer topicId);

    List<Topic> findAll();

    Topic findByTitle(String title);

    List<Topic> findWithIds(@Param("topic") Topic topic, @Param("ids") List<Integer> ids);
}
