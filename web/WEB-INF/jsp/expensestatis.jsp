<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
        <meta charset="utf-8">
    </head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript">
    $(function () {
        $.get("getPaymentDatas",function (data) {
            eval("var datas="+data);

           /*
            var datas =
                [
                    {value: 1048, name: '通讯费用'},
                    {value: 1735, name: '办公室耗材'},
                    {value: 980, name: '住宿费用'},
                    {value: 984, name: '房租水电'},
                    {value: 900, name: '其他'}
                ]
            */
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var app = {};

            var option;



            option = {
                title: {
                    text: '公司的财务支出统计',
                    subtext: '来自财务部门',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '50%',
                        data: datas,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        })
    })
</script>
</body>
</html>

