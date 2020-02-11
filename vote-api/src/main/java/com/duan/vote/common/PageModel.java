package com.duan.vote.common;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2019/11/16.
 *
 * @author DuanJiaNing
 */
@Data
public class PageModel<D> implements Serializable {

    private static final long serialVersionUID = -8501352785957042449L;
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<D> list;
}
