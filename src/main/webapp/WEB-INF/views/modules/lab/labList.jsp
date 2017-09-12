<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实验室/教室/办公室管理</title>
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
		<li class="active"><a href="${ctx}/lab/">实验室/教室/办公室列表</a></li>
		<shiro:hasPermission name="lab:lab:edit"><li><a href="${ctx}/lab/form">实验室/教室/办公室添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lab" action="${ctx}/lab/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>实验室名称：</label>
				<form:input path="labName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>门牌号：</label>
				<form:input path="labId" htmlEscape="false" maxlength="32" class="input-small"/>
			</li>
			<li><label>负责人：</label>
				<form:input path="labPrincipal" htmlEscape="false" class="input-small"/>
			</li>
			<li><label>实验室类型：</label>
				<form:input path="labType" htmlEscape="false" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实验室名称</th>
				<th>实验室门牌号</th>
				<th>负责人</th>
				<th>带宽</th>
				<th>机柜数量</th>
				<th>启用时间</th>
				<th>到期时间</th>
				<th>应有计算机数量</th>
				<th>实验室类型</th>
				<th>应有课桌数量</th>
				<th>起始ip</th>
				<th>结束ip</th>
				<th>备注</th>
				<shiro:hasPermission name="lab:lab:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lab">
			<tr>
				<td><a href="${ctx}/lab/lab/form?id=${lab.id}">
					${lab.labName}
				</a></td>
				<td>
					${lab.labId}
				</td>
				<td>
					${lab.user.name}
				</td>
				<td>
					${lab.labBandWidthToal}
				</td>
				<td>
					${lab.labCabinetCount}
				</td>
				<td>
					<fmt:formatDate value="${lab.labBirthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lab.labExpireday}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lab.labComputerCount}
				</td>
				<td>
					${lab.labType}
				</td>
				<td>
					${lab.labDeskCount}
				</td>
				<td>
					${lab.labStartIp}
				</td>
				<td>
					${lab.labEndIp}
				</td>
				<td>
					${lab.remarks}
				</td>
				<shiro:hasPermission name="lab:lab:edit"><td>
    				<a href="${ctx}/lab/form?id=${lab.id}">修改</a>
					<a href="${ctx}/lab/delete?id=${lab.id}" onclick="return confirmx('确认要删除该实验室/教室/办公室吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>