package com.duan.vote.service.impl;

import com.duan.service.dto.PageCondition;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.TopicStatsDao;
import com.duan.vote.dto.TopicCriteriaDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.duan.vote.service.TopicStatsService;
import com.duan.vote.utils.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
@Service
public class TopicStatsServiceImpl implements TopicStatsService {

    @Autowired
    private TopicStatsDao topicStatsDao;

    @Override
    public PageInfo<TopicSummaryDTO> listSummary(TopicCriteriaDTO criteria) {
        criteria = Utils.checkPageCondition(criteria);
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<TopicSummaryDTO> pageList = topicStatsDao.simpleStats(criteria.getKeyword(), criteria.getUserId());
        return DataConverter.page(pageList, TopicSummaryDTO.class);
    }

    @Override
    public PageInfo<TopicSummaryDTO> listInterest(TopicCriteriaDTO criteria) {
        criteria = Utils.checkPageCondition(criteria);
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<TopicSummaryDTO> pageList = topicStatsDao.listInterestStats(criteria.getUserId());
        return DataConverter.page(pageList, TopicSummaryDTO.class);
    }

    @Override
    public TopicSummaryDTO getSummary(Integer topicId) {
        return topicStatsDao.getStats(topicId);
    }

}
