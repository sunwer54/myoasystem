package com.oa.service;

import com.oa.pojo.Auditing;
import com.oa.pojo.Expense;
import com.oa.pojo.Expenseitem;
import com.oa.pojo.Expimage;

import java.util.List;

public interface ExpenseService {
    public String selectExpId();
    public int expenseAdd(Expense expense) throws Exception;

    public List<Expense> showExpenses(String empid);

    public List<Expenseitem> showExpenseDetail(int expid);

    public List<Expimage> findImagesByExpid(int expid);

    public int auditing(Auditing auditing) throws Exception;

}
