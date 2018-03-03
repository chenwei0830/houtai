<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/org/">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/org/form?id=${org.id}">字典<shiro:hasPermission name="sys:org:edit">${not empty org.id?'修改':'添加'}</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="org" action="${ctx}/sys/org/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="orgName" htmlEscape="false" maxlength="255" class="required"/>
				<span class="help-inline"><font color="red">*</font>不超过50个字符</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构简介:</label>
			<div class="controls">
				<form:textarea path="orgInfo" htmlEscape="false" rows="3" maxlength="255" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用状态：</label>
			<div class="controls">
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font>初始默认为启用</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:org:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>