<%--
  Created by IntelliJ IDEA.
  User: jxxy
  Date: 2019/11/9
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="./css/bootstrap.css">
    <link rel="stylesheet" href="./js/jquery-3.3.1.js">
    <link rel="stylesheet" href="./js/bootstrap.js">
    <script language="JavaScript">
      function delectEmp(da) {
          window.location.href='delectEmp?empno='+da;
      }
      function choseEmp(da) {
          window.location.href='choseEmp?empno='+da;
      }
      function gofirst(page) {
          if(page>1) {
              window.location.href = 'selectEmp?page=1';
          }
      }
      function gonext(page,totalpage) {
          if(page<totalpage) {
              page++;
              window.location.href = 'selectEmp?page=' + page;
          }
      }
      function golast(page) {
          if(page>1) {
              page--;
              window.location.href = 'selectEmp?page=' + page;
          }
      }
      function goend(page,totalpage) {
          if(page<totalpage) {
              window.location.href = 'selectEmp?page=' + totalpage;
          }
      }
      function hidefirst(page,totalpage) {
          if (page==1) {
              document.getElementById("first").style.display="none";
              document.getElementById("last").style.display="none";
          }else if(page==totalpage){
              document.getElementById("end").style.display="none";
              document.getElementById("next").style.display="none";
          }
      }
    </script>
    <style>
      .selectpage{
        margin-right: 50px;
      }
    </style>
  </head>
  <body onload="hidefirst(${page},${totalpage})">
  <div class="row">
    <div class="col-xs-12">
      <table class="table table-bordered table-hover table-striped">
        <tr>
          <th>员工编号</th>
          <th>员工姓名</th>
          <th>员工工资</th>
          <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.empDT}" var="emp">
          <tr>
            <td>${emp.empno}</td>
            <td>${emp.ename}</td>
            <td>${emp.sal}</td>
            <td>
              <a href="JavaScript:void(0)" onclick="delectEmp(${emp.empno})">删除</a>
              <a href="JavaScript:void(0)" onclick="choseEmp(${emp.empno})">修改</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
    <div class="pull-right selectpage">
      <%--<a href="selectEmp?page=1">首页</a>--%>
      <%--<a href="selectEmp?page=${lastpage}">上一页</a>--%>
      <%--<a href="selectEmp?page=${nextpage}">下一页</a>--%>
      <%--<a href="selectEmp?page=${totalpage}">尾页</a>--%>
        <input type="button" id="first" style="" value="首页" onclick="gofirst(${page})"/>
        <input type="button" id="last" style="" value="上一页" onclick="golast(${page})"/>
        <input type="button" id="next" style="" value="下一页" onclick="gonext(${page},${totalpage})"/>
        <input type="button" id="end" style="" value="尾页" onclick="goend(${page},${totalpage})"/>
    </div>
  </div>
  </body>
</html>
