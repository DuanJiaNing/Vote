package com.duan.vote.common;


import java.io.Serializable;
import java.util.List;

/**
 * Created on 2019/11/16.
 *
 * @author DuanJiaNing
 */
public class PageModel<D> implements Serializable {

    // TODO ser..uid
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<D> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<D> getList() {
        return list;
    }

    public void setList(List<D> list) {
        this.list = list;
    }
}
