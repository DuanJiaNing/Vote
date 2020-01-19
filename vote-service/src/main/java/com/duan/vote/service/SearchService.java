package com.duan.vote.service;

import com.duan.vote.dto.SearchHistoryCriteriaDTO;
import com.duan.vote.dto.SearchHistoryDTO;
import com.duan.vote.exceptions.ServiceException;
import com.github.pagehelper.PageInfo;

/**
 * Created on 2020/1/19.
 *
 * @author DuanJiaNing
 */
public interface SearchService {

    PageInfo<SearchHistoryDTO> list(SearchHistoryCriteriaDTO criteria);

    SearchHistoryDTO add(String content, Integer userId) throws ServiceException;
}
