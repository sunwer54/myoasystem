package com.oa.controller;

import com.oa.pojo.*;
import com.oa.service.impl.EmpServiceImpl;
import com.oa.service.impl.ExpenseServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/exp")
public class ExpenseController {
    //添加报销前数据准备
    @Autowired
    private EmpServiceImpl empService;
    @RequestMapping("/expenseAddBeFore")
    public String expenseAddBeFore(Model m){
        List<Employee> mgrs = empService.showMgrs();
        m.addAttribute("mgrs",mgrs);
        return "/expenseAdd";
    }
    @Autowired
    private ExpenseServiceImpl expenseService;
    //添加报销
    @RequestMapping(value = "/expAdd",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String expAdd(String[]type, String[]amount, String[]itemdesc, String nextauditor, String expdesc, HttpSession session, MultipartFile[] photo, HttpServletRequest req){
        /*System.out.println("数据");
        System.out.println(Arrays.toString(type));
        System.out.println(Arrays.toString(amount));
        System.out.println(Arrays.toString(itemdesc));
        System.out.println(nextauditor);
        System.out.println(expdesc);*/
        //手动获取expid
        int expId=0;
        String expId2 = expenseService.selectExpId();//是当前数据库表中id加1,手动在数据库中先加入一个数据,让expid有初始值
        if(expId2 ==null){
            expId = 1;
        }else{
            expId = Integer.parseInt(expId2)+1;
        }
        //获取报销人的empid
        Employee employee= (Employee)session.getAttribute("emp");
        String empid = employee.getEmpid();
        //报销的总额=所有明细中的amount的和
        double totalamount = 0.0;
        //装报销明细
        List<Expenseitem> items = new ArrayList<>();
        if(amount != null){
            for (int i = 0; i <amount.length ; i++) {
                //获取每个报销明细中的amount的值
                double price = Double.parseDouble(amount[i]);
                totalamount += price;  //报销的总额=所有明细中的amount的和
                //创建一个明细对象Expenseitem
                Expenseitem item = new Expenseitem();
                item.setType(type[i]);
                item.setAmount(price);
                item.setItemdesc(itemdesc[i]);
                item.setExpid(expId);//报销单的expid(当前的报销明细输入哪张报销单)
                items.add(item);
            }
        }
        //创建一个报销单的对象Expense;
        Expense exp = new Expense();
        exp.setItems(items);
        exp.setExpid(expId);
        exp.setEmpid(empid);
        exp.setTotalamount(totalamount);
        exp.setExptime(new Date());//报销时间就是当前时间
        exp.setNextauditor(nextauditor);
        exp.setExpdesc(expdesc);
        //当前状态: 1,待审核2,通过3,驳回4,已打款
        exp.setStatus("1");
        exp.setLastresult("新提交");//报销的情况: 新提交,审核中,驳回,审核通过
        //加入报销的图片凭证
        List<Expimage> expimages = new ArrayList<>();
        for (int i = 0; i <photo.length ; i++) {
            //获取每张图片的封装对象
            MultipartFile multipartFile = photo[i];
            //获取图片的原始文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //在服务器中,创建一个目录来保存上传之后的图片数据
            //获取服务器tomcat的根目录
                //1.获取tomcat容器上下文对象ServletContext对象
            ServletContext servletContext = req.getServletContext();
                //2.获取服务器tomcat的根目录
            String rootPath = servletContext.getRealPath("/");
            //创建一个目录uploadFiles用来保存上传的文件
            File uploadFiles = new File(rootPath,"/uploadfiles");//uploadfiles在http://localhost:8090/MyOaSystem_war_exploded/uploadfiles下
            if(!uploadFiles.exists()){
                uploadFiles.mkdir();//创建目录
            }
            //用multipartFile对象上传数据
            UUID uuid = UUID.randomUUID();
            File file = new File(uploadFiles,uuid+originalFilename);
            //执行上传
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //图片存储到uploadfiles中的路径要存到数据库中 a.jpg
            Expimage expimage = new Expimage();
            expimage.setFilename("uploadfiles/"+uuid+originalFilename);//为了避免服务器文件同名,UUID+originalFilename作为别名
            expimage.setRealname(originalFilename);//文件的真名
            expimage.setExpid(expId);
            int index = originalFilename.lastIndexOf(".");
            String imgeType = originalFilename.substring(index);
            expimage.setFiletype(imgeType);//图片的后缀
            expimages.add(expimage);
        }
        //把报销图片全部放入报销单中
        exp.setExpimages(expimages);

        try {
            int  n = expenseService.expenseAdd(exp);
            if(n ==1){
                return "添加报销成功!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return "添加报销失败!";
    }

    //显示所有的报销
    @RequestMapping("/showExpenses")
    public String showExpenses(Model m,HttpSession session){
        Employee employee = (Employee)session.getAttribute("emp");

        List<Expense> expenses = expenseService.showExpenses(employee.getEmpid());
        m.addAttribute("expenses",expenses);
        return "/toAudit";
    }
    //查看具体的报销明细
    @RequestMapping("/showExpenseDetail/{expid}")
    public String showExpenseDetail(@PathVariable int expid,Model m){
        List<Expenseitem> expenseitems = expenseService.showExpenseDetail(expid);
        m.addAttribute("expenseitems",expenseitems);
        return "/expenseDetail";
    }


    //查看报销的凭证
    @RequestMapping("/showExpenseImg/{expid}")
    public String showExpenseImg(@PathVariable int expid,Model m){
        List<Expimage> images = expenseService.findImagesByExpid(expid);
        m.addAttribute("images",images);
        return "/expenseImg";
    }

    //完成审核
    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(int expid,String result,String auditdesc,HttpSession session){
        //当前的审核人就是登录的人
        Employee emp =(Employee)session.getAttribute("emp");
        //审核人的empid
        String empid = emp.getEmpid();
        //审核时间:当前的时间
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        //封装提交审核记录的数据
        Auditing auditing = new Auditing();
        auditing.setExpid(expid);
        auditing.setAuditdesc(auditdesc);
        auditing.setTime(date);
        auditing.setResult(result);
        auditing.setExpid(expid);
        auditing.setEmpid(empid);

        try {
            int n = expenseService.auditing(auditing);
            return "success";//成功
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";//失败
        }
    }
}
