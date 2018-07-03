<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>首页 - meetu</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body style="background-color:white">
    <div id="wrapper">


        <div class="row">
            <div class="col-lg-12">
                                <div class="ibox float-e-margins">
                                    <div class="ibox-title" style="border:0;background-color:#44b549;color:white">
                                        <img src="img/logo.jpeg" class="img-circle" style="width:48px;height:48px" alt="profile">
                                        <small> Hey guys, nice 2 meetu !</small>
                                        <!-- <small >hey guys, nice to meet u ~</small> -->
                                    </div>

                                    <div class="ibox-content">

                                            <%--<div class="feed-activity-list">--%>

                                                <%--<div class="feed-element">--%>
                                                    <%--<a href="profile.html" class="pull-left">--%>
                                                        <%--<img alt="image" class="img-circle" src="img/profile.jpg">--%>
                                                    <%--</a>--%>
                                                    <%--<div class="media-body ">--%>
                                                        <%--<small class="pull-right">5m ago</small>--%>
                                                        <%--<strong>时间:</strong> Today 5:60 pm - 12.06.2014 <br>--%>
                                                        <%--<strong>地点:</strong> 朝阳区 北京化工大学东校区操场 <br>--%>
                                                        <%--<strong>费用:</strong> 免费 <br>--%>
                                                        <%--<strong>发起人:</strong> ipv009 <br>--%>
                                                        <%--<a href="#"><i class="fa fa-angle-double-right" style="margin-top:5px"><Strong>详情</strong></i></a>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>

                                            <%--</div>--%>
                                                <%--<h2>${activityDTOList}<h2>--%>
                                         <div class="feed-activity-list">
                                            <c:forEach items="${activityDTOList}" var="activity">
                                                <div class="feed-element">
                                                    <a href="#" class="pull-left">
                                                        <%--可以考虑把这个38放大一点。。即先固定高度，再确定宽度;这样的话就不需要margin了--%>
                                                        <img alt="image" style="width:38px;height:56px;margin-top:15px" src="${activity.logo}">
                                                    </a>
                                                    <div class="media-body ">
                                                        <small class="pull-right">5m ago</small>
                                                        <strong>时间:</strong> ${activity.date} <br>
                                                        <strong>地点:</strong> ${activity.location} <br>
                                                        <strong>费用:</strong> ${activity.charge} <br>
                                                        <strong>发起人:</strong> ${activity.sponsor} <br>
                                                        <a href="/activityDetail?activityId=${activity.id}"><i class="fa fa-angle-double-right" style="margin-top:5px"><Strong>详情</strong></i></a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                         </div>


                                    </div>
                                </div>






                <%--引入公用footer--%>
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>





    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- Sparkline -->
    <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="js/demo/sparkline-demo.js"></script>

    <!-- ChartJS-->
    <script src="js/plugins/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="js/plugins/toastr/toastr.min.js"></script>


    <script>

    </script>
    <script>
        alert(activityDTOList);
        // (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        //     (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        //         m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        // })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
        //
        // ga('create', 'UA-4625583-2', 'webapplayers.com');
        // ga('send', 'pageview');

    </script>
</body>
</html>
