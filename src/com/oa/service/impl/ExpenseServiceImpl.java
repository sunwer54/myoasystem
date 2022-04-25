package com.oa.service.impl;

import com.oa.mapper.*;
import com.oa.pojo.*;
import com.oa.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseMapper expenseMapper;
    @Override
    public String selectExpId() {
        return expenseMapper.selectExpId();
    }

    @Autowired
    private ExpenseitemMapper expenseitemMapper;
    @Autowired
    private ExpimageMapper expimageMapper;
    //添加报销单
    @Transactional(propagation = Propagation.REQUIRED)
    public int expenseAdd(Expense expense) throws Exception{
        //报销单输入expense表中
        int n = expenseMapper.insert(expense);//如果插入数据成功返回1
//        System.out.println(1/0);
        //报销明细要插入expenseitem表中
        List<Expenseitem> items = expense.getItems();
        int n2 =0;
        for (int i = 0; i < items.size(); i++) {
            Expenseitem expenseitem = items.get(i);
            n2 += expenseitemMapper.insert(expenseitem);//n2的值 = items.size()
        }
        //把报销图片存入expimage表中
        int n3 =0;
        List<Expimage> expimages = expense.getExpimages();
        for (int i = 0; i <expimages.size() ; i++) {
            Expimage expimage = expimages.get(i);
            n3 += expimageMapper.insert(expimage);//n3的值 = expimages.size()
        }
        if((n+n2+n3) == 1+items.size()+expimages.size()) {
            return 1;
        }else {
            throw new Exception("添加报销失败!");
        }
    }
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Expense> showExpenses(String employeeId) {
        ExpenseExample expense = new ExpenseExample();
        expense.createCriteria().andNextauditorEqualTo(employeeId);
        List<Expense> expenses = expenseMapper.selectByExample(expense);
        System.out.println("expenses:"+expenses);
        for (Expense exp:expenses) {
            String empid = exp.getEmpid();
            Employee employee = employeeMapper.selectByPrimaryKey(empid);
            String realname = employee.getRealname();
            exp.setRealname(realname);
        }
        return expenses;
    }

    @Override
    public List<Expenseitem> showExpenseDetail(int expid) {
        ExpenseitemExample exp = new ExpenseitemExample();
        exp.createCriteria().andExpidEqualTo(expid);
        return expenseitemMapper.selectByExample(exp);
    }

    @Override
    public List<Expimage> findImagesByExpid(int expid) {
        ExpimageExample exp = new ExpimageExample();
        exp.createCriteria().andExpidEqualTo(expid);
        List<Expimage> expimages = expimageMapper.selectByExample(exp);
        return expimages;
    }
    @Autowired
    private AuditingMapper auditingMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Transactional(propagation = Propagation.REQUIRED)   //加入事务的处理
    public int auditing(Auditing auditing) throws Exception {
        int n = 0;
        //首先获取到需要审核的报销单
        Integer expid = auditing.getExpid();
        //需要审核的报销单
        Expense expense = expenseMapper.selectByPrimaryKey(expid);
        System.out.println("auditing:"+auditing);

        //审核逻辑的设计
        if("1".equals(auditing.getResult())){   //通过
            //进入审核流程
            if("xiaoqiao".equals(auditing.getEmpid())){   //是财务
                //添加财务的支出
                Payment payment = new Payment();
                payment.setPayempid("xiaoqiao");
                payment.setPaytime(new Date());
                payment.setExpid(expid);
                payment.setEmpid(expense.getEmpid());
                payment.setAmount(expense.getTotalamount());
                //往payment表中插入数据
                paymentMapper.insert(payment);

                //添加审核记录auditing表中加入数据
                auditingMapper.insert(auditing);
                //修改报销单的状态,下一个审核人:空
                    //封装需要修改报销单的数据
                Expense expAudit = new Expense();
                expAudit.setExpid(expid);
                expAudit.setLastresult("已打款");
                expAudit.setNextauditor(" ");
                expAudit.setStatus("4");//已打款
                expenseMapper.updateByPrimaryKeySelective(expAudit);
                System.out.println("小乔打款了");

            }else{  //不是财务
                if(expense.getTotalamount() > 2500){  //费用超过2500,必须有总裁审核才能交给财务打款
                    if("admin".equals(auditing.getEmpid())){
                        //是总裁
                        //添加审核记录
                        auditingMapper.insert(auditing);
                        //修改报销单状态,下一个审核人是:财务
                        Expense expAudit = new Expense();
                        expAudit.setExpid(expid);
                        expAudit.setLastresult("通过");
                        expAudit.setNextauditor("xiaoqiao");
                        expAudit.setStatus("3");//通过
                        expenseMapper.updateByPrimaryKeySelective(expAudit);
                    }else{
                        //不是总裁:是普通的管理层
                        //添加审核记录
                        auditingMapper.insert(auditing);
                        //修改报销单状态,下一个审核人:总裁
                        Expense expAudit = new Expense();
                        expAudit.setExpid(expid);
                        expAudit.setLastresult("审核中");
                        expAudit.setNextauditor("admin");
                        expAudit.setStatus("1");//通过
                        expenseMapper.updateByPrimaryKeySelective(expAudit);
                    }

                }else{
                    //<=2500,普通管理层审核就可以交给财务打款
                    //添加审核记录
                    auditingMapper.insert(auditing);
                    //修改报销单状态,下一个审核人:财务
                    Expense expAudit = new Expense();
                    expAudit.setExpid(expid);
                    expAudit.setLastresult("通过");
                    expAudit.setNextauditor("xiaoqiao");
                    expAudit.setStatus("2");//通过
                    expenseMapper.updateByPrimaryKeySelective(expAudit);
                }
            }

        }else {   //拒绝2或驳回3的情况
            //添加审核记录
            //修改报销单的状态,下一个审核人:不变
        }
        n=1;
        if(n==1){
            return n;
        }else{
            throw new Exception("审核失败");
        }
    }
}
