<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>CPU信息管理</title>
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
		<li class="active"><a href="${ctx}/device/cPU/">CPU信息列表</a></li>
		<shiro:hasPermission name="device:cPU:edit"><li><a href="${ctx}/device/cPU/form">CPU信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cPU" action="${ctx}/device/cPU/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>品牌or厂商：</label>
				<form:input path="brand" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>型号：</label>
				<form:input path="model" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>核心数量：</label>
				<form:input path="coresNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
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
				<th>型号</th>
				<th>核心数</th>
				<th>核心代号</th>
				<th>接口类型</th>
				<th>主频</th>
				<th>一级缓存</th>
				<th>二级缓存</th>
				<th>三级缓存</th>
				<th>制程工艺</th>
				<th>功率</th>
				<th>是否支持64位</th>
				<th>备注</th>
				<shiro:hasPermission name="device:cPU:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cPU">
			<tr>
				<td><a href="${ctx}/device/cPU/form?id=${cPU.id}">
					<fmt:formatDate value="${cPU.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${cPU.brand}
				</td>
				<td>
					${cPU.model}
				</td>
				<td>
					${cPU.coresNumber}
				</td>
				<td>
					${cPU.kernalcode}
				</td>
				<td>
					${cPU.interfaceType}
				</td>
				<td>
                    <!--主频 -->
					${cPU.basicFrequency}
				</td>
				<td>
					${cPU.l1Cache}
				</td>
				<td>
					${cPU.l2Cache}
				</td>
				<td>
					${cPU.l3Cache}
				</td>
				<td>
                    <!-- 制程工艺 -->
					${cPU.processTechnology}
				</td>
				<td>
					${cPU.power}
				</td>
				<td>
					${cPU.support64}
				</td>
				<td>
					${cPU.remarks}
				</td>
				<shiro:hasPermission name="device:cPU:edit"><td>
    				<a href="${ctx}/device/cPU/form?id=${cPU.id}">修改</a>
					<a href="${ctx}/device/cPU/delete?id=${cPU.id}" onclick="return confirmx('确认要删除该CPU信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>