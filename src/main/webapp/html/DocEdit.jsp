<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>push内容工具 | 新用户内容池</title>

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
                        <form action="/findDocsByKeyword" method="get">
                            <select id="type" name="searchType" class="col-lg-1" style="height:46px;background-color:#1ab394;border-radius:0px;color:white;font-size:17px">
                                <option value="docId">docId</option>
                                <option value="title">标题</option>
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
                                        <th class="col-lg-1">docId</th>
                                        <th class="col-lg-2">标题</th>
                                        <th class="col-lg-3">摘要</th>
                                        <th class="col-lg-1.5">过期时间</th>
                                        <th class="col-lg-0.5">channel</th>
                                        <th class="col-lg-1.5">level1</th>
                                        <th class="col-lg-1">level2</th>
                                        <th class="col-lg-0.5">level3</th>
                                        <th class="col-lg-0.5">type</th>
                                        <th class="col-lg-0.5">操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${docDTOList}" var="doc">
                                        <tr>
                                            <td>${doc.docId}</td>
                                            <td>${doc.title}</td>
                                            <td>${doc.summary}</td>
                                            <td>${doc.expire}</td>
                                            <td>${doc.channel}</td>
                                            <td>${doc.level1}</td>
                                            <td>${doc.level2}</td>
                                            <td>${doc.level3}</td>
                                            <td>${doc.type}</td>
                                            <td><a href="/deleteDoc?id=${doc.id}&&currentPage=${pageModel.currentPage}" type="button" class="btn btn-danger btn-sm">删除</a></td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>

                                </table>

                                <%--todo: 给下面这个控件取名字，然后根据当前页，以及总共页面数的关系用js来设置这个控件的内容--%>
                                <%--首页往前，尾页往后怎么处理--%>
                                <%--href可以改为onclick(page)..然后写个js function就好--%>
                                <%--<div class="text-center">--%>
                                    <%--<div class="btn-group">--%>
                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=0">第一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage-1}">上一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage-2}">${pageModel.currentPage-2}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage-1}">${pageModel.currentPage-1}</a>--%>

                                        <%--<a class="btn btn-white active" href="/findDocs?currentPage=${pageModel.currentPage}">${pageModel.currentPage}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage+1}">${pageModel.currentPage+1}</a>--%>
                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage+2}">${pageModel.currentPage+2}</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.currentPage+1}">下一页</a>--%>

                                        <%--<a class="btn btn-white" href="/findDocs?currentPage=${pageModel.totalPage-1}">最后一页</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="text-center">
                                    <div class="btn-group" id="pageDiv">

                                    </div>
                                </div>


                                <div class="pull-right">
                                    第 ${pageModel.currentPage} 页／共 ${pageModel.totalPage} 页
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
            <%--$.get(${pageContext.request.contextPath}, {'currentPage': page});--%>
        <%--}--%>
        pageDiv = "<div class=\"text-center\">\n" +
            "                                    <div class=\"btn-group\">\n" +
            "                                        <a class=\"btn btn-white\" href=\"/findDocs?currentPage=1\">第一页</a>";

        if(${pageModel.currentPage} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage-1}\">上一页</a>";
        }
        if(${pageModel.currentPage} > 2){
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage-2}\">${pageModel.currentPage-2}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage-1}\">${pageModel.currentPage-1}</a>";
        }else if(${pageModel.currentPage} > 1){
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage-1}\">${pageModel.currentPage-1}</a>";
        }

        pageDiv += "<a class=\"btn btn-white active\" href=\"/findDocs?currentPage=${pageModel.currentPage}\">${pageModel.currentPage}</a>";

        if(${pageModel.currentPage} < ${pageModel.totalPage-1}){
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage+1}\">${pageModel.currentPage+1}</a>\n" +
                "                                        <a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage+2}\">${pageModel.currentPage+2}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage+1}\">下一页</a>";
        }else if(${pageModel.currentPage} < ${pageModel.totalPage}){//当前页是倒数第二页
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage+1}\">${pageModel.currentPage+1}</a>";
            pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.currentPage+1}\">下一页</a>";
        }

        pageDiv += "<a class=\"btn btn-white\" href=\"/findDocs?currentPage=${pageModel.totalPage}\">最后一页</a>";

        document.getElementById("pageDiv").innerHTML = pageDiv;
    </script>

</body>

</html>
