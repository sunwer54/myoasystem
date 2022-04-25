<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <head>
        <div >
            <h1 align="center">公司收入统计柱状图</h1>
        </div>
        <meta charset="utf-8">
    </head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js"></script>

<script type="text/javascript">
    //页面加载事件
    $(function () {
        //发送ajax请求
        $.get("income/getDatas",function (data) {
            // alert(data); //希望拿到的data数据是[['人员外包', '项目开发', '技术咨询费', '房租收入'],[1200, 2000, 1050, 800]]结构
            eval("var datas="+data);//这里的eval可以自动变成对象var datas = [['人员外包','项目开发','技术咨询费','房租收入'],[132000,117000,30000,55000]]
            // alert(datas[0])
            // alert(datas[1])


            // var datas = [['人员外包', '项目开发', '技术咨询费', '房租收入'],[1200, 2000, 1050, 800]];
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var app = {};

            var option;

            option = {
                xAxis: {
                    type: 'category',
                    data: datas[0]
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: datas[1],
                    type: 'bar',
                    showBackground: true,
                    backgroundStyle: {
                        color: 'rgba(180, 180, 180, 0.2)'
                    }
                }]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        })
    })
</script>
</body>
</html>

