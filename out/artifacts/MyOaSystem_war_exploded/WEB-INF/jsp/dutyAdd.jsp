<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery.js"></script>
    <script>
        function signIn() {
            $.get("duty/signIn",function (data) {
                if(data == "1"){
                    $("#signSpan").html("签到成功!")
                }else if(data == "2"){
                    $("#signSpan").html("已经签到过了!")
                }else if(data == "0"){
                    $("#signSpan").html("签到失败!")
                }

            })
        }

        function signOut() {
            $.get("duty/signOut",function (data) {
                if(data =="2"){
                    $("#signOutSpan").html("你还没有签到,先签到!");
                }else if(data == "1"){
                    $("#signOutSpan").html("签退成功!");
                }else if(data == "0"){
                    $("#signOutSpan").html("签退失败!");
                }
            })
        }
    </script>
    </head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">考勤管理</a></li>
        <li><a href="#">签到签退</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="signIn()" value="签到"/><span id="signSpan"></span> 每天签到一次，不可重复签到</li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label></li>
        <li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="signOut()" value="签退"/><span id="signOutSpan"></span>可重复签退，以最后一次签退为准</li>
    </ul>


</div>


</body>

</html>
