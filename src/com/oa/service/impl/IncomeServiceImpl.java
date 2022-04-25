package com.oa.service.impl;

import com.oa.mapper.IncomeMapper;
import com.oa.pojo.IncomData;
import com.oa.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeMapper incomeMapper;
    @Override
    public List<IncomData> getDatas() {

        return  incomeMapper.getDatas();
    }
}
