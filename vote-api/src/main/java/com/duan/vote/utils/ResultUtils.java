package com.duan.vote.utils;

import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.exceptions.CheckedException;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
public class ResultUtils {

    private static final int OK = 0;
    // user understandable
    private static final int CHECKED_ERROR = 1;
    // user can not understand
    private static final int INTERNAL_ERROR = 2;

    public static ResultModel processException(Throwable err) {
        return err instanceof CheckedException ? ResultUtils.checked((CheckedException) err)
                : ResultUtils.fail(err);
    }

    public static ResultModel checked(String msg) {
        ResultModel rm = new ResultModel();
        rm.setMsg(msg);
        rm.setCode(CHECKED_ERROR);
        return rm;
    }

    public static ResultModel checked(CheckedException err) {
        ResultModel rm = new ResultModel();
        rm.setMsg(err.getMessage());
        rm.setCode(CHECKED_ERROR);
        return rm;
    }

    public static ResultModel fail(String msg) {
        ResultModel rm = new ResultModel();
        rm.setMsg(msg);
        rm.setCode(INTERNAL_ERROR);
        return rm;
    }

    public static ResultModel fail(Throwable e) {
        e.printStackTrace();
        LoggerFactory.getLogger(ResultUtils.class).error("error when handle request", e);

        ResultModel rm = new ResultModel();
        rm.setMsg(e.getMessage());
        rm.setCode(INTERNAL_ERROR);
        return rm;
    }

    public static <T extends Serializable> ResultModel<T> success(T data) {
        ResultModel<T> rm = new ResultModel<T>();
        rm.setMsg("success");
        rm.setData(data);
        rm.setCode(OK);
        return rm;
    }

    public static <T extends Serializable> ResultModel<PageModel<T>> successPaged(PageInfo<T> page) {
        if (page == null) {
            return success(new PageModel<>());
        }

        PageModel<T> pm = new PageModel<>();
        pm.setList(page.getList());
        pm.setPageNum(page.getPageNum());
        pm.setPages(page.getPages());
        pm.setPageSize(page.getPageSize());
        pm.setTotal(page.getTotal());

        return success(pm);
    }

}
