<%@page pageEncoding="utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工管理系统</title>
    <link rel="stylesheet" href="./css/bootstrap.css" class="src">

    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <style>
        @media(min-width: 768px){
            .navbar-left-1{
                min-width: 200px;
                min-height: 900px;
                top: 51px;
                position: absolute;
            }
            .ul-top{
                min-width: 500px;
            }
            .iframe1{
                  margin-left: 200px;
            }
        }
        .badred{
            background: pink;
        }
        .iframe2{
            width: 100%;
            height: 100%;
        }
    </style>

</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
             <a href="#ul-top" data-toggle="collapse"><span class="navbar-brand">员工管理系统 <span class="badge badred">20</span></span></a>
            <button class="navbar-toggle" data-toggle="collapse" data-target="#navbar-left-1">
                <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
            </button>

        </div>
        <ul id="ul-top" class="nav navbar-nav ul-top navbar-right collapse">
            <li class="col-xs-4"><a href="#">欢迎${sessionScope.username}</a></li>
            <li class="col-xs-4"><a href="#">收件箱 <span class="badge badred">20</span></a></li>
            <li class="col-xs-4"><a href="exit"><span class="glyphicon glyphicon-off"></span> 注销</a></li>
        </ul>
        <div id="navbar-left-1" class="nav navbar-default navbar-left-1 navbar-collapse collapse">
        <ul class="nav nav-pills nav-stacked ">
            <li><a href="#sub1" data-toggle="collapse"><span class="glyphicon glyphicon-menu-down"></span> 员工管理</a></li>
            <ul id="sub1" class="nav collapse">
                <li><a href="selectEmp" target="window-iframe"><span class="glyphicon glyphicon-menu-right"></span> 查看员工</a></li>
                <li><a href="addEmp.html" target="window-iframe"><span class="glyphicon glyphicon-menu-right"></span> 添加员工</a></li>
            </ul>
        </ul>
        </div>
    </nav>
    <div class="iframe1">
        <iframe name="window-iframe" class="iframe2"></iframe>
    </div>

</body>
</html>