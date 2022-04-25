package com.oa.service.impl;

import com.oa.mapper.DeptMapper;
import com.oa.mapper.DutyMapper;
import com.oa.pojo.*;
import com.oa.service.DutyService;
import com.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class DutyServiceImpl implements DutyService {
    @Autowired
    private DutyMapper dutyMapper;
    @Override
    public Duty findBy(String empid, Date date) {
        DutyExample exp = new DutyExample();
        exp.createCriteria().andEmpridEqualTo(empid).andDtdateEqualTo(date);
        List<Duty> duties = dutyMapper.selectByExample(exp);
        if(duties.size() == 1){
            return duties.get(0); //有数据说明已经打过卡
        }else {
            return null;
        }
    }

    @Override
    public int signIn(Duty duty) {
        return dutyMapper.insert(duty);
    }

    @Override
    public int signOut(Duty duty) {
        DutyExample exp = new DutyExample();
        exp.createCriteria().andEmpridEqualTo(duty.getEmprid()).andDtdateEqualTo(duty.getDtdate());
        int n = dutyMapper.updateByExampleSelective(duty, exp);
        return n;
    }
    @Autowired
    private EmpServiceImpl empService;
    @Autowired
    private DeptMapper deptMapper;
    public List<Duty> showDutys(){
        //所有的员工
        List<Duty> dutys = dutyMapper.selectByExample(null);
        for(Duty duty:dutys){
            //通过emprid查employee
            Employee emp = empService.findById(duty.getEmprid());
            duty.setRealname(emp.getRealname());
            //通过deptno 查deptname
            Dept dept = deptMapper.selectByPrimaryKey(emp.getDeptno());
            duty.setDeptname(dept.getDeptname());
        }
        return dutys;
    }

    @Override
    public List<DutyData> findMultyDuty(DutyData dutyData) {
        return dutyMapper.findMultyDuty(dutyData);
    }
}
