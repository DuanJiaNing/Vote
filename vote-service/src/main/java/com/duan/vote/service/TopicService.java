package com.duan.vote.service;


import com.duan.service.Service;
import com.duan.vote.dto.TopicCriteriaDTO;
import com.duan.vote.dto.TopicDTO;
import com.duan.vote.exceptions.ServiceException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
public interface TopicService extends Service<TopicDTO> {

    TopicDTO add(String topic, String notes, Integer userId) throws ServiceException;

    PageInfo<TopicDTO> simpleList(TopicCriteriaDTO criteria);

}
