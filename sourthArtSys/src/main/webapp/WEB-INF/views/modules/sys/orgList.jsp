<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		<li class="active"><a href="${ctx}/sys/org/">机构列表</a></li>
		<shiro:hasPermission name="sys:dict:edit"><li><a href="${ctx}/sys/org/form">机构${not empty org.id?'编辑':'添加'}</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="org" action="${ctx}/sys/org/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label style="width: 120px;">机构名称：</label><form:input path="orgName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>使用状态：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" style="margin-left: 20px;" type="submit" value="查询" onclick="return page();"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机构名称</th>
				<th>机构代码</th>
				<th>机构简介</th>
				<th>创建日期</th>
				<th>状态</th>
				<shiro:hasPermission name="sys:org:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="e">
			<tr>
				<td><a href="${ctx}/sys/org/form?id=${e.id}">${e.orgName}</a></td>
				<td>${e.orgCode}</td>
				<td>${e.orgInfo}</td>
				<td><fmt:formatDate value="${e.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>${fns:getDictLabel(e.delFlag, 'del_flag', '')}</td>
				<shiro:hasPermission name="sys:org:edit">
					<td>
	    				<a href="${ctx}/sys/org/form?id=${e.id}">修改</a>
						<a href="${ctx}/sys/org/delete?id=${e.id}">${e.delFlag eq '0'?'关闭':'启用'}</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>