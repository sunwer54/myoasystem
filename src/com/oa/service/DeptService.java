package com.oa.service;

import com.oa.pojo.Dept;
import com.oa.pojo.Employee;

import java.sql.Date;
import java.util.List;

public interface DeptService {
    public int deptAdd(Dept dept);

    public List<Dept> showDept();

    public int updateDeptByDeptNo(Dept dept);

    public int deleteDeptByNo(int deptno) throws Exception;


}
