package com.duan.vote.service;


import com.duan.service.Service;
import com.duan.vote.dto.CommentDTO;
import com.duan.vote.exceptions.ServiceException;

/**
 * Created on 2020/02/09.
 *
 * @author DuanJiaNing
 */
public interface CommentService extends Service<CommentDTO> {

    CommentDTO add(String content, Integer userId, Integer topicId) throws ServiceException;

}
