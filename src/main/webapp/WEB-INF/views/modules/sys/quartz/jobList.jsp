<%--
  Created by IntelliJ IDEA.
  User: 方小白
  Date: 2017/8/29
  Time: 11:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>定时任务管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        //暂停任务
        function pauseJob(jobName,jobGroupName){
            $.post("${ctx}/sys/quartz/pauseJob",{"jobName":jobName,"jobGroup":jobGroupName},function(data){
                if(data.status = 'success'){
                    window.location.href = window.location.href;
                }else{
                    alert("操作失败，请刷新重新！");
                }
            });
        }

        //恢复任务
        function resumeJob(jobName,jobGroupName){
            $.post("${ctx}/sys/quartz/resumeJob",{"jobName":jobName,"jobGroup":jobGroupName},function(data){
                if(data.status = 'success'){
                    window.location.href = window.location.href;
                }else{
                    alert("操作失败，请刷新重新！");
                }
            });
        }
        //删除
        function deleteJob(jobName,jobGroupName,triggerName,triggerGroupName){
            $.post("${ctx}/sys/quartz/deleteJob",{"jobName":jobName,"jobGroup":jobGroupName,"triggerName":triggerName,"triggerGroup":triggerGroupName},
                function(data){
                    if(data.status = 'success'){
                        window.location.href = window.location.href;
                    }else{
                        alert("操作失败，请刷新重新！");
                    }
                });
        }

        // 手动执行一次任务
        function executeJob(jobName,jobGroupName,triggerName,triggerGroupName){
            alert("executeJob");
            $.post("${ctx}/sys/quartz/executeJob",{"jobName":jobName,"jobGroup":jobGroupName,"triggerName":triggerName,"triggerGroup":triggerGroupName},
                function(data){
                    if(data.status = 'success'){
                        alert("执行成功")
                    }else{
                        alert("操作失败，请刷新重新！");
                    }
                });
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/quartz/">定时任务列表</a></li>
    <shiro:hasPermission name="sys:quartz:edit"><li><a href="${ctx}/sys/quartz/form?sort=10">定时任务添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="jobEntity" action="${ctx}/sys/quartz/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <%--<label>类型：</label>--%>
    <%--<form:select id="type" path="type" class="input-medium">--%>
        <%--<form:option value="" label=""/>--%>
        <%--<form:options items="${typeList}" htmlEscape="false"/>--%>
    <%--</form:select>--%>
    &nbsp;&nbsp;<label>任务名称 ：</label><form:input path="jobName" htmlEscape="false" maxlength="50" class="input-medium"/>
    &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
        <tr>
            <th>任务组</th>
            <th>任务名</th>
            <th>开始时间</th>
            <th>cron表达式</th>
            <th>上一次执行时间</th>
            <th>下次执行时间</th>
            <th>运行状态</th>
            <th>任务类</th>
            <th>运行次数</th>
            <shiro:hasPermission name="sys:quartz:edit"><th>操作</th></shiro:hasPermission></tr></thead>
    <tbody>
    <c:forEach items="${jobInfos}" var="jobInfo">
        <tr>
            <td>${jobInfo.jobGroup}</td>
            <td><a href="${ctx}/sys/quartz/form?id=${quartz.id}">${jobInfo.jobName}</a></td>
            <td><fmt:formatDate value="${jobInfo.startTime}" type="both"/></td>
            <td>${jobInfo.cron}</td>
            <td><fmt:formatDate value="${jobInfo.previousFireTime}" type="both"/></td>
            <td><fmt:formatDate value="${jobInfo.nextFireTime}" type="both"/></td>
            <td>${jobInfo.jobStatus}</td>
            <td>${jobInfo.jobClass}</td>
            <td>${jobInfo.count}</td>
            <shiro:hasPermission name="sys:quartz:edit">
            <td>
                <%--<a href="${ctx}/sys/quartz/form?id=${quartz.id}">修改</a>--%>
                <a href="javaScript:void(0);" onclick="pauseJob('${jobInfo.jobName}','${jobInfo.jobGroup}');">暂停</a>
                <a href="javaScript:void(0);" onclick="resumeJob('${jobInfo.jobName}','${jobInfo.jobGroup}');">恢复运行</a>
                <a href="javaScript:void(0);" onclick="executeJob('${jobInfo.jobName}','${jobInfo.jobGroup}','${jobInfo.triggerName}','${jobInfo.triggerGroup}');">手动运行</a>
                <a href="javaScript:void(0);" onclick="deleteJob('${jobInfo.jobName}','${jobInfo.jobGroup}','${jobInfo.triggerName}','${jobInfo.triggerGroup}');">删除</a>
            </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
