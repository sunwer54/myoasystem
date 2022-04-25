package com.util;

import com.oa.pojo.Employee;

import java.util.List;

/**
 * 这个类封装分页的一些数据信息
 */
public class Result {
    private List<Employee> emps;
    private long total; // 数据的总条数
    private long pageNum;//当前显示的页码

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }
}
