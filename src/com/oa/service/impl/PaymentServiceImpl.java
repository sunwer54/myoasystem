package com.oa.service.impl;

import com.oa.mapper.PaymentMapper;
import com.oa.pojo.PaymentData;
import com.oa.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Override
    public List<PaymentData> getPaymentDatas() {
        return paymentMapper.getPaymentDatas();
    }
}
