package com.oa.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 * 封装考勤管理的数据
 */
@Data  //注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；
public class DutyData {
    private String emprid;
    private String realname;
    private int deptno;
    private String deptname;
    private Date dtdate;
    private String signintime;
    private String signouttime;

}
