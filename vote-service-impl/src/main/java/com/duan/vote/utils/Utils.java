package com.duan.vote.utils;

import com.duan.service.dto.PageCondition;
import com.github.pagehelper.PageInfo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
public class Utils {

    public static final int default_page_size = 10;
    public static final int default_page_num = 0;

    public static <E extends PageCondition> E checkPageCondition(E pageCondition) {
        if (pageCondition == null) {
            return null;
        }

        if (pageCondition.getPageNum() < 0) {
            pageCondition.setPageNum(default_page_num);
        }

        if (pageCondition.getPageSize() <= 0) {
            pageCondition.setPageSize(default_page_size);
        }

        return pageCondition;
    }

    public static <E> PageInfo<E> newPageInfo(PageInfo pageInfo, List<E> list) {
        PageInfo<E> rpage = new PageInfo<>();
        rpage.setList(list);
        rpage.setPageNum(pageInfo.getPageNum());
        rpage.setPageSize(pageInfo.getPageSize());
        rpage.setTotal(pageInfo.getTotal());
        rpage.setPages(pageInfo.getPages());
        return rpage;
    }

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
