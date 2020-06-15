<%@page pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/bootstrap.css">
    <link rel="stylesheet" href="./js/jquery-3.3.1.js">
    <link rel="stylesheet" href="./js/bootstrap.js">
</head>
<body>
    <div class="container-fluid">
        <form action="changeEmp" method="post">
            <div class="form-group">
                <label for="empno">员工编号</label>
                <input type="text" value="${requestScope.emp.empno}" class="form-control" id="empno" name="empno" placeholder="员工编号">
            </div>
            <div class="form-group">
                <label for="ename">员工姓名</label>
                <input type="text" value="${requestScope.emp.ename}" class="form-control" id="ename" name="ename" placeholder="员工姓名">
            </div>
            <div class="form-group">
                <label for="hiredate">入职日期</label>
                <input type="text" value="${requestScope.emp.hiredate}" class="form-control" id="hiredate" name="hiredate" placeholder="入职日期">
            </div>
            <button type="submit" class="btn btn-default">保存</button>
        </form>
    </div>
</body>
</html>