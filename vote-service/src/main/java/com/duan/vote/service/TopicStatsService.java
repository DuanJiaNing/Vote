package com.duan.vote.service;

import com.duan.vote.dto.TopicCriteriaDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.github.pagehelper.PageInfo;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
public interface TopicStatsService {

    PageInfo<TopicSummaryDTO> listSummary(TopicCriteriaDTO criteria);

    PageInfo<TopicSummaryDTO> listInterest(TopicCriteriaDTO criteria);

    TopicSummaryDTO getSummary(Integer topicId);
}
