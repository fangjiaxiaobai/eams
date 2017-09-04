<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>硬盘信息管理</title>
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
		<li class="active"><a href="${ctx}/device/hardDriver/">硬盘信息列表</a></li>
		<shiro:hasPermission name="device:hardDriver:edit"><li><a href="${ctx}/device/hardDriver/form">硬盘信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hardDriver" action="${ctx}/device/hardDriver/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>品牌/厂商：</label>
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>硬盘大小：</label>
				<form:input path="capacity" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>硬盘SN号：</label>
				<form:input path="sn" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>使用状态</th>
				<th>录入时间</th>
				<th>备注</th>
				<shiro:hasPermission name="device:hardDriver:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hardDriver">
			<tr>
                <td>
                        ${hardDriver.sn}
                </td>
                <td>
                        ${fns:getDictLabel(hardDriver.delFlag,'use_flag',' ')}
                </td>
				<td>
					<fmt:formatDate value="${hardDriver.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${hardDriver.remarks}
				</td>
				<shiro:hasPermission name="device:hardDriver:edit"><td>
    				<a href="${ctx}/device/hardDriver/form?id=${hardDriver.id}">修改</a>
					<a href="${ctx}/device/hardDriver/delete?id=${hardDriver.id}" onclick="return confirmx('确认要删除该硬盘信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>