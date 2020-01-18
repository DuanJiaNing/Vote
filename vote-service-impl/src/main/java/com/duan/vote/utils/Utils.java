package com.duan.vote.utils;

import com.github.pagehelper.PageInfo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
public class Utils {

    public static PageInfo emptyPage() {
        return new PageInfo();
    }

    public static boolean emptyPage(PageInfo page) {
        return page == null || page.getTotal() == 0 || page.getList() == null || page.getList().size() == 0;
    }

    public static String sha256(String msg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(msg.getBytes("UTF-8"));
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
