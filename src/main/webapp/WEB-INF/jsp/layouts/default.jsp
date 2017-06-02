<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html class="lte-ie9" lang="en">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no"/>

    <%
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        String showImgUrl = "http://erp.kocla.com/assets/img/favicon-koala";
        if (tempContextUrl.indexOf("monster") > 0){
            showImgUrl = "http://erp.kocla.com/assets/img/favicon-monster";//使用同一图标
        }
    %>
    <c:set var="contextUrl" value="<%=tempContextUrl%>" />
    <link rel="icon" type="image/png" href="<%=showImgUrl%>-16x16.png" sizes="16x16">
    <link rel="icon" type="image/png" href="<%=showImgUrl%>-32x32.png" sizes="32x32">

    <title>考拉微店ERP管理系统</title>
    <link rel="stylesheet" href="../bower_components/uikit/css/uikit.almost-flat.min.css" media="all">

    <!-- flag icons -->
    <link rel="stylesheet" href="../assets/icons/flags/flags.min.css" media="all">

    <!-- altair admin -->
    <link rel="stylesheet" href="../assets/css/main.min.css" media="all">

    <!-- matchMedia polyfill for testing media queries in JS -->
    <link href="../kendo/styles/kendo.default.min.css" rel="stylesheet"/>
    <link href="../kendo/styles/kendo.default.mobile.min.css" rel="stylesheet"/>
    <link href="../kendo/styles/kendo.common.min.css" rel="stylesheet"/>
    <link href="../kendo/styles/kendo.bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../assets/css/MyStyle.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/easyui/easyui.css" >
    <link rel="stylesheet" href="../css/login/jgmodal.css" />
    <!--[if lte IE 9]>
    <script type="text/javascript" src="../bower_components/matchMedia/matchMedia.js"></script>
    <script type="text/javascript" src="../bower_components/matchMedia/matchMedia.addListener.js"></script>
    <![endif]-->
    <script src="../assets/js/common.min.js"></script>
    <script src="../js/common/StrUtil.js"></script>
    <script src="../bower_components/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../assets/js/form_validator.js" type="text/javascript"></script>
    <script type="text/javascript" src="../kendo/js/kendo.web.min.js"></script>
    <script type="text/javascript" src="../kendo/js/messages/kendo.messages.zh-CN.min.js"></script>
    <script type="text/javascript" src="../kendo/js/cultures/kendo.culture.zh-CN.min.js"></script>
    <script type="text/javascript" src="${ctx}/kendo/js/kendo.all.min.js"></script>


    <script type="text/javascript" src="../highcharts/highcharts.js"></script>
    <script type="text/javascript" src="../highcharts/modules/exporting.js"></script>
    <script type="text/javascript" src="../assets/js/pages/components_notifications.min.js"></script>
    <script type="text/javascript" src="../assets/js/application.common.js"></script>
    <script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="sidebar_main_open sidebar_main_swipe">
<!-- main header -->
<header id="header_main">
    <div class="header_main_content">
        <nav class="uk-navbar">
            <a href="#" id="sidebar_main_toggle" class="sSwitch sSwitch_left">
                <span class="sSwitchIcon"></span>
            </a>
            <a href="#" id="sidebar_secondary_toggle" class="sSwitch sSwitch_right sidebar_secondary_check">
                <span class="sSwitchIcon"></span>
            </a>

            <div class="uk-navbar-flip">
                <ul class="uk-navbar-nav user_actions">
                    <li>
                        <a href="#" id="main_search_btn" class="user_action_icon">
                            <i class="material-icons md-24 md-light">&#xE8B6;</i>
                        </a>
                    </li>
                    <li data-uk-dropdown="{mode:'click'}">
                        <a href="#" class="user_action_icon"><i class="material-icons md-24 md-light">&#xE7F4;</i><span class="uk-badge">${sessionScope.myTodos.totalCount}</span></a>
                        <div class="uk-dropdown uk-dropdown-xlarge uk-dropdown-flip">
                            <div class="md-card-content">
                                <ul class="uk-tab uk-tab-grid" data-uk-tab="{connect:'#header_alerts',animation:'slide-horizontal'}">
                                    <li class="uk-width-1-2 uk-active"><a href="#" class="js-uk-prevent uk-text-small">我的待办&nbsp;(${sessionScope.myTodos.totalCount})</a></li>
                                    <li class="uk-width-1-2"><a href="#" class="js-uk-prevent uk-text-small">我的消息&nbsp;0</a></li>
                                </ul>
                                <ul id="header_alerts" class="uk-switcher uk-margin">
                                    <li>
                                        <ul class="md-list md-list-addon">
                                            <c:if test="${sessionScope.myTodos.ap}">
                                                <li>
                                                    <div class="md-list-addon-element">
                                                        <span class="md-user-letters md-bg-cyan">ap</span>
                                                    </div>
                                                    <div class="md-list-content">
                                                        <span class="md-list-heading"><a href="../teachingAffairs/toAuditPage.do?explain=applicationdo">排课审核&nbsp;(${sessionScope.myTodos.appTodoCount})</a></span>
                                                    </div>
                                                </li>
                                            </c:if>
                                            <c:if test="${sessionScope.myTodos.pa}">
                                                <li>
                                                    <div class="md-list-addon-element">
                                                        <span class="md-user-letters md-bg-light-green">pa</span>
                                                    </div>
                                                    <div class="md-list-content">
                                                        <span class="md-list-heading"><a href="../financials/refundRequestFromYjj.do?explain=refundRequestFromYjjdo">家长退费审核&nbsp;(${sessionScope.myTodos.paTodoCount})</a></span>
                                                    </div>
                                                </li>
                                            </c:if>
                                            <c:if test="${sessionScope.myTodos.di}">
                                                <li>
                                                    <div class="md-list-addon-element">
                                                        <span class="md-user-letters md-bg-light-green">di</span>
                                                    </div>
                                                    <div class="md-list-content">
                                                        <span class="md-list-heading"><a href="../financials/refundRequestFromErp.do?explain=refundRequestFromErpdo">主任退费审核&nbsp;(${sessionScope.myTodos.diTodoCount})</a></span>
                                                    </div>
                                                </li>
                                            </c:if>

                                        </ul>
                                    </li>
                                    <li>
                                        NO MESSAGE
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li data-uk-dropdown="{mode:'click'}">
                        <a href="#" class="user_action_image">
                            <img class="md-user-image" src="../assets/img/avatars/avatar_11_tn.png"  alt=""/>
                        </a>
                        <div class="uk-dropdown uk-dropdown-small uk-dropdown-flip">
                            <ul class="uk-nav js-uk-prevent">
                                <%--<li><a href="page_user_profile.html">个人信息</a></li>
                                <li><a href="../base/editpassword.do">修改密码</a></li>
                                <li><a href="page_settings.html">设置</a></li>--%>
                                <!--<li><a href="#mymodal" data-uk-modal>切换机构端</a></li>-->
                                <li>
                                    <a href="../logout.do">退出</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="header_main_search_form">
        <i class="md-icon header_main_search_close material-icons">&#xE5CD;</i>

        <form class="uk-form">
            <input type="text" class="header_main_search_input"/>
            <button class="header_main_search_btn uk-button-link"><i class="md-icon material-icons">&#xE8B6;</i>
            </button>
        </form>
    </div>
