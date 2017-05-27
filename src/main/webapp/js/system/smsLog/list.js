var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			width : "50px",
			hide : true
		}, {
			colkey : "phone",
			name : "手机号码"
		}, {
			colkey : "verify_code",
			name : "验证码"
		}, {
			colkey : "ip_address",
			name : "IP地址"
		}, {
			colkey : "status",
			name : "发送状态",
			renderData : function(rowindex, data, rowdata, column) {
				if(data=="0"){
					return "失败";
				}else if(data=="1"){
					return "成功";
				}
			}
		}, {
			colkey : "error_msg",
			name : "错误信息"
		}, {
			colkey : "ts",
			name : "操作时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/smsLog/findByPage.shtml',
		checkbox : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});
