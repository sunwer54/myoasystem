package com.oa.service.impl;

import com.oa.mapper.DeptMapper;
import com.oa.pojo.Dept;
import com.oa.pojo.Employee;
import com.oa.pojo.EmployeeExample;
import com.oa.service.DeptService;
import com.util.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public int deptAdd(Dept dept) {
        int n = deptMapper.insert(dept);
        return n;
    }

    @Override
    public List<Dept> showDept() {
        List<Dept> depts = deptMapper.selectByExample(null);
        return depts;
    }

    @Override
    public int updateDeptByDeptNo(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public int deleteDeptByNo(int deptno) throws DeleteException{
        try{
           int n =  deptMapper.deleteByPrimaryKey(deptno);
            return n;
        }catch (Exception e){
            throw new DeleteException("删除异常");
        }
    }


}
