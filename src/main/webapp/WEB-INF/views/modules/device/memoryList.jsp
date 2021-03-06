<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内存信息管理</title>
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
		<li class="active"><a href="${ctx}/device/memory/">内存信息列表</a></li>
		<shiro:hasPermission name="device:memory:edit"><li><a href="${ctx}/device/memory/form">内存信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memory" action="${ctx}/device/memory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>SN号：</label>
                <form:input path="sn" htmlEscape="false" maxlength="100" class="input-medium"/>
            </li>
            <li><label>内存大小：</label>
                <form:input path="capacity" htmlEscape="false" maxlength="255" class="input-medium"/>
            </li>
            <li><label>品牌/厂商：</label>
                <form:input path="brand" htmlEscape="false" maxlength="255" class="input-medium"/>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>SN号</th>
				<th>品牌/厂商</th>
				<th>内存大小</th>
				<th>设备id</th>
				<th>remarks</th>
				<shiro:hasPermission name="device:memory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memory">
			<tr>
				<td><a href="${ctx}/device/memory/form?id=${memory.id}">
					${memory.sn}
				</a></td>
				<td>
					${memory.brand}
				</td>
				<td>
					${memory.capacity}
				</td>
				<td>
					${memory.deviceId}
				</td>
				<td>
					${memory.remarks}
				</td>
				<shiro:hasPermission name="device:memory:edit"><td>
    				<a href="${ctx}/device/memory/form?id=${memory.id}">修改</a>
					<a href="${ctx}/device/memory/delete?id=${memory.id}" onclick="return confirmx('确认要删除该内存信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>