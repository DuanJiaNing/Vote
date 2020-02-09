package com.duan.vote.service.impl;

import com.duan.service.exceptions.InternalException;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.TopicDao;
import com.duan.vote.dto.TopicCriteriaDTO;
import com.duan.vote.dto.TopicDTO;
import com.duan.vote.entity.Topic;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.TopicService;
import com.duan.vote.utils.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public TopicDTO get(int id) {
        Topic topic = topicDao.findById(id);
        return DataConverter.map(topic, TopicDTO.class);
    }

    @Override
    public TopicDTO add(String title, String notes, Integer userId) throws ServiceException {
        if (title == null || StringUtils.isBlank(title)) {
            throw new ServiceException("Fail to add topic: topic title can not be empty");
        }

        if (topicDao.findByTitle(title) != null) {
            throw new ServiceException("Fail to add topic: topic with same title exist");
        }

        Topic topic = new Topic();
        if (StringUtils.isNotBlank(notes)) {
            topic.setNotes(notes);
        }
        topic.setTitle(title);
        topic.setUserId(userId);
        if (topicDao.insert(topic) != 1) {
            throw new ServiceException("Fail to add topic", new InternalException("DB"));
        }

        return DataConverter.map(topic, TopicDTO.class);
    }

    @Override
    public PageInfo<TopicDTO> simpleList(TopicCriteriaDTO criteria) {
        criteria = Utils.checkPageCondition(criteria);
        Topic st = new Topic();
        st.setUserId(criteria.getUserId());
        st.setTitle(criteria.getKeyword());
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<Topic> pageList = topicDao.find(st);
        return DataConverter.page(pageList, TopicDTO.class);
    }

}
