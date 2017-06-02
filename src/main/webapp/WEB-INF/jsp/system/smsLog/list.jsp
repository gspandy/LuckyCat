<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>

<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/easyui/locale/easyui-lang-zh_CN.js"></script>

<div class="m-b-md">
    <form class="form-inline" role="form" id="searchForm" name="searchForm">
        <div class="form-group">
            <label class="control-label">
                <span class="h4 font-thin v-middle">电话号码:</span>
            </label>
            <input class="input-medium ui-autocomplete-input" id="phone" name="userFormMap.phone">
            <label class="control-label">
                <span class="h4 font-thin v-middle">验证码:</span>
            </label>
            <input class="input-medium ui-autocomplete-input" id="verify_code" name="userFormMap.verify_code">
        </div>
        <a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
    </form>
    <table id="grid" class="easyui-datagrid" url="smsLog/findByPage.shtml" style="height: 800px;"
           idField="id" rownumbers="true" striped="true" fitColumns="true"
           fit="true" pagination="true" toolbar="#toolbar">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="200"></th>
            <th field="phone" width="200">手机号码</th>
            <th field="verify_code" width="100">验证码</th>
            <th field="ip_address" width="300">请求IP地址</th>
            <th field="ts"  width="200"> 时间</th>
        </tr>
        </thead>
    </table>
</div>

<script>
</script>