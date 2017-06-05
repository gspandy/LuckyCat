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
	<title>考拉微店ERP管理系统</title>

	<link rel="stylesheet" href="${ctx}/bower_components/uikit/css/uikit.almost-flat.min.css" media="all">
	<!-- flag icons -->
	<link rel="stylesheet" href="${ctx}/assets/icons/flags/flags.min.css" media="all">
	<!-- altair admin -->
	<link rel="stylesheet" href="${ctx}/assets/css/main.min.css" media="all">
	<!-- matchMedia polyfill for testing media queries in JS -->
	<link href="${ctx}/kendo/styles/kendo.default.min.css" rel="stylesheet"/>
	<link href="${ctx}/kendo/styles/kendo.default.mobile.min.css" rel="stylesheet"/>
	<link href="${ctx}/kendo/styles/kendo.common.min.css" rel="stylesheet"/>
	<link href="${ctx}/kendo/styles/kendo.bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="${ctx}/assets/css/MyStyle.css" media="all">
	<link rel="stylesheet" href="${ctx}/css/easyui/easyui.css" >
	<link rel="stylesheet" href="${ctx}/css/login/jgmodal.css" />
	<!--[if lte IE 9]>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctx}/bower_components/matchMedia/matchMedia.js"></script>
	<script type="text/javascript" src="${ctx}/bower_components/matchMedia/matchMedia.addListener.js"></script>
	<![endif]-->
	<script src="${ctx}/assets/js/common.min.js"></script>
	<script src="${ctx}/js/common/StrUtil.js"></script>
	<script src="${ctx}/bower_components/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/assets/js/form_validator.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/kendo/js/kendo.web.min.js"></script>
	<script type="text/javascript" src="${ctx}/kendo/js/messages/kendo.messages.zh-CN.min.js"></script>
	<script type="text/javascript" src="${ctx}/kendo/js/cultures/kendo.culture.zh-CN.min.js"></script>
	<script type="text/javascript" src="${ctx}/kendo/js/kendo.all.min.js"></script>


	<script type="text/javascript" src="${ctx}/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx}/highcharts/modules/exporting.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/pages/components_notifications.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/js/application.common.js"></script>
	<script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
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
						<a href="#" class="user_action_icon"><i class="material-icons md-24 md-light">&#xE7F4;</i><span class="uk-badge">1</span></a>
						<div class="uk-dropdown uk-dropdown-xlarge uk-dropdown-flip">
							<div class="md-card-content">
								<ul class="uk-tab uk-tab-grid" data-uk-tab="{connect:'#header_alerts',animation:'slide-horizontal'}">
									<li class="uk-width-1-2 uk-active"><a href="#" class="js-uk-prevent uk-text-small">我的待办&nbsp;(1)</a></li>
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
														<span class="md-list-heading"><a href="../teachingAffairs/toAuditPage.do?explain=applicationdo">排课审核&nbsp;(1)</a></span>
													</div>
												</li>
											</c:if>
											<c:if test="${sessionScope.myTodos.pa}">
												<li>
													<div class="md-list-addon-element">
														<span class="md-user-letters md-bg-light-green">pa</span>
													</div>
													<div class="md-list-content">
														<span class="md-list-heading"><a href="../financials/refundRequestFromYjj.do?explain=refundRequestFromYjjdo">家长退费审核&nbsp;(1)</a></span>
													</div>
												</li>
											</c:if>
											<c:if test="${sessionScope.myTodos.di}">
												<li>
													<div class="md-list-addon-element">
														<span class="md-user-letters md-bg-light-green">di</span>
													</div>
													<div class="md-list-content">
														<span class="md-list-heading"><a href="../financials/refundRequestFromErp.do?explain=refundRequestFromErpdo">主任退费审核&nbsp;(1)</a></span>
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
							<img class="md-user-image" src="${ctx}/assets/img/avatars/avatar_11_tn.png"  alt=""/>
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
				<img src="${ctx}/assets/img/logo/logo1.png" alt=""style="height: 20px !important;"/>
				<span style="color:#fff;font-size: 14px; font-weight: bold;position: relative;top:5px;">ERP管理系统</span>
			</a>
			<a href="#" class="sSidebar_show">
				<img src="${ctx}/assets/img/logo_main_small.png" alt="" height="32"  width="32"/>
			</a>
		</div>
	</div>
	<div class="menu_section" id="menus">
		<ul>
			<c:forEach var="funcMap" items="${list}" varStatus="i">
				<c:set var="path" value="${funcMap.resUrl}"></c:set>
				<li title="${funcMap.name}" id="${i.index}">
					<a href="${path}">
                        <span class="menu_icon">
                            <c:if test="${! empty funcMap.icon}">
								<i class="material-icons"><img src="..${funcMap.icon}"></i>
							</c:if>
                        </span>
						<span class="menu_title">${funcMap.name}</span>
					</a>
					<c:if test="${not empty funcMap.children}">
						<ul>
							<c:forEach var="funcMenu" items="${funcMap.children}" varStatus="ci">
								<li id="${ci.index}"><a href="${ctx}${funcMenu.resUrl}">${funcMenu.name}</a></li>
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
		<%--<iframe id="frim" src=""></iframe>--%>
	</div>
</div>

<!-- common functions -->
<script src="${ctx}/assets/js/uikit_custom.min.js" charset="GBK"></script>
<script src="${ctx}/assets/js/altair_admin_common.min.js"></script>

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