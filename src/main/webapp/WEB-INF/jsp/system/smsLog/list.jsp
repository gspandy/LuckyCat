<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
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
</head>
<body>
<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm" name="searchForm">
        <div class="form-group">
            <label class="control-label">
                <span class="h4 font-thin v-middle">账号:</span>
            </label>
            <kendo:comboBox name="smsLogId"  filter="contains" placeholder="选择电话号码..." index="0" suggest="true" dataTextField="phone" dataValueField="id" style="width: 80%;">
                <kendo:dataSource data="${smsLogList}"></kendo:dataSource>
            </kendo:comboBox>
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
    </form>
</div>
</body>
</html>
