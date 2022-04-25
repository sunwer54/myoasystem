<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/select.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="js/jquery.js"></script>

        <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
        <script type="text/javascript" src="js/select-ui.min.js"></script>
        <script type="text/javascript" src="editor/kindeditor.js"></script>
        <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

        <script type="text/javascript">
            $(document).ready(function(e) {
                $(".select1").uedSelect({
                    width : 200
                });

            });
        </script>
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
        </script>

    </head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">员工管理</a></li>
    </ul>
</div>

<div class="rightinfo">
    <form action="emp/multyFind">
        <ul class="prosearch">
            <li>
                <label>查询：</label><i>用户名</i>
                <a>
                    <input name="empid" type="text" class="scinput" />
                </a><i>所属部门</i>
                <a>
                    <select class="select1" name="deptno">
                        <option value="-1">-请选择部门-</option>
                        <c:forEach items="${depts}" var="dept">
                        <option value="${dept.deptno}">${dept.deptname}</option>
                        </c:forEach>
                    </select>
                </a>

            </li>
            <li>
                <label>是否在职：</label>
                <input name="onduty" type="radio" value="1" checked="checked" />&nbsp;是&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0" />&nbsp;否
            </li>
            <li>
                <label>入职时间：</label>
                <a>
                    <input name="hiredate" type="text" onfocus="WdatePicker({startDate:'2000-01-01'})"  class="scinput" />
                </a>
            </li>
            <a>
                <input name="" type="submit" class="sure" value="查询" />
            </a>
        </ul>
    </form>
    <div class="formtitle1"><span>员工列表</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>真实姓名</th>
            <th>所属部门</th>
            <th>所属岗位</th>
            <th>入职时间</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${emps}" var="emp">
            <tr>
                <td>
                    <input name="" type="checkbox" value="" />
                </td>
                <td>${emp.empid}</td>
                <td>${emp.realname}</td>
                <td>${emp.dept.deptname}</td>
                <td>${emp.position.pname}</td>
                <td>${emp.hiredate}</td>
                <td>${emp.phone}</td>
                <td>
                    <a href="empInfo" class="tablelink">查看</a>
                    <a href="emp/empUpdateBefore/${emp.empid}" class="tablelink">修改</a>
                    <a href="#" class="tablelink click"> 删除</a>
                    <a href="#" class="tablelink"> 重置密码</a>
                </td>
            </tr>
        </c:forEach>


        </tbody>
    </table>

    <div class="pagin">
        <div class="message">共<i class="blue">${total}</i>条记录，当前显示第&nbsp;<i class="blue">${num+1}</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="emp/showEmpList/1">1</a></li>
            <li class="paginItem current"><a href="emp/showEmpList/2">2</a></li>
            <li class="paginItem"><a href="emp/showEmpList/3">3</a></li>
            <li class="paginItem"><a href="emp/showEmpList/4">4</a></li>
            <li class="paginItem"><a href="emp/showEmpList/5">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="emp/showEmpList/10">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>

    <div class="tip">
        <div class="tiptop"><span>提示信息</span>
            <a></a>
        </div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定" />&nbsp;
            <input name="" type="button" class="cancel" value="取消" />
        </div>

    </div>

</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>
