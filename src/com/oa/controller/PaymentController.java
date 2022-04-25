package com.oa.controller;

import com.oa.pojo.PaymentData;
import com.oa.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
    @RequestMapping(value = "/getPaymentDatas",produces = "text/html;charset=utf-8")
    public String getPaymentDatas(){
        List<PaymentData> paymentDatas = paymentService.getPaymentDatas();
        System.out.println("paymentDatas:"+paymentDatas);
//[PaymentData(type=1, totalamount=300), PaymentData(type=2, totalamount=200), PaymentData(type=4, totalamount=3000), PaymentData(type=5, totalamount=3200), PaymentData(type=3, totalamount=1000)]
        /*
                [
                    {value: 1048, name: '通讯费用'},
                    {value: 1735, name: '办公室耗材'},
                    {value: 980, name: '住宿费用'},
                    {value: 984, name: '房租水电'},
                    {value: 900, name: '其他'}
                ]
            */
        String datas = "[\n" +
                "                    {value: "+paymentDatas.get(0).getTotalamount()+", name: '通讯费用'},\n" +
                "                    {value: "+paymentDatas.get(1).getTotalamount()+", name: '办公室耗材'},\n" +
                "                    {value: "+paymentDatas.get(2).getTotalamount()+", name: '住宿费用'},\n" +
                "                    {value: "+paymentDatas.get(3).getTotalamount()+", name: '房租水电'},\n" +
                "                    {value: "+paymentDatas.get(4).getTotalamount()+", name: '其他'}\n" +
                "                ]";
        return datas;
    }
}
