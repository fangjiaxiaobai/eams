<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/device/device/">设备信息列表</a></li>
		<shiro:hasPermission name="device:device:edit"><li><a href="${ctx}/device/device/form">设备信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="device" action="${ctx}/device/device/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>品牌/厂商：</label>
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>ip：</label>
				<form:input path="ip" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否整套：</label>
				<form:input path="completed" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>具体位置信息：</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>品牌/厂商</th>
				<th>实验室名称</th>
				<th>具体位置</th>
				<th>Ip地址</th>
				<th>是否整套</th>
				<th>启用时间</th>
				<th>负责人</th>
				<th>备注</th>
				<shiro:hasPermission name="device:device:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="device">
			<tr>
				<td><a href="${ctx}/device/device/form?id=${device.id}">
					<fmt:formatDate value="${device.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${device.remarks}
				</td>
				<shiro:hasPermission name="device:device:edit"><td>
    				<a href="${ctx}/device/device/form?id=${device.id}">修改</a>
					<a href="${ctx}/device/device/delete?id=${device.id}" onclick="return confirmx('确认要删除该设备信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>