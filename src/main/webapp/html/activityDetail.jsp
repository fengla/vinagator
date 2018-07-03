<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>活动详情 - meetu</title>

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
            <div class="col-lg-12" style="padding-left:0;padding-right:0;color:white">
              <div class="contact-box center-version" style="margin:0">

                  <a style="background-color:#44b549">
<%--这里怎么获取到当前网站的根目录？？？--%>
<%--原来的哪些换行符都没有保存下来？怎么处理？--%>
    <%--不写前面这一串url是否可以正常执行？--%>
                      <img alt="image" class="" src="${activity.logo}" style="height:238px;width:160px">

                  </a>
                  <div class="text-center" style="color:black">

                                <h2>
                                    ${activity.name}
                                </h2>
                  </div>
                  <div class="contact-box-footer" style="color:#676a6c;border-top:0;padding-top:5px;padding-bottom:5px">
                      <div class="m-t-xs btn-group">
                          <button type="button" class="btn btn-default btn-xs" onclick="interest()" style="width:100px;border-color:#44b549;color:#44b549">感 兴 趣: ${activity.interested}</button>
                          <!-- <a class="btn btn-xs btn-white"><i class="fa fa-envelope"></i> 留言</a>
                          <a class="btn btn-xs btn-white"><i class="fa fa-user-plus"></i> 关注</a> -->
                      </div>
                  </div>

              </div>
          </div>

                                    <div class="ibox-content">

                                            <div class="feed-activity-list" >

                                                <div class="form-inline" >
                                                    <i class="fa fa-calendar"></i> 时间:<p>${activity.date}</p>
                                                </div>

                                                <div class="form-inline">
                                                    <i class="fa fa-map-marker"></i> 地点:<p>${activity.location}</p>
                                                </div>

                                                <div class="form-inline">
                                                    <i class="fa fa-rmb"></i> 费用:<p>${activity.charge}</p>
                                                </div>

                                                <div class="form-inline">
                                                    <i class="fa fa-envelope"></i> 联系主办方:<p>${activity.sponsor}</p>
                                                </div>

                                            </div>

                                            <div class="text-center" style="color:black">

                                                      【活动详情】
                                            </div>
                                            <p>${activity.details}</p>

                                            <div class="text-center" style="color:black">

                                                      【活动须知】
                                            </div>
                                            <p>${activity.tips}</p>

                                      <div class="text-center" style="color:black">

                                        【活动图片】
                                      </div>
                                      <p>此处为图片及图片说明（图片及说明都要居中显示）</p>

                                      <div class="row">
                                <div class="col-lg-12">

                                    <h4>【讨论区】</h4>



                                    <c:forEach items="${commentDetailList}" var="comment">
                                        <div class="social-feed-box" style="border:0;margin:0">
                                            <div class="social-avatar" style="padding-top: 0;padding-bottom: 0">
                                                <a href="article.html" class="pull-left">
                                                    <%--attention:可以把用户的头像统一归纳在这个目录下img/portrait当然这个目录是相对于网站根目录而言的--%>
                                                    <img alt="image" src="img/portrait/${comment.userLogo}">
                                                </a>
                                                <div class="media-body">
                                                    <a href="article.html#">
                                                            ${comment.userName}
                                                    </a>
                                                    <small class="text-muted">${comment.updateTime}</small>
                                                </div>
                                            </div>
                                            <div class="social-body">
                                                <p>
                                                    ${comment.content}
                                                </p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div id="newComment"></div>


                                </div>

    <div class="col-lg-12">





                <div class="col-lg-12">
                    <div id="commentForm" class="form-horizontal">
                        <textarea class="note-editor note-editable form-control" name="commentContent" id="commentContent"></textarea>

                            <%--<a href="mailbox.html" class="btn btn-sm btn-primary pull-right" data-toggle="tooltip" data-placement="top" title="Send" style="background-color:#44b549;margin-top:10px;margin-bottom:30px">--%>
                                <%--<i class="fa fa-reply"></i> 发布留言--%>
                            <%--</a>--%>
                            <button class="btn btn-sm btn-primary pull-right" data-toggle="tooltip" data-placement="top" onclick="putComment()" style="background-color:#44b549;margin-top:10px;margin-bottom:100px">
                                <i class="fa fa-reply"></i> 发表留言
                            </button>
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

    <script type="text/javascript">
        function putComment() {
            var data = {
                activityId:1,
                userId:1,
                content:$("#commentContent").val()
            };
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "${pageContext.request.contextPath}/putComment" ,//url
                //contentType: "application/json",
                // data: $('#commentForm').serialize(),
                data: data,
                success: function (result) {
                    //alert(result.toJSONString());
                    //真实逻辑：这里应该把新的评论消息加到文章下方
                    console.log(result);//打印服务端返回的数据(调试用)
                    // if (result.resultCode == 200) {
                    //     alert("SUCCESS");
                    // }
                    var s = "<div class=\"social-feed-box\" style=\"border:0\">\n" +
                        "                                            <div class=\"social-avatar\">\n" +
                        "                                                <a href=\"article.html\" class=\"pull-left\">\n" +
                        "                                                    <img alt=\"image\" src=\"img/a1.jpg\">\n" +
                        "                                                </a>\n" +
                        "                                                <div class=\"media-body\">\n" +
                        "                                                    <a href=\"article.html#\">\n" +
                        "                                                        "+result.userName+"\n" +
                        "                                                    </a>\n" +
                        "                                                    <small class=\"text-muted\">"+result.updateTime+"</small>\n" +
                        "                                                </div>\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"social-body\">\n" +
                        "                                                <p>\n" +
                        "                                                    "+result.content+"\n" +
                        "                                                </p>\n" +
                        "                                            </div>\n" +
                        "                                        </div>";
                    document.getElementById("newComment").innerHTML += s;

                },
                error : function(result) {
                    //console.log(result);
                    //真实逻辑：提示评论失败
                    alert("评论失败！");
                }
            });
        }

        function interest(){
            //todo:这里还是要写ajax来做！！！
        }
    </script>

    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-4625583-2', 'webapplayers.com');
        ga('send', 'pageview');

    </script>


</body>
</html>
