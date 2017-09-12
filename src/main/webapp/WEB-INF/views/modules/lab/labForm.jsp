<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实验室/教室/办公室管理</title>
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

            $("#principal").select2({
                placeholder:"请选择",//文本框的提示信息
                minimumInputLength:1,   //至少输入n个字符，才去加载数据
//                dropdownParent: $("#inputForm"), //指定父元素
                multiple: true,
                theme: "bootstrap", // 设置主题为bootsharp的主题
                ajax:{
                    url:'${ctx}/sys/user/getAllUserList',
                    dataType:"json",
                    quietMillis:250,
                    method:'post',
                    data: function (params) {
                        return {
                            search: params,
                        };
                    },
                    results: function (data) {
                        return {
                            results: data,
                        };
                    },
                    cache: true,
                },
                formatInputTooShort: function (input, min) {
                    return "请输入姓名或者邮箱";
                },
                // 选中回调
                formatSelection  : function (repo) {
                    $("#principal").val(repo.id);
                    return repo.text;
                }
                // 初始化
                ,initSelection : function (element, callback) {
                    var id = $(element).val();
                    if ($(element).val()!=="") {
                        $.ajax("${ctx}/sys/user/getUserByIdForNew", {
                            data: {
                                id: id
                            },
                            dataType: "json"
                        }).done(function(data) {
                            var data = {id: data.id, text: data.text};
                            callback(data);
                        });
                    }
                },
                formatResult:function(data){ //搜索框中的显示
                    return '<div class="select2-user-result">' + data.name +"("+data.phone+")"+ '</div>'
                }
            });

		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lab/">实验室/教室/办公室列表</a></li>
		<li class="active"><a href="${ctx}/lab/form?id=${lab.id}">实验室/教室/办公室<shiro:hasPermission name="lab:lab:edit">${not empty lab.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lab:lab:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lab" action="${ctx}/lab/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
        <table width="100%">
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">实验室名称：</label>
                        <div class="controls">
                            <form:input path="labName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
                            <span class="help-inline"><font color="red">*</font> </span>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">实验室门牌号：</label>
                        <div class="controls">
                            <form:input path="labId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
                            <span class="help-inline"><font color="red">*</font> </span>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">负责人：</label>
                        <div class="controls">
                            <%--<form:input path="labPrincipal" htmlEscape="false" maxlength="64" class="input-xlarge select2" id="principal"/>--%>
                            <input name="labPrincipal" htmlEscape="false" maxlength="64" class="input-xlarge" id="principal" value="${lab.labPrincipal}"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">带宽：</label>
                        <div class="controls">
                            <form:input path="labBandWidthToal" htmlEscape="false" maxlength="11" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">实验室地址：</label>
                        <div class="controls">
                            <form:input path="labAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">实验室类型：</label>
                        <div class="controls">
                            <form:input path="labType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">起始ip：</label>
                        <div class="controls">
                            <form:input path="labStartIp" htmlEscape="false" maxlength="32" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">结束ip：</label>
                        <div class="controls">
                            <form:input path="labEndIp" htmlEscape="false" maxlength="32" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">启用时间：</label>
                        <div class="controls">
                            <input name="labBirthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="<fmt:formatDate value="${lab.labBirthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">到期时间：</label>
                        <div class="controls">
                            <input name="labExpireday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="<fmt:formatDate value="${lab.labExpireday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">应有计算机数：</label>
                        <div class="controls">
                            <form:input path="labComputerCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">应有课桌数量：</label>
                        <div class="controls">
                            <form:input path="labDeskCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">机柜数量：</label>
                        <div class="controls">
                            <form:input path="labCabinetCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="control-group">
                        <label class="control-label">备注：</label>
                        <div class="controls">
                            <form:textarea path="remarks" htmlEscape="false" rows="1" maxlength="255" class="input-xxlarge "/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <div class="form-actions">
                        <shiro:hasPermission name="lab:lab:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                    </div>
                </td>
            </tr>
        </table>

	</form:form>
</body>
</html>