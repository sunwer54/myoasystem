<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">修改部门信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <%
        //从url中取数据
        String deptno = request.getParameter("deptno");
        String deptname = request.getParameter("deptname");
        String location = request.getParameter("location");
    %>
    <ul class="forminfo">
        <form action="dept/updateDeptByDeptNo">
            <li><label>部门编号</label><input name="deptno" type="text" class="dfinput" value="<%= deptno%>" disabled="disabled"/> </li>
            <%--使用隐藏标签(这个标签中的内容是不显示到网页上的),用来提交禁止编辑的数据--%>
            <input type="hidden" name="deptno" value="<%= deptno%>"/>
            <li><label>部门名称</label><input name="deptname" type="text" class="dfinput"  value="<%= deptname%>"/></li>
            <li><label>办公地点</label><input name="location" type="text" class="dfinput" value="<%= location%>"/></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
        </form>
    </ul>


</div>


</body>

</html>

