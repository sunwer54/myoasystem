package com.oa.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class Expense implements Serializable {
    //持有报销人的真名
    private String realname;
    //持有报销凭证(图片)
    private List<Expimage> expimages;
    //持有报销明细
    private List<Expenseitem> items;
    private Integer expid;  //报销单id

    private String empid;  //报销人的id

    private Double totalamount;  //报销的总额

    private Date exptime; //报销的日期

    private String expdesc; //报销单的描述

    private String nextauditor; //下一个审核人(上级)

    private String lastresult; //报销的情况: 新提交,审核中,驳回,审核通过

    private String status; //报销的状态: 1,待审核2,通过3,驳回4,已打款


}