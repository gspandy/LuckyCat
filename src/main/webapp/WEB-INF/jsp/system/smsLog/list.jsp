<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!doctype html>
<html class="lte-ie9" lang="en">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no"/>
    <title>erp</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm" name="searchForm">
        <div class="form-group">
            <label class="control-label">
                <span class="h4 font-thin v-middle">账号:</span>
            </label>
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
    </form>
    <table id="grid" class="easyui-datagrid" url="smsLog/findByPage.shtml"
           idField="id" rownumbers="true" striped="true" fitColumns="true"
           fit="true" pagination="true" singleSelect="true" toolbar="#toolbar">
        <thead>
        <tr>
            <th field="phone" width="200">电话</th>
            <th field="verify_code" width="400"> 验证码</th>
            <th field="ip_address" width="60">请求IP地址</th>
            <th field="ts" width="60"> 时间</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
