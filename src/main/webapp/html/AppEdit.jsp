<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>D1小程序助手 | App管理</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <link href="../font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../css/dropzone.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

</head>

<body>

    <div id="wrapper">

        <jsp:include page="nav.jsp"></jsp:include>

        <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">

        </div>

            <%--这里填写查询条件的form--%>

            <div class="row wrapper border-bottom white-bg page-heading">
                <%--<div class="col-lg-2">--%>

                <%--</div>--%>

                <div class="col-lg-12">
                    <div class="search-form">
                        <form action="/findAppsByKeyword" method="get">
                            <select id="type" name="searchType" class="col-lg-1" style="height:46px;background-color:#1ab394;border-radius:0px;color:white;font-size:17px">
                                <option value="keyword">关键词</option>
                                <%--<option value="title">标题</option>--%>
                                <%--这里可以扩展搜索关键词属性--%>
                            </select>
                            <div class="input-group">
                                <input type="text" placeholder="请键入搜索内容" name="keyword" class="form-control input-lg">
                                <div class="input-group-btn">
                                    <button class="btn btn-lg btn-primary" type="submit" style="font-size:17px">
                                        搜索
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>

            </div>


        <div class="wrapper wrapper-content animated fadeIn">

            <div class="row">


                        <div class="ibox float-e-margins">

                            <div class="ibox-content" style="padding:0">
                                <table class="table table-striped" style="word-break:break-all; word-wrap:break-word; font-size: 15px">

                                    <thead>
                                    <tr>
                                        <th class="col-lg-1">图标</th>
                                        <th class="col-lg-0.5">appid</th>
                                        <th class="col-lg-1">名称</th>
                                        <th class="col-lg-1">开发者</th>
                                        <th class="col-lg-2.5">摘要</th>
                                        <th class="col-lg-1.5">分类</th>
                                        <th class="col-lg-2">备注</th>
                                        <th class="col-lg-2.5">操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${appDTOList}" var="app">
                                        <tr>
                                            <%--这里填写的属性名字就是服务端定义的属性名--%>
                                            <%--这里第一栏把app的图标也展示出来吧,设置好样式不要太大了--%>
                                            <td>
                                                <%--图片圆角怎么设置？ --%>
                                                <img alt="image" class="img-circle" src="http://localhost:8080${app.icon}" style="width:50px;height:50px"/>
                                            </td>
                                            <td>${app.appid}</td>
                                            <td>${app.appName}</td>
                                            <td>${app.dev}</td>
                                            <td>${app.summary}</td>
                                            <%--ct现在展示的是数字，后面需要修改成中文名，对于没有ct的展示空白，这样更容易识别出来--%>
                                            <%--分类做成下来菜单，而且可以直接在本页进行更新，ajax请求更新，更新成功或者失败以alert的形式反馈给用户--%>
                                            <%--下拉菜单用全量分类数据来展示，然后设置selected id为当前app的ct数据--%>
                                            <%--<td>${app.ct}</td>--%>
                                            <td>
                                                <%--这里可以包装一个控件用于设置这个下拉菜单的样式--%>
                                                <form:select id="ct" path="cts" name="ct">
                                                    <form:option value="${app.ct}" label="${app.ctName}" />
                                                    <form:options items="${cts}" itemLabel="name" itemValue="id"/>
                                                </form:select>
                                            </td>
                                            <td>${app.remark}</td>
                                            <td>
                                                <%--根据app情况设置这个按钮，如果是已经审核通过,点击设置为不通过，如果不通过则点击变为通过。。。这个需求怎么做？--%>
                                                <%--删除后还是需要返回当前页面，所以需要带上curPage参数;但是对于令挖几个只是需要ajax请求的并不涉及到页面跳转，因此则不需要带上这个当前页面的参数--%>
                                                <c:if test="${app.valid eq true}">
                                                    <a href="/auditApp?appid=${app.appid}&&valid=false" type="button" class="btn btn-primary btn-sm">取消通过</a>
                                                </c:if>
                                                <c:if test="${app.valid eq false}">
                                                    <a href="/auditApp?appid=${app.appid}&&valid=true" type="button" class="btn btn-primary btn-sm">审核通过</a>
                                                </c:if>

                                                <a href="/updateCt?appid=${app.appid}&&ct=${app.ct}" type="button" class="btn btn-info btn-sm">更新分类</a>
                                                <a href="/editApp?appid=${app.appid}&&curPage=${pageModel.curPage}" type="button" class="btn btn-warning btn-sm disabled">编辑</a>
                                                <%--先不开放删除功能，将这个按键设置为不可点击--%>
                                                <a href="/deleteApp?appid=${app.appid}" type="button" class="btn btn-danger btn-sm disabled">删除</a>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>

                                </table>

                                <%--todo: 给下面这个控件取名字，然后根据当前页，以及总共页面数的关系用js来设置这个控件的内容--%>
                                <%--首页往前，尾页往后怎么处理--%>
                                <%--href可以改为onclick(page)..然后写个js function就好--%>
                                <%--<div class="text-center">--%>
                                    <%--<div class="btn-group">--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=0">第一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-1}">上一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-2}">${pageModel.curPage-2}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage-1}">${pageModel.curPage-1}</a>--%>

                                        <%--<a class="btn btn-white active" href="/findDocs?curPage=${pageModel.curPage}">${pageModel.curPage}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+1}">${pageModel.curPage+1}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+2}">${pageModel.curPage+2}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.curPage+1}">下一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?curPage=${pageModel.totalPage-1}">最后一页</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="text-center">
                                    <div class="btn-group" id="pageDiv">

                                    </div>
                                </div>


                                <div class="pull-right">
                                    第 ${pageModel.curPage} 页／共 ${pageModel.totalPage} 页
                                </div>

                            </div>
                        </div>




            </div>

        </div>


            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        </div>



    <!-- Mainly scripts -->
    <script src="../js/jquery-2.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <script src="../js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../js/inspinia.js"></script>

    <!-- DROPZONE -->
    <script src="../js/dropzone.js"></script>


    <script>
        <%--function jump(page){--%>
            <%--alert();--%>
            <%--$.get(${pageContext.request.contextPath}, {'curPage': page});--%>
        <%--}--%>
        pageDiv = "<div class=\"text-center\">\n" +
            "                                    <div class=\"btn-group\">\n" +
            "                                        <a class=\"btn btn-white\" href=\"/findApps?curPage=1\">第一页</a>";

        if(${pageModel.curPage} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage-1}\">上一页</a>";
        }
        if(${pageModel.curPage} > 2){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage-2}\">${pageModel.curPage-2}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage-1}\">${pageModel.curPage-1}</a>";
        }else if(${pageModel.curPage} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage-1}\">${pageModel.curPage-1}</a>";
        }

        pageDiv += "<a class=\"btn btn-white active\" href=\"/editApp?curPage=${pageModel.curPage}\">${pageModel.curPage}</a>";

        if(${pageModel.curPage} < ${pageModel.totalPage-1}){
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage+1}\">${pageModel.curPage+1}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage+2}\">${pageModel.curPage+2}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage+1}\">下一页</a>";
        }else if(${pageModel.curPage} < ${pageModel.totalPage}){//当前页是倒数第二页
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage+1}\">${pageModel.curPage+1}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.curPage+1}\">下一页</a>";
        }

        pageDiv += "<a class=\"btn btn-white\" href=\"/editApp?curPage=${pageModel.totalPage}\">最后一页</a>";

        document.getElementById("pageDiv").innerHTML = pageDiv;
    </script>

</body>

</html>
