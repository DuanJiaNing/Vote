package com.duan.vote.service.impl;

import com.duan.service.TopicService;
import com.duan.service.dto.TopicCriteriaDTO;
import com.duan.service.dto.TopicDTO;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.TopicStatsDao;
import com.duan.vote.dao.UserInterestTopicDao;
import com.duan.vote.dto.InterestTopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.duan.vote.entity.TopicStats;
import com.duan.vote.entity.UserInterestTopic;
import com.duan.vote.service.TopicStatsService;
import com.duan.vote.utils.Utils;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created on 2020/1/18.
 *
 * @author DuanJiaNing
 */
@Service
public class TopicStatsServiceImpl implements TopicStatsService {

    @Reference
    private TopicService topicService;

    @Autowired
    private TopicStatsDao topicStatsDao;

    @Autowired
    private UserInterestTopicDao userInterestTopicDao;

    @Override
    public PageInfo<TopicSummaryDTO> listSummary(TopicStatsCriteriaDTO criteria) {
        PageInfo<TopicDTO> pageInfo = topicService.simpleList(DataConverter.map(criteria, TopicCriteriaDTO.class));
        if (Utils.emptyPage(pageInfo)) {
            return Utils.emptyPage();
        }

        List<TopicSummaryDTO> summaryDTOS = summaryTopicStats(pageInfo.getList());
        return Utils.newPageInfo(pageInfo, summaryDTOS);
    }

    private List<TopicSummaryDTO> summaryTopicStats(List<TopicDTO> topicList) {
        List<Integer> ids = topicList.stream().map(TopicDTO::getId).collect(Collectors.toList());
        List<TopicStats> topicStats = topicStatsDao.findTopicStatsByIds(ids);
        List<TopicSummaryDTO> res = new ArrayList<>(topicList.size());
        Map<Integer, TopicStats> idMap = topicStats.stream().collect(Collectors.toMap(TopicStats::getTopicId, dto -> dto));
        topicList.forEach(topic -> {
            TopicSummaryDTO tsDTO = new TopicSummaryDTO();
            tsDTO.setTopicId(topic.getId());
            TopicStats ts = idMap.get(topic.getId());
            if (ts != null) {
                tsDTO.setAgree(Optional.ofNullable(ts.getAgree()).orElse(0));
                tsDTO.setDisagree(Optional.ofNullable(ts.getDisagree()).map(da -> -da).orElse(0));
                tsDTO.setInterestUserCount(Optional.ofNullable(ts.getInterestUserCount()).orElse(0));
            } else {
                tsDTO.setAgree(0);
                tsDTO.setDisagree(0);
                tsDTO.setInterestUserCount(0);
            }
            tsDTO.setInsertTime(topic.getInsertTime());
            tsDTO.setTitle(topic.getTitle());
            tsDTO.setStatus(topic.getStatus());

            res.add(tsDTO);
        });

        return res;
    }

    @Override
    public PageInfo<TopicSummaryDTO> listInterest(InterestTopicStatsCriteriaDTO criteria) {
        List<UserInterestTopic> uit = userInterestTopicDao.findByUserId(criteria.getOwnerId());
        if (CollectionUtils.isEmpty(uit)) {
            return Utils.emptyPage();
        }

        List<Integer> topicIds = uit.stream().map(UserInterestTopic::getTopicId).map(Integer::valueOf)
                .collect(Collectors.toList());
        PageInfo<TopicDTO> pageInfo = topicService.simpleList(DataConverter.map(criteria, TopicCriteriaDTO.class), topicIds);
        if (Utils.emptyPage(pageInfo)) {
            return Utils.emptyPage();
        }

        List<TopicSummaryDTO> summaryDTOS = summaryTopicStats(pageInfo.getList());
        return Utils.newPageInfo(pageInfo, summaryDTOS);
    }

}
