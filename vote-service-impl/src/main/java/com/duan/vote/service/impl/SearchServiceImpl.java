package com.duan.vote.service.impl;

import com.duan.service.exceptions.InternalException;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.UserSearchHistoryDao;
import com.duan.vote.dto.SearchHistoryCriteriaDTO;
import com.duan.vote.dto.SearchHistoryDTO;
import com.duan.vote.entity.UserSearchHistory;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.SearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserSearchHistoryDao userSearchHistoryDao;

    @Override
    public PageInfo<SearchHistoryDTO> list(SearchHistoryCriteriaDTO criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        UserSearchHistory ush = new UserSearchHistory();
        ush.setUserId(criteria.getUserId());
        List<UserSearchHistory> pageList = userSearchHistoryDao.find(ush);
        return DataConverter.page(pageList, SearchHistoryDTO.class);
    }

    @Override
    public SearchHistoryDTO add(String content, Integer userId) throws ServiceException {
        UserSearchHistory history = userSearchHistoryDao.findByContent(content);
        if (history == null) {
            history = new UserSearchHistory();
            history.setCount(1);
            history.setUserId(userId);
            history.setContent(content);
            if (userSearchHistoryDao.insert(history) != 1) {
                throw new ServiceException("Fail to insert user search history", new InternalException("DB"));
            }
            return newSearchHistoryDTO(content, 1);
        }

        int newCount = history.getCount() + 1;
        history.setCount(newCount);
        if (userSearchHistoryDao.update(history) != 1) {
            throw new ServiceException("Fail to increment user search history count", new InternalException("DB"));
        }

        return newSearchHistoryDTO(content, newCount);
    }

    private SearchHistoryDTO newSearchHistoryDTO(String content, int count) {
        SearchHistoryDTO dto = new SearchHistoryDTO();
        dto.setContent(content);
        dto.setCount(count);
        return dto;
    }
}
