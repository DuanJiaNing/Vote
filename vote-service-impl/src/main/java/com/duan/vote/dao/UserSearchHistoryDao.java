package com.duan.vote.dao;

import com.duan.service.dao.BaseDao;
import com.duan.vote.entity.UserSearchHistory;
import org.springframework.stereotype.Repository;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Repository
public interface UserSearchHistoryDao extends BaseDao<UserSearchHistory> {

    UserSearchHistory findByContent(String content);
}
