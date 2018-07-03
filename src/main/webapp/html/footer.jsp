<%@ page contentType="text/html; charset=UTF-8" %>

<div class="footer" style="position:fixed;background-color:#44b549;color:white">
    <ul class="" style="list-style:none">

        <li class="col-xs-3">
            <a href="/showActivities" style="color:white">
                <i class="fa fa-institution" style="padding-left:6px"></i><br>首页
            </a>
        </li>
        <li class="col-xs-3">

            <a href="#" style="color:white">
                <i class="fa fa-group" style="padding-left:11px"></i><br>meetu
            </a>
        </li>


        <li class="col-xs-3 dropup">
            <a href="#" class="dropup-toggle count-info" data-toggle="dropdown" style="color:white">
                <i class="fa fa-rocket"  style="padding-left:9px"></i><br>X-Lab
            </a>
            <ul class="dropdown-menu dropdown-messages" style="padding-bottom:0;min-width:0;margin-bottom:10px;background-color:#44b549">
                <li>
                    <div class="dropdown-messages-box">

                        <div class="media-body">
                            <small style="color:white">10点10分</small>
                        </div>
                    </div>
                </li>
                <li class="divider"></li>
                <li>
                    <div class="dropdown-messages-box">

                        <div class="media-body ">
                            <small style="color:white"> 告  白 墙</small>

                        </div>
                    </div>
                </li>
                <li class="divider"></li>
                <li>
                    <div class="dropdown-messages-box">

                        <div class="media-body ">
                            <small style="color:white">历史推文</small>

                        </div>
                    </div>
                </li>
                <li class="divider"></li>
            </ul>
        </li>
        <li class="col-xs-3">

            <%--怎么注入这个user.id???--%>
            <a href="/profile?id=${user.id}" class="right-sidebar-toggle" style="color:white">
                <i class="fa fa-align-justify"></i><br>我
            </a>
        </li>
    </ul>
    <!-- <div class="pull-right">
        10GB of <strong>250GB</strong> Free.
    </div>
    <div>
        <strong>Copyright</strong> Example Company &copy; 2014-2015
    </div> -->
</div>