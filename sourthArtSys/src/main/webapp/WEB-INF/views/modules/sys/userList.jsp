<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>机构：</label>
				<form:select path="org.id" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getOrgList()}" itemLabel="orgName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label style="width: 120px;">登录名(微信号)：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium" cssStyle="width:80px;"/>
			</li>
			<li>
				<label>昵&nbsp;&nbsp;&nbsp;称：</label><form:input path="nickName" htmlEscape="false" maxlength="50" class="input-medium" cssStyle="width:80px;"/>
			</li>
			<li><label>用户类别：</label>
				<form:select path="userType" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" style="margin-left: 20px;"  type="submit" value="查询" onclick="return page();"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 40px;">头像</th>
				<th>登录名/手机号</th>
				<th>昵称</th>
				<th>手机</th>
				<th>用户类型</th>
				<th>机构</th>
				<th>是否允许登录</th>
				<th>最近登录</th>
				<shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td style="text-align: center;"><img src="${user.photo}" style="height: 40px;width: 40px;border-radius: 50%;"/></td>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.nickName}</td>
				<td>${user.phone}</td>
				<td>${fns:getDictLabel(user.userType, 'sys_user_type', '')}</td>
				<td>${user.org.orgName}</td>
				<td>${fns:getDictLabel(user.loginFlag, 'yes_no', '')}</td>
				<td><fmt:formatDate value="${user.loginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="sys:user:edit"><td>
    				<a href="${ctx}/sys/user/form?id=${user.id}">修改</a>
					<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>