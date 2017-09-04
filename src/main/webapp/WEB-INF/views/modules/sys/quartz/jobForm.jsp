<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});


			$("#jobClass").blur(function(){
                $.post("${ctx}/sys/quartz/jobClassIsExist", {"clazz":$("#jobClass").val()},function(data){
                    if(data.status = 'success'){
                        var $methods = data.methods;
                        var $options = '';
                        if(null!=$methods && ''!=$methods){
                            for(var i in $methods){
                                $options= $options + "<option value=\""+$methods[i]+"\">"+$methods[i]+"</option>";
                            }
                            $("#jobMethod").html($options);
                        }
                    }else{
                        alert("任务类不存在，请重新填写");
                    }
                });
            });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/quartz/">定时任务列表</a></li>
		<li class="active"><a href="form?id=">定时任务<shiro:hasPermission name="sys:quartz:edit">${not empty quartz.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:quartz:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="jobEntity" action="${ctx}/sys/quartz/save" method="post" class="form-horizontal">
		<%--<form:hidden path="id"/>--%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">任务名:</label>
			<div class="controls">
                <form:input path="jobName" htmlEscape="false" maxlength="50" class="required"/>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务组:</label>
			<div class="controls">
				<form:input path="jobGroup" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">触发器名:</label>
			<div class="controls">
				<form:input path="triggerName" htmlEscape="false" maxlength="50"/>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">触发器组名:</label>
			<div class="controls">
                <form:input path="triggerGroup" htmlEscape="false" maxlength="50"/>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
        <div class="control-group">
			<label class="control-label">任务类:</label>
			<div class="controls">
                <form:input path="jobClass" htmlEscape="false" maxlength="50" id="jobClass"  />
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
        <div class="control-group">
			<label class="control-label">目标方法:</label>
			<div class="controls">
                <%--<form:input path="jobMethod" htmlEscape="false" maxlength="50" id="jobMethod"/>--%>
                <form:select path="jobMethod" htmlEscape="false" maxLength="50" id="jobMethod" >
                    <option value="methods">methods</option>

                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
        <div class="control-group">
			<label class="control-label">cron表达式:</label>
			<div class="controls">
                <form:input path="cron" htmlEscape="false" maxlength="50"/>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:quartz:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>