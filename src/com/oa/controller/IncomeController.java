package com.oa.controller;

import com.oa.pojo.IncomData;
import com.oa.service.impl.IncomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private IncomeServiceImpl incomeService;

    @RequestMapping(value = "/getDatas",produces = "text/html;charset=utf-8")
    public String getDatas(){
        //需要组装数据称为[['人员外包', '项目开发', '技术咨询费', '房租收入'],[1200, 2000, 1050, 800]]格式
        List<IncomData> datas = incomeService.getDatas();
        System.out.println("datas:"+datas);
        /*
        [IncomData(ictype=1, totalamout=132000), IncomData(ictype=2, totalamout=117000), IncomData(ictype=3, totalamout=30000), IncomData(ictype=4, totalamout=55000)]
        需求把以上的数据转成[['人员外包', '项目开发', '技术咨询费', '房租收入'],[1200, 2000, 1050, 800]]结构
         */
        StringBuilder sb = new StringBuilder();
        sb.append("[[");
       //第一步拼成 ['人员外包', '项目开发', '技术咨询费', '房租收入']
        for (int i = 0; i <datas.size()-1 ; i++) {
            IncomData incomData = datas.get(i);
            String title = "";
            sb.append("'"); //sb="[['"
            String titleData = incomData.getIctype();
            switch (titleData){
                case "1":
                    title="人员外包";
                    break;
                case "2":
                    title="项目开发";
                    break;
                case "3":
                    title="技术咨询费";
                    break;
                case "4":
                    title="房租收入";
                    break;
            }
            sb.append(title);//-->sb="[['人员外包"
            sb.append("',");//-->sb="[['人员外包',"
        }
            sb.append("'").append("房租收入").append("'],");//-->[['人员外包', '项目开发', '技术咨询费', '房租收入'],
      //第二步拼成  [1200, 2000, 1050, 800]
        sb.append("[");
        for (int i = 0; i <datas.size()-1 ; i++) {
            IncomData incomData = datas.get(i);
            int totalamout=incomData.getTotalamout();
            sb.append(totalamout).append(",");//-->[['人员外包', '项目开发', '技术咨询费', '房租收入'],[1200, 2000, 1050,
        }
        sb.append(datas.get(datas.size()-1).getTotalamout()).append("]]");
        System.out.println("sb:-----"+sb);
        return sb.toString();
    }
}
