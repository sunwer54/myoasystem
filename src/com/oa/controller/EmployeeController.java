package com.oa.controller;

import com.oa.mapper.EmployeeMapper;
import com.oa.pojo.Dept;
import com.oa.pojo.Employee;
import com.oa.pojo.EmployeeExample;
import com.oa.pojo.Position;
import com.oa.service.impl.DeptServiceImpl;
import com.oa.service.impl.EmpServiceImpl;
import com.oa.service.impl.PositoinServiceImpl;
import com.util.Result;
import com.util.SendMsg;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmpServiceImpl empService;
    //使用shiro登录
    @RequestMapping(value = "/loginShiro",method = RequestMethod.POST)
    public String loginShiro(HttpServletRequest req,Model m){
        //获取shiro框架中的登录异常
        Object attribute = req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(attribute != null){
            m.addAttribute("msg","用户名或密码有误!");
            return "/login";
        }else{
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String loginUser(Employee employee, Model m, HttpSession session){
        Employee emp = empService.loginUser(employee);
        if(emp != null){
            //登陆成功,跳转到main页面,重定向
            //把登录者的身份信息放入session
            session.setAttribute("emp",emp);
            return "redirect:/main";

        }else {
            //登录失败,回到登录页面
            m.addAttribute("msg","你的用户名和密码有误!");
            return "/login";

        }
    }
    //处理shiro登出
    @RequestMapping(value = "/loginShiroOut",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String loginShiroOut(HttpSession session){
        //强制销毁session
        session.invalidate();
        return "谢谢使用,欢迎下次再来!";
    }
    //退出系统
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session,Model m){
        //强制销毁session
        session.invalidate();
        return "redirect:/login";
    }

    @Autowired
    private DeptServiceImpl deptService;
    @Autowired
    private PositoinServiceImpl positoinService;
    //添加员工之前的数据准备工作
    @RequestMapping("/empAddBefore")
    public String empAddBefore(Model m){
        //查询所有的部门信息
        List<Dept> depts = deptService.showDept();
        //查所有的岗位信息
        List<Position> positions = positoinService.showPostions();
        //查所有的上级的信息
        List<Employee> mgrs = empService.showMgrs();
        m.addAttribute("depts",depts);
        m.addAttribute("positions",positions);
        m.addAttribute("mgrs",mgrs);
        return "/empAdd";
    }

    //添加员工
    @RequestMapping(value = "/empAdd",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String empAdd(String empid, Integer deptno,String realname, Integer posid, String mgrid, String sex, Date birthdate, Date hiredate, @RequestParam(defaultValue = "2100-10-10") Date leavedate, Integer onduty, Integer emptype, String phone, String qq, String emercontactperson, String idcard){
        Employee employee = new Employee(empid,deptno,posid,mgrid,realname,sex,birthdate,hiredate,leavedate,onduty,emptype,phone,qq,emercontactperson,idcard);
        System.out.println("employee:"+employee);
        int n = empService.empAdd(employee);
        if(n ==1){
            return "添加成功!";
        }else{
            return "添加失败!";
        }

    }
    //显示所有的员工
    @RequestMapping("/showEmpList/{pageNum}")
    public String showEmpList(Model m,@PathVariable long pageNum){
        Result result = empService.showEmpList(pageNum);
        System.out.println("emps:"+result.getEmps());
        m.addAttribute("emps",result.getEmps());
        m.addAttribute("total",result.getTotal());
        m.addAttribute("num",result.getPageNum());
        List<Dept> depts = deptService.showDept();
        m.addAttribute("depts",depts);
        return "/empList";
    }
    //多条件查询用户信息
    @RequestMapping("/multyFind")
    public String multyFind(String empid,int deptno,int onduty,String hiredate,Model m){
        List<Employee> emps = empService.multyFind(empid,deptno,onduty,hiredate);
        m.addAttribute("emps",emps);
        return "/empList";
    }

    //修改前数据准备
    @RequestMapping("/empUpdateBefore/{empid}")
    public String empUpdateBefore(Model m,@PathVariable String empid){
        //所有的部门
        List<Dept> depts = deptService.showDept();
        //所有的上级
        List<Employee> employees = empService.showMgrs();
        //所有的职位信息
        List<Position> positions = positoinService.showPostions();
        //根据empid查用户
        Employee updateEmp = empService.findById(empid);
        m.addAttribute("depts",depts);
        m.addAttribute("employees",employees);
        m.addAttribute("updateEmp",updateEmp);
        m.addAttribute("positions",positions);

        return "/empUpdate";
    }
    String sms;
    //短信登录之前处理
    @RequestMapping("/smsSend")
    @ResponseBody
    public String smsSend(String phone,Model m){
        //根据phone查数据库,看是否有这个phone对应相关的用户
        Employee employee = empService.findEmpByPhone(phone.trim());
        if(employee != null){
            //这个phone对应相关的用户是存在的
            //执行发送短信
            try {
                sms = SendMsg.sender();//后台接收到的短信验证码
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "1";
        }else{
            m.addAttribute("msg","你还没有注册,请先注册用户!");
            return "2";
        }
    }



    /**
     * 处理短信登录
     * @param code   是手机端收到的验证码
     * @param m
     * @return
     */
    @RequestMapping("/smsLogin")
    public String smsLogin(String code,Model m,HttpSession session,String phone){
            if(code.equals(sms)){
                //短信校验成功,允许登录
                Employee employee = empService.findEmpByPhone(phone);
                session.setAttribute("emp",employee);
                    //跳转页面到main,重定向

                return "redirect:/main";
            }else{
                m.addAttribute("msg","验证码不正确");
                return "/smslogin";
            }
    }


}
