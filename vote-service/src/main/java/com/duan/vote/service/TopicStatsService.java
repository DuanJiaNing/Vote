package com.duan.vote.service;

import com.duan.vote.dto.InterestTopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.github.pagehelper.PageInfo;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
public interface TopicStatsService {

    PageInfo<TopicSummaryDTO> listSummary(TopicStatsCriteriaDTO criteria);

    PageInfo<TopicSummaryDTO> listInterest(InterestTopicStatsCriteriaDTO criteria);

    TopicSummaryDTO getSummary(Integer id);
}
