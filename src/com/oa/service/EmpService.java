package com.oa.service;

import com.oa.pojo.Employee;
import com.util.Result;

import java.sql.Date;
import java.util.List;

public interface EmpService {
    public Employee loginUser(Employee employee);

    public List<Employee> showMgrs();

    public int empAdd(Employee employee);

    public Result showEmpList(long pageNum);
    public List<Employee> multyFind(String empid, int deptno, int onduty, String hiredate);

    public Employee findById(String empid);

    public Employee findEmpByPhone(String phone);

}