</header>
<aside id="sidebar_main">
    <div class="sidebar_main_header">
        <div class="sidebar_logo">
            <a href="#" class="sSidebar_hide">
               <%-- <img src="../assets/img/erplogo03.png" alt="" height="45" width="220"/>--%>
                <img src="../assets/img/logo/logo${sessionScope.corpId}.png" alt=""style="height: 20px !important;"/>
                <span style="color:#fff;font-size: 14px; font-weight: bold;position: relative;top:5px;">ERP管理系统</span>
            </a>
            <a href="#" class="sSidebar_show">
                <img src="../assets/img/logo_main_small.png" alt="" height="32"  width="32"/>
            </a>
        </div>
    </div>
    <div class="menu_section" id="menus">
        <ul>
            <c:forEach var="funcMap" items="${sessionScope.loginWebSession.sysFunc}">
                <c:set var="path" value="${funcMap.key.funcPath}"></c:set>
                <c:set var="indexId" value="${fn:indexOf(path, 'explain=')}"></c:set>
                <c:if test="${indexId > 0}">
                    <c:set var="len" value="${fn:length(path)}"></c:set>
                    <c:set var="liId" value="${fn:substring(path,indexId+8,len)}"></c:set>
                </c:if>
                <c:if test="${indexId < 0}">
                    <c:set var="liId" value="dashboarddo"></c:set>
                </c:if>
                <li title="${funcMap.key.funcName}" id="${liId}">
                    <c:choose>
                        <c:when test="${fn:length(path) > 5}">
                            <c:set var="url" value="..${path}"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="url" value="${path}"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <a href="${url}">
                        <span class="menu_icon">
                            <c:if test="${! empty funcMap.key.icon}">
                                <i class="material-icons"><img src="..${funcMap.key.icon}"></i>
                            </c:if>
                        </span>
                        <span class="menu_title">${funcMap.key.funcName}</span>
                    </a>
                    <c:if test="${not empty funcMap.value}">
                        <ul>
                            <c:forEach var="funcMenu" items="${funcMap.value}">

                                <c:set var="son_path" value="${funcMenu.funcPath}"></c:set>
                                <c:set var="son_indexId" value="${fn:indexOf(son_path, 'explain=')}"></c:set>
                                <c:if test="${son_indexId > 0}">
                                    <c:set var="son_len" value="${fn:length(son_path)}"></c:set>
                                    <c:set var="son_liId" value="${fn:substring(son_path,son_indexId+8,son_len)}"></c:set>
                                </c:if>
                                <c:if test="${son_indexId < 0}">
                                    <c:set var="son_liId" value=""></c:set>
                                </c:if>
                                <li id="${son_liId}"><a href="..${funcMenu.funcPath}">${funcMenu.funcName}</a></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>

        </ul>
    </div>
</aside>
<!-- main sidebar end -->

<div id="page_content">
    <div id="page_content_inner">
        <sitemesh:body/>
        <%--<iframe id="frim" src=""></iframe>--%>
    </div>
</div>

<!-- common functions -->
<script src="../assets/js/uikit_custom.min.js" charset="GBK"></script>
<script src="../assets/js/altair_admin_common.min.js"></script>

<script>
    //目录高亮设置
    var str2 = '<%=request.getParameter("explain")%>';
    $('#'+ str2).addClass("act_item");
    $('#'+ str2).addClass("current_section");

    $(function () {
        // enable hires images
        altair_helpers.retina_images();
        // fastClick (touch devices)
        if (Modernizr.touch) {
            FastClick.attach(document.body);
        }

        $("#menus ul li a").click(function(e){
            $("#menus ul li").removeClass("current_section");
            $(this).parent().addClass("current_section");
        });
    });

    $(document).ready(function(){
        var arr = eval(${info.loginWebOthersSession});
        $.each( arr, function(index, content){
            $('#'+ content.corpCode).css("display", "");
            $('#'+ content.corpCode).attr('href', content.siteUrl);
        });
    });
</script>

</body>
</html>