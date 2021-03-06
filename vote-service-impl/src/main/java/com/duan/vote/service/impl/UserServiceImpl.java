package com.duan.vote.service.impl;

import com.duan.service.exceptions.InternalException;
import com.duan.service.util.DataConverter;
import com.duan.vote.dao.UserDao;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.entity.User;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.Utils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO getUserUid(String uidKey) throws ServiceException {
        String uid = Utils.sha256(uidKey);
        UserDTO user = getUserByUid(uid);
        if (user == null) {
            User iu = new User();
            iu.setUid(uid);
            if (userDao.insert(iu) != 1) {
                throw new ServiceException("Fail to insert user", new InternalException("DB"));
            }
            user = getUserByUid(uid);
        }

        return user;
    }

    @Override
    public UserDTO getUserByUid(String uid) {
        User fe = new User();
        fe.setUid(uid);
        List<User> users = userDao.find(fe);
        if (users.size() == 0) {
            return null;
        }

        return DataConverter.map(users.get(0), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.findById(id);
        if (user == null) {
            return null;
        }

        return DataConverter.map(user, UserDTO.class);
    }
}
