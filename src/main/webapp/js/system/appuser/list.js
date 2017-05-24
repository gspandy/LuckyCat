var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide : true
		}, {
			colkey : "phone",
			name : "手机号",
			isSort:true
		}, {
			colkey : "nickname",
			name : "昵称",
			width: "auto",
			isSort:true
		}, {
			colkey : "sign",
			name : "签名"
		}, {
			colkey : "avatar",
			name : "头像",
			width : '90px'
		}, {
			colkey : "weixin_nickname",
			name : "微信昵称"
		}, {
			colkey : "weixin_headImgUrl",
			name : "微信头像URL",
		}, {
			colkey : "weixin_subscribed",
			name : "是否订阅公众号",
			renderData : function(rowindex, data, rowdata, column) {
				if(data=="0"){
					return "否";
				}else if(data=="1"){
					return "是";
				}
			}
		}, {
			colkey : "login_times",
			name : "登录次数"
		}, {
			colkey : "last_login_time",
			name : "上次登录时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "last_login_ip",
			name : "上次登录IP"
		}, {
			colkey : "created_at",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "updated_at",
			name : "更新时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		} ],
		jsonUrl : rootPath + '/appuser/findByPage.shtml',
		checkbox : true,
		width:"1900px",
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});