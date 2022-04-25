package com.oa.controller;

import com.oa.pojo.Dept;
import com.oa.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptServiceImpl deptService;

    @RequestMapping(value = "/deptadd",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deptAdd(Dept dept){  //整体接收用户提交的数据
        int n = deptService.deptAdd(dept);
        if(n ==1){
            //添加成功
            return "添加成功";
        }else{
            return "添加失败";
        }
    }
    //查询部门信息
    @RequestMapping("/showDept")
    public String showDept(Model m){
        List<Dept> depts = deptService.showDept();
        m.addAttribute("depts",depts);
        return "/deptList";
    }

    //修改部门信息
    @RequestMapping(value = "/updateDeptByDeptNo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String updateDeptByDeptNo(Dept dept){
        int n = deptService.updateDeptByDeptNo(dept);
        if(n==1){
            return "修改成功!";
        }else{
            return "修改失败!";
        }
    }
    //根据部门编号删除部门
    @RequestMapping(value = "/deleteDeptByNo",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteDeptByNo(String deptno){
        System.out.println(deptno);
        int n = 0;
        try {
            n = deptService.deleteDeptByNo(Integer.parseInt(deptno));
            return n+""; //返回数据,要加响应体
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("当前部门下有员工,不能删除");
        }
        return "当前部门下有员工,不能删除";
    }
}
