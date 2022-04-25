package com.oa.controller;

import com.oa.mapper.DeptMapper;
import com.oa.pojo.Dept;
import com.oa.pojo.Duty;
import com.oa.pojo.DutyData;
import com.oa.pojo.Employee;
import com.oa.service.DeptService;
import com.oa.service.impl.DeptServiceImpl;
import com.oa.service.impl.DutyServiceImpl;
import com.util.ExcelOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/duty")
public class DutyController {
    @Autowired
    private DutyServiceImpl dutyService;

    @RequestMapping("/signIn")
    @ResponseBody
    public String signIn(HttpSession session){
        //查是否已经签到过
            //1.获取员工的empid
        Employee employee = (Employee)session.getAttribute("emp");
        String empid = employee.getEmpid();
        //当前的日期: java.sql.Date里只有年月日
        Date date = new Date(System.currentTimeMillis());
        //根据empid和date查是否有签到的数据
        Duty duty = dutyService.findBy(empid, date);
        if(duty == null){
            //没有签到过,执行签到
            Duty du = new Duty();
            du.setEmprid(empid);//签到人的id,为登录者的id
            du.setDtdate(date);
            //签到的时间 HH:mm:ss
            java.util.Date time = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String signInTime = sdf.format(time);
            du.setSignintime(signInTime);
            int n = dutyService.signIn(du);
            if(n ==1){
                //签到成功
                return "1";
            }else {
                return "0";

            }

        }else{
            //签到过了
            return "2";
        }
    }
    //签退处理
    @RequestMapping("/signOut")
    @ResponseBody
    public String signOut(HttpSession session){
        //判断是否签到过
        //1.获取员工的empid
        Employee employee = (Employee)session.getAttribute("emp");
        String empid = employee.getEmpid();
        //当前的日期: java.sql.Date里只有年月日
        Date date = new Date(System.currentTimeMillis());
        //根据empid和date查是否有签到的数据
        Duty duty = dutyService.findBy(empid, date);
        if(duty != null) {
            //签到过了,执行签退:根据empid和date修改signouttime这一列的数据
            //签退的时间 HH:mm:ss
            java.util.Date time = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String signOutTime = sdf.format(time);
            Duty du = new Duty();
            du.setEmprid(empid);
            du.setDtdate(date);
            du.setSignouttime(signOutTime);
            int n = dutyService.signOut(du);
            if(n ==1){
                return "1";
            }else{
                return "0";
            }
        }else{
            //还没有签到
            return "2";
        }
    }
    @Autowired
    private DeptServiceImpl deptService;
    //我的考勤
    @RequestMapping("/myDutyBefore")
    public String myDutyBefore(Model m){
        List<Duty> duties = dutyService.showDutys();
        List<Dept> depts = deptService.showDept();
        m.addAttribute("duties",duties);
        m.addAttribute("depts",depts);
        //在duty实体类中加一个字段(是 String sdtdate)
        System.out.println(duties);
        return "/dutyList";
    }

    @RequestMapping("/outDutyDataExcel")
    @ResponseBody
    public String outDutyDataExcel(){
        List<Duty> duties = dutyService.showDutys();
        try {
            ExcelOperate.createExcel(duties);
            return "1";//成功
        } catch (IOException e) {
            e.printStackTrace();
            return "0"; //失败
        }
    }

    //多条件查询考勤数据
    @RequestMapping("/findMultyDuty")
    public String findMultyDuty(String empid,int deptno,String dtdate,Model m) throws ParseException {
        DutyData dutyData = new DutyData();
        dutyData.setEmprid(empid);
        dutyData.setDeptno(deptno);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlDate=null;
        if(dtdate != null && !"".equals(dtdate)){
            java.util.Date date = sdf.parse(dtdate);
            sqlDate = new Date(date.getTime());
        }
        dutyData.setDtdate(sqlDate);

        List<DutyData> dutys = dutyService.findMultyDuty(dutyData);
        m.addAttribute("duties",dutys);
        return "/dutyList";
    }
}
