package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.mapper.DeptMapper;
import com.oa.mapper.DutyMapper;
import com.oa.mapper.EmployeeMapper;
import com.oa.mapper.PositionMapper;
import com.oa.pojo.*;
import com.oa.service.EmpService;
import com.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee loginUser(Employee employee) {
        //封装查询条件
        EmployeeExample exp = new EmployeeExample();
        exp.createCriteria().andEmpidEqualTo(employee.getEmpid()).andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(exp);
        if(employees.size()==1) {
            return employees.get(0);//不加判断的话会报空指针异常
        }else {
            return null;
        }
    }

    @Override
    public List<Employee> showMgrs() {
        EmployeeExample exp = new EmployeeExample();
        exp.createCriteria().andEmptypeEqualTo(1); //上级员工 的emptype=1
        return employeeMapper.selectByExample(exp);
    }

    @Override
    public int empAdd(Employee employee) {
        employee.setPassword("000000");//给每一个新员工默认的密码
        int n = employeeMapper.insert(employee);
        return n;
    }

    //注入deptMapper
    @Autowired
    private DeptMapper deptMapper;
    //注入positionMapper
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public Result showEmpList(long pageNum) {
        //分页处理
        PageHelper.startPage((int)pageNum,3);//为由MyBatis,分页管理对象PageHelper接管查询数据,一定要写在查询动作之前
        List<Employee> employees = employeeMapper.selectByExample(null);
        for (Employee emp: employees) {
            Integer deptno = emp.getDeptno();
            //需要部门信息(根据deptNo查dept)
            Dept dept = deptMapper.selectByPrimaryKey(deptno);
            emp.setDept(dept);//把dept信息放入Employee的对象中
            Integer posid = emp.getPosid();
            //需要职位信息(根据posid查Position)
            Position position = positionMapper.selectByPrimaryKey(posid);
            emp.setPosition(position);//把position信息放入Employee的对象中
        }
        PageInfo info = new PageInfo(employees);
        long total = info.getTotal();//其实封装了获取employees.size()
        long pageNum2 = info.getPageNum();
        Result result =new Result();
        result.setEmps(employees);
        result.setTotal(total);
        result.setPageNum(pageNum2);
        return result;
    }

    public List<Employee> multyFind(String empid, int deptno, int onduty, String hiredate){
        EmployeeExample exp = new EmployeeExample();
        EmployeeExample.Criteria criteria = exp.createCriteria();
        if(empid !=null && empid != ""){
            criteria.andEmpidLike("%"+empid+"%");
        }
        if(deptno != -1){
            criteria.andDeptnoEqualTo(deptno);
        }
        criteria.andOndutyEqualTo(onduty);
        if(hiredate != null && hiredate !=""){
            //把时间的字符串格式转成java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sqlDate = null;
            try {
                java.util.Date date = sdf.parse(hiredate);
                sqlDate = new java.sql.Date(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            criteria.andHiredateGreaterThanOrEqualTo(sqlDate);
        }
        List<Employee> employees = employeeMapper.selectByExample(exp);
        return employees;
    }

    @Override
    public Employee findById(String empid) {
        return employeeMapper.selectByPrimaryKey(empid);
    }

    @Override
    public Employee findEmpByPhone(String phone) {
        EmployeeExample exp = new EmployeeExample();
        exp.createCriteria().andPhoneEqualTo(phone);
        List<Employee> employees = employeeMapper.selectByExample(exp);
        if(employees.size()>0){
            return employees.get(0);
        }else {
            return null;
        }
    }

}
