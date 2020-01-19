package com.duan.vote.service;

import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.ServiceException;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
public interface UserService {

    UserDTO getUserUid(String uidKey) throws ServiceException;

    UserDTO getUserByUid(String uid);

    UserDTO getUserById(int id);
}
