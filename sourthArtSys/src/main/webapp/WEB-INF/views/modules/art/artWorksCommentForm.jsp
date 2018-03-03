<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="artWorksComment" action="${ctx}/oa/comment/save" method="post" class="form-horizontal" cssStyle="margin-top:20px;">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">评论内容：</label>
			<div class="controls">${artWorksComment.content}</div>
		</div>	
		<div class="control-group">
			<label class="control-label">评论人：</label>
			<div class="controls">${artWorksComment.user.nickName}</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论时间：</label>
			<div class="controls"><fmt:formatDate value="${artWorksComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		</div>
		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<input type="radio" value="1" name="status" checked="checked">通过
				<input type="radio" value="2" name="status" style="margin-left: 20px;">不通过
				<span class="help-inline"><font color="red">*</font> 审核后不能进行修改。</span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">审核备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
			</div>
		</div>
	</form:form>
</body>
</html>