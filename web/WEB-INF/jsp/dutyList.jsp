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
        <link href="css/select.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="js/jquery.js"></script>

        <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
        <script type="text/javascript" src="js/select-ui.min.js"></script>
        <script type="text/javascript" src="editor/kindeditor.js"></script>
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
            
            function toexcel() {
                $.get("duty/outDutyDataExcel",function (data) {
                    if(data =="1"){
                        $("#daochu").val("导出成功!")
                    }else{
                        $("#daochu").val("导出失败!")
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
        <li><a href="#">考勤管理</a></li>
    </ul>
</div>

<div class="rightinfo">

    <ul class="prosearch">
            <form action = "duty/findMultyDuty">
        <li>
            <label>查询：</label><i>用户名</i>
            <a>
                <input name="empid" type="text" class="scinput" />
            </a><i>所属部门</i>
            <a>
                <select class="select1" name="deptno">
                        <option value="0">-请选择部门-</option>
                    <c:forEach items="${depts}" var="dept">
                        <option value="${dept.deptno}">${dept.deptname}</option>
                    </c:forEach>

                </select>
            </a>
            <i>考勤时间</i>
            <a>
                <input name="dtdate" type="date" class="scinput" />
            </a>
            <a>
                <input name="" type="submit" class="sure" value="查询"/>
            </a>
            <a>
                <input name="" type="button" onclick="toexcel()" class="scbtn2" value="导出" id="daochu"/>
            </a>

        </li>
            </form>


    </ul>

    <div class="formtitle1"><span>考勤管理</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>真实姓名</th>
            <th>所属部门</th>
            <th>出勤日期</th>
            <th>签到时间</th>
            <th>签退时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${duties}" var="duty">
            <tr>
                <td>
                    <input name="" type="checkbox" value="" />
                </td>
                <td>${duty.emprid}</td>
                <td>${duty.realname}</td>
                <td>${duty.deptname}</td>
                <td>${duty.dtdate}</td>
                <td>${duty.signintime}</td>
                <td>${duty.signouttime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
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