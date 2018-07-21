<%@ page contentType="text/html; charset=UTF-8"%>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <img alt="image" class="img-circle" src="../img/logo.jpeg" style="width:150px;height:150px"/>
                    <a href="#">
                            <span class="clear"> <span class="block m-t-xs" style="color:gray"> <strong class="font-bold">推送工具</strong>
                             </span>  </span>
                    </a>

                    <a href="#">
                                 <span class="clear"> <span class="block m-t-xs" style="color:gray"> 新用户机器推送机型文章及视频文章维护工具
                                  </span>  </span>
                    </a>

                </div>

            </li>

            <li>
                <a href="#"><i class="fa fa-cloud-upload"></i> <span class="nav-label">文章上传 </span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a href="/docUpload">单篇上传</a></li>
                    <li><a href="/fileUpload">文件上传</a></li>
                </ul>
            </li>

            <li>
                <a href="/findDocs?currentPage=1"><i class="fa fa-database"></i> <span class="nav-label">文章管理</span></a>
            </li>

        </ul>

    </div>
</nav>