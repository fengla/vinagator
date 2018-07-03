<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>注册 - meetu</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <!-- <link href="css/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet"> -->

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <img src="img/logo.jpeg" class="img-circle" style="width:180px;height:180px" alt="profile">

            </div>
            <h3>Nice 2 meetu !</h3>
            <p>Create account to see it in action.
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>


            <!--这里form怎么指定get,post方法呢？还是说都可以？？？-->


            <form:form class="m-t" role="form" method="POST" action="/doRegister">
                <div class="form-group">
                    <form:input type="email" class="form-control" placeholder="登录邮箱" required="" path="email"/>
                </div>
                <div class="form-group">
                    <form:input type="password" class="form-control" placeholder="登录密码" required="" path="pwd"/>
                </div>
                <!--todo important-->
                <!-- <div class="form-group">
                    <form:input type="password" class="form-control" placeholder="重复密码" required="" path="pwd"/>
                </div> -->
                <!-- 写一个js check两次输入的密码 -->
                <div class="form-group">
                    <form:input type="text" class="form-control" placeholder="昵称" required="" path="nickName"/>
                </div>
                <div class="form-group" id="date">
                    <div class="input-group date" style="margin-bottom:15px">

                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input type="text" class="form-control" value="生日" path="birthday"/>

                    </div>
                </div>
                <div class="form-group">
                    <form:input type="text" class="form-control" placeholder="专业" required="" path="major"/>
                </div>
                <div class="form-group">
                    <form:input type="text" class="form-control" placeholder="学校" required="" path="school"/>
                </div>
                <!-- 怎么改下拉列表？？？全国范围，北京放在第一个？？ 还是说当前只做北京一个地区-->
                <%--地区可以从微信数据中获取？我们主动的话是这么获取的，同时也支持用户后续在个人资料页面修改--%>
                <%--<div class="form-group">--%>
                    <%--<form:input type="text" class="form-control" placeholder="地区" required="" path="location"/>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<form:input type="text" class="form-control" placeholder="微信" required="" path="wechat"/>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<form:input type="text" class="form-control" placeholder="手机" required="" path="tel"/>--%>
                <%--</div>--%>
                <!--这个验证码肯定不是在UserDTO里面？那么应该放在哪里？-->
                <!-- <div class="form-group">
                    <input type="text" class="form-control" placeholder="验证码" required="" name="vCode">
                </div> -->
                <button type="submit" class="btn btn-primary block full-width m-b" style="background-color:#44b549">创建账号</button>


            </form:form>
            <p class="m-t"> <small>CopyRight &copy meetu.cn ; 2018</small> </p>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- Data picker -->
    <script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>

    <script>
    $('#date .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
    </script>

</body>

</html>
