<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <style>
        .ul-right{
            min-width:450px;
        }
    </style>
</head>
<body>
    <ul class="pull-right ul-right">
        <li class="col-md-6">欢迎${sessionScope.username}进入系统</li>
        <li class="col-md-6"><a href="exit">注销</a></li>
    </ul>
</body>
</html>