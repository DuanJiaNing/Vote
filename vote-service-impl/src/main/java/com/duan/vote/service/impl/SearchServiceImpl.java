package com.duan.vote.service.impl;

import com.duan.service.exceptions.InternalException;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.SearchHistoryDao;
import com.duan.vote.dto.SearchHistoryCriteriaDTO;
import com.duan.vote.dto.SearchHistoryDTO;
import com.duan.vote.entity.SearchHistory;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.SearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchHistoryDao searchHistoryDao;

    @Override
    public PageInfo<SearchHistoryDTO> list(SearchHistoryCriteriaDTO criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        SearchHistory ush = new SearchHistory();
        ush.setUserId(criteria.getUserId());
        List<SearchHistory> pageList = searchHistoryDao.find(ush);
        return DataConverter.page(pageList, SearchHistoryDTO.class);
    }

    @Override
    public SearchHistoryDTO add(String content, Integer userId) throws ServiceException {
        SearchHistory fuh = new SearchHistory();
        fuh.setUserId(userId);
        fuh.setContent(content);
        List<SearchHistory> histories = searchHistoryDao.find(fuh);
        if (CollectionUtils.isEmpty(histories)) {
            SearchHistory history = new SearchHistory();
            history.setCount(1);
            history.setUserId(userId);
            history.setContent(content);
            if (searchHistoryDao.insert(history) != 1) {
                throw new ServiceException("Fail to insert user search history", new InternalException("DB"));
            }
            return newSearchHistoryDTO(content, 1);
        }

        SearchHistory history = histories.get(0);
        int newCount = history.getCount() + 1;
        history.setCount(newCount);
        if (searchHistoryDao.update(history) != 1) {
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
