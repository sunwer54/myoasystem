package com.oa.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 享学课堂-SaiLing老师
 * @老师qq:2408524688
 */
@Data
public class PaymentData implements Serializable {
    private String type;
    private int totalamount;
}
