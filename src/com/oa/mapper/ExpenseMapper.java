package com.oa.mapper;

import com.oa.pojo.Expense;
import com.oa.pojo.ExpenseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpenseMapper {
    long countByExample(ExpenseExample example);

    int deleteByExample(ExpenseExample example);

    int deleteByPrimaryKey(Integer expid);

    int insert(Expense record);

    int insertSelective(Expense record);

    List<Expense> selectByExample(ExpenseExample example);

    Expense selectByPrimaryKey(Integer expid);

    int updateByExampleSelective(@Param("record") Expense record, @Param("example") ExpenseExample example);

    int updateByExample(@Param("record") Expense record, @Param("example") ExpenseExample example);

    int updateByPrimaryKeySelective(Expense record);

    int updateByPrimaryKey(Expense record);

    //从expense表中查当前的自增id的值
    public String selectExpId();
}