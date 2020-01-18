package com.duan.vote.dao;

import com.duan.vote.entity.TopicStats;
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

    List<TopicStats> findTopicStatsByIds(@Param("ids") List<Integer> ids);

}
