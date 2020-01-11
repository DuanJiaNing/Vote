package com.duan.vote.service.impl;

import com.duan.vote.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
@Service
public class UserServiceImpl implements UserService {

    // TODO check and write into db
    @Override
    public String getUserUid(String uidKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(uidKey.getBytes("UTF-8"));
            byte[] byteBuffer = messageDigest.digest();
            StringBuilder strHexString = new StringBuilder();
            for (byte b : byteBuffer) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            return strHexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
