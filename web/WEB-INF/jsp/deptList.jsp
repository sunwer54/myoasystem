<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <%--相当于java中的import--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery.js"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $(".click").click(function(){
                    $(".tip").fadeIn(200);
                });

                $(".tiptop a").click(function(){
                    $(".tip").fadeOut(200);
                });

                $(".sure").click(function(){
                    $(".tip").fadeOut(100);
                });

                $(".cancel").click(function(){
                    $(".tip").fadeOut(100);
                });

            });

            //使用单击事件通过ajax发送删除部门的请求
            function  deleteDeptByNo(deptno) {
                //发送ajax请求
                $.get("dept/deleteDeptByNo",{"deptno":deptno},function (data) {
                    if(data =="1"){
                        //重新跳转到
                        location.href="dept/showDept";
                    }else{
                        document.getElementById("deleteError").innerHTML=data;
                    }
                })
            }
        </script>


    </head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">部门管理</a></li>
    </ul>
</div>

<div class="rightinfo">


    <div class="formtitle1"><span>部门列表</span></div>

    <table class="tablelist" >
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>部门名称</th>
            <th>办公地点</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <%--items从作用域中取流转过来的数据,通过${depts}取--%>
        <c:forEach items="${depts}" var="dept">  <%--相当于java中的增强for循环for(Dept dept:depts)--%>
            <tr>
                <td><input name="" type="checkbox" value="" /></td>
                <td>${dept.deptno}</td>
                <td>${dept.deptname}</td>
                <td>${dept.location}</td>
                <td><a href="deptUpdate?deptno=${dept.deptno}&deptname=${dept.deptname}&location=${dept.location}" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" onclick="deleteDeptByNo(${dept.deptno})" > 删除<span id="deleteError"></span></a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>



    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>

    </div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>

