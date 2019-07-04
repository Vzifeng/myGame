package com.example.game.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 14:12 2019/6/17
 * @Version ： $version$
 */
public class Pager {
    private Integer curPage;

    private Integer pageSize;

    private Integer totalRow;

    private Integer totalPage;

    private List list = new ArrayList();

    public Integer getCurPage(Integer curPage) {
        return this.curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
