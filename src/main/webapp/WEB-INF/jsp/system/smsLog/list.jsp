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
    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="680" style="border-width:0px;">
        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5"/>
        <kendo:grid-filterable extra="true">
            <kendo:grid-filterable-messages filter="查询" clear="清除" info="提示" and="并且" or="或者"/>
            <kendo:grid-filterable-operators>
                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                <kendo:grid-filterable-operators-number eq="=" lte="<=" gte=">="/>
                <kendo:grid-filterable-operators-date eq="=" lte="早于" gte="晚于"/>
                <kendo:grid-filterable-operators-enums eq="等于"/>
            </kendo:grid-filterable-operators>
        </kendo:grid-filterable>
        <kendo:grid-columns>
            <kendo:grid-column title="电话号码" field="phone" width="100px"/>
            <kendo:grid-column title="验证码" field="verify_code" width="100px"/>
            <kendo:grid-column title="请求ip" field="ip_address" width="90px"/>
            <kendo:grid-column title="发送时间" field="ts" width="90px"/>
            <kendo:grid-column title="状态" field="status" width="110px"/>
        </kendo:grid-columns>
        <kendo:dataSource pageSize="20" serverPaging="true" serverFiltering="true" serverSorting="true">
            <kendo:dataSource-schema data="content" total="totalElements">
            </kendo:dataSource-schema>
            <kendo:dataSource-transport>
                <kendo:dataSource-transport-read url="smsLog/findByPage.shtml" type="POST" contentType="application/json"/>
                <kendo:dataSource-transport-parameterMap>
                    <script>
                        function parameterMap(options, type) {
                            return JSON.stringify(options);
                        }
                    </script>
                </kendo:dataSource-transport-parameterMap>
            </kendo:dataSource-transport>
        </kendo:dataSource>
    </kendo:grid>
</div>
</body>
</html>
