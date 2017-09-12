<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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

            $("#labId").select2({
                placeholder:"请选择实验室",//文本框的提示信息
                minimumInputLength:1,   //至少输入n个字符，才去加载数据
                ajax:{
                    url:'${ctx}/lab/getLabByNameOrLabId',
                    dataType:"json",
                    quietMillis:250,
                    delay:250,
                    type:'post',
                    data: function (params) {
                        debugger;
                        return {
                            labName: params,
                        };
                    },
                    results: function (data) {
                        return {
                            results: data
                        };
                    },
                    cache: true,
                },
                formatInputTooShort: function (input, min) {
                    /*var n = min - input.length;
                     return "请再输入" + n + "个字符";*/
                    return "请输入实验室名或者门牌号";
                },
                // 选中回调
                formatSelection  : function (repo) {
                    $("#labId").val(repo.id);
                    return repo.text;
                },
                // 初始化
                initSelection : function (element, callback) {
                    var id = $(element).val();
                    if ($(element).val()!=="") {
                        $.ajax("${ctx}/lab/getLabByIdToJson", {
                            data: {
                                id: id
                            },
                            dataType: "json"
                        }).done(function(data) {
                            var data = {id: data.id, text: data.text};
                            callback(data);
                        });
                    }
                }
            });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/device/device/">设备信息列表</a></li>
		<li class="active"><a href="${ctx}/device/device/form?id=${device.id}">设备信息<shiro:hasPermission name="device:device:edit">${not empty device.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="device:device:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="device" action="${ctx}/device/device/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">品牌/厂商：</label>
			<div class="controls">
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实验室名称：</label>
			<div class="controls">
                <input name="labId" htmlEscape="false" class="input-xlarge" id="labId" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ip地址：</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否完整：</label>
			<div class="controls">
                <form:select path="completed" htmlEscape="false" maxlength="1" class="input-xlarge">
                    <form:option value="" label="" />
                    <form:options itemValue="value" itemLabel="label" items="${fns:getDictList('yes_no')}" />
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用时间：</label>
			<div class="controls">
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${device.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体位置信息：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="device:device:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>