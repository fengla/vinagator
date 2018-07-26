<%@ page contentType="text/html; charset=UTF-8"%>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <img alt="image" class="img-circle" src="../img/logo.jpeg" style="width:150px;height:150px"/>
                    <a href="#">
                            <span class="clear"> <span class="block m-t-xs" style="color:gray"> <strong class="font-bold">d1app.cn</strong>
                             </span>  </span>
                    </a>

                    <a href="#">
                                 <span class="clear"> <span class="block m-t-xs" style="color:gray"> D1小程序助手管理工具
                                  </span>  </span>
                    </a>

                </div>

            </li>

            <li>
                <a href="#"><i class="fa fa-cloud-upload"></i> <span class="nav-label">APP上传 </span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a href="/uploadApp" class="disabled">手动上传</a></li>
                    <li><a href="/crawlApp">自动上传</a></li>
                </ul>
            </li>

            <li>
                <a href="/editApp?curPage=0"><i class="fa fa-database"></i> <span class="nav-label">APP管理</span></a>
            </li>

            <li>
                <a href="#" class="disabled"><i class="fa fa-bars"></i> <span class="nav-label">信息流管理</span></a>
            </li>

            <li>
                <a href="#"><i class="fa fa-gear"></i> <span class="nav-label">系统管理 </span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a href="/editBanner?curPage=0" class="disabled">Banner管理</a></li>
                    <li><a href="/editCt?curPage=0" class="disabled">分类信息管理</a></li>
                </ul>
            </li>

        </ul>

    </div>
</nav>