package com.duan.vote.dao;

import com.duan.service.dao.BaseDao;
import com.duan.vote.entity.UserInterestTopic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Repository
public interface UserInterestTopicDao extends BaseDao<UserInterestTopic> {

    List<UserInterestTopic> findByUserId(int userId);

}
