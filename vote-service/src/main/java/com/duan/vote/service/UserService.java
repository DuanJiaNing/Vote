package com.duan.vote.service;

import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.UserException;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
public interface UserService {

    UserDTO getUserUid(String uidKey) throws UserException;

    UserDTO getUserByUid(String uid);
}
