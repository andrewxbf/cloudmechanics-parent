<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <jsp:include page="/WEB-INF/jspPages/util.jsp"></jsp:include>
    <title>自动构建升级包</title>
    <script type="text/javascript" src="../js/common/echarts.common.min.js"></script>
    <script type='text/javascript' src='../js/common/jquery.particleground.js'></script>
    <script type='text/javascript' src='../js/common/demo.js'></script>
</head>
<body>
<div id="_maindiv" class="overflow-hidden">
    <div class="easyui-layout" data-options="fit:true">

        <div data-options="region:'north'" class="_layout_north overflow-hidden bor-btm" style="height:65px;">
            <div class="top_img">
                <div class="fleft clearfix" style="margin-top:10px; margin-left: 5px;"><img src="../images/man.png" class="fleft"/></div>
            </div>
        </div>

        <div data-options="region:'center'" id="_layout_center">

        </div>

        <div data-options="region:'west'" class="_layout_west nav-group"
             style="border-right:#e7e7eb 1px solid; width:160px;">
            <ul class="nav nav-pills nav-stacked" id="nav-1">
                <li>
                    <a href="javascript:;"><span class="navicon"></span>配置菜单<i class="up"></i></a>
                    <dl class="dls">
                        <dd>
                            <a id="_FirstMenu" class="_sub_menu" url="zdsjbAreaInfo/choiceLoginArea.do">&nbsp;&nbsp; 升级地区列表</a>
                        </dd>
                        <dd>
                            <a id="000" class="_sub_menu" url="zdsjbAreaInfo/showMainConfig.do">&nbsp;&nbsp; 升级包详细配置</a>
                        </dd>
                        <%--<dd>
                            <a id="aaa" class="_sub_menu" url="zdsjbAreaInfo/showJenkinsServerConfig.do">&nbsp;&nbsp; Jenkins配置</a>
                        </dd>
                        <dd>
                            <a id="bbb" class="_sub_menu" url="zdsjbAreaInfo/showDownloadServer.do">&nbsp;&nbsp; 下载服务器配置</a>
                        </dd>
                        <dd>
                            <a id="ccc" class="_sub_menu" url="zdsjbAreaInfo/showUploadServer.do">&nbsp;&nbsp; 上传服务器配置</a>
                        </dd>--%>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    var init = false;
    $.parser.onComplete = function (context) {
        if (init) return;
        init = true;

        function registLeftMenuEvent() {
            $('._sub_menu').bind("click", function () {
                var _sub_menu = $(this);
                $(".selected").removeClass("selected");
                /*$("#_layout_center").panel({
                    href: _sub_menu.attr("url"),
                    onLoad: function () {
                        //layout.onLoadShow($("._sub_north").find("form"));
                        layout.showMenu($("._load"));
                    }
                });*/
            });
            $(".nav .dls a").click(function () {
                $(this).parent().siblings().find("a").removeClass("red");
                $(this).addClass("red").parent().parent().parent().siblings().find(".dls a").removeClass("red");
                $(this).parent().parent().parent().addClass("li_cur").siblings().removeClass("li_cur");
            });
            $(".nav-group .nav >li > a").click(function () {
                if ($(this).parent().find(".dls").css("display") == "block") {
                    $(this).find("i").addClass("down");

                } else {
                    $(this).find("i").removeClass("down");
                }
                $(this).parent().addClass("li_cur").siblings().removeClass("li_cur");
                $(this).parent().find(".dls").slideToggle();
            });
        }

        var showCenter = function (url) {
            $("#_layout_center").panel({
                href: url,
                method: "post",
                onLoad: function () {
                    layout.showMenu($("._load"));
                    registLeftMenuEvent();
                }
            });
        };

        $("._sub_menu").click(function () {
            showCenter($(this).attr('url'));
        });

        //初始化页面-选择升级地区
        showCenter("zdsjbAreaInfo/choiceLoginArea.do");
    };

</script>


</html>
