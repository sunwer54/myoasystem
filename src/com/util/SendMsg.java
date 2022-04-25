package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg {
    //发送短信的逻辑
    public static String sender() throws Exception{
        String num =getNum();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", "a17368123021"),new NameValuePair("Key", "d41d8cd98f00b204e988"),new NameValuePair("smsMob","15897321643"),new NameValuePair("smsText","本次登录验证码是:"+num)};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
            for(Header h : headers){
                System.out.println(h.toString());
            }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        System.out.println("num:"+num);
        return num;

    }
    //获取一个六位的随机数
    public static String getNum(){
        String num = "";
        while (num.length()<6){  //当拼接的字符串超过6个时,推出循环
            Random r = new Random();
            int n = r.nextInt(10);// 得到一个0~9之间的随机数
            num += n;//字符串拼接
        }
        return num;
    }

}