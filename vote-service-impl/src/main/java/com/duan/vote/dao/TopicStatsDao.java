package com.duan.vote.dao;

import com.duan.vote.dto.TopicSummaryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
@Repository
public interface TopicStatsDao {

    List<TopicSummaryDTO> simpleStats(@Param("title") String title,@Param("userId") Integer userId);

    List<TopicSummaryDTO> listInterestStats(Integer userId);

    TopicSummaryDTO getStats(Integer topicId);
}
