<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/select.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
        <script type="text/javascript" src="js/select-ui.min.js"></script>
        <script type="text/javascript" src="editor/kindeditor.js"></script>
        <script type="text/javascript">
            $(document).ready(function(e) {
                $(".select1").uedSelect({
                    width : 345
                });

            });
        </script>
    </head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">修改员工信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li>
            <label>用户名</label>
            <input name="" type="text" class="dfinput" value="lei" /><i>必须唯一，也可以根据真实姓名自动生成</i></li>
        <li>
        <li>
            <label>真实姓名</label>
            <input name="" type="text" class="dfinput" value="尼古拉斯雷少侠"/><i></i></li>
        <li>
            <label>性别</label><cite>
            <c:if test="${updateEmp.sex == 1}">
                <input name="sex" type="radio" value="1" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="sex" type="radio" value="0" />女<i>也可以根据身份证号自动获取</i></cite>
            </c:if>
            <c:if test="${updateEmp.sex == 0}">
                <input name="sex" type="radio" value="1"/>男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="sex" type="radio" value="0"  checked="checked" />女<i>也可以根据身份证号自动获取</i></cite>
            </c:if>
        </li>
        <li>
            <label>出生日期</label>
            <input name="" type="text" class="dfinput" /><i>也可以根据身份证号自动获取</i></li>
        <li>
        <li>
            <label>入职时间</label>
            <input name="" type="text" class="dfinput" /><i></i></li>

        <li>
            <label>离职时间</label>
            <input name="" type="text" class="dfinput" /><i></i></li>
        <li>
            <label>是否在职</label><cite>
            <c:if test="${updateEmp.onduty ==1}">
                <input name="onduty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0" />否</cite>
            </c:if>
            <c:if test="${updateEmp.onduty == 0}">
                <input name="onduty" type="radio" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0"  checked="checked" />否</cite>
            </c:if>
        </li>
        <li>
            <label>所属部门<b>*</b></label>
            <div class="vocation">
                <select class="select1" name="deptno">
                    <c:forEach items="${depts}" var="dept">
                        <c:if test="${updateEmp.deptno == dept.deptno}">
                            <option value="${dept.deptno}" selected="selected">${dept.deptname}</option>
                        </c:if>
                        <c:if test="${updateEmp.deptno != dept.deptno}">
                            <option value="${dept.deptno}">${dept.deptname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

        </li>
        <li>
            <label>所在职位<b>*</b></label>
            <div class="vocation">
                <select class="select1" name="posid">
                    <c:forEach items="${positions}" var="position">
                        <c:if test="${updateEmp.posid == position.posid}">
                            <option value="${position.posid}" selected="selected">${position.pname}</option>
                        </c:if>
                        <c:if test="${updateEmp.posid != position.posid}">
                            <option value="${position.posid}" >${position.pname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            &nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
        </li>
        <li>
            <label>直接上级<b>*</b></label>
            <div class="vocation">
                <select class="select1" name="mgrid">
                    <c:forEach items="${employees}" var="emp">
                        <c:if test="${updateEmp.mgrid == emp.empid}">
                        <option value="${emp.empid}" selected="selected">${emp.realname}</option>
                        </c:if>
                        <c:if test="${updateEmp.mgrid != emp.empid}">
                            <option value="${emp.empid}">${emp.realname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            &nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
        </li>
        <li>
            <label>联系方式</label>
            <input name="" type="text" class="dfinput" />
        </li>
        <li>
            <label>QQ号</label>
            <input name="" type="text" class="dfinput" />
        </li>
        <li>
            <label>紧急联系人信息</label>
            <textarea name="" cols="" rows="" class="textinput"></textarea>
        </li>
        <li>
            <label>身份证号</label>
            <input name="" type="text" class="dfinput" />
        </li>
        <li>
            <label>&nbsp;</label>
            <input name="" type="button" class="btn" value="确认保存" />
        </li>
    </ul>

</div>

</body>

</html>