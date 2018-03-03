<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>认证列表</title>
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
		<li class="active"><a href="${ctx}/art/auth/">认证列表</a></li>
		<li><a href="${ctx}/art/auth/form">认证新增</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="artAuth" action="${ctx}/art/auth/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构：</label>
				<form:select path="org.id" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getOrgList()}" itemLabel="orgName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_release_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>认证申请人：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium" cssStyle="width:80px;"/>
			</li>
			<li>
				<label>申请日期：</label>
				<input id="beginDate" name="beginDate" type="text" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${artWorks.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--&nbsp;<input id="endDate" name="endDate" type="text" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${artWorks.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>艺术分类：</label>
				<form:select path="artType" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns" style="margin-left: 20px;">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input class="btn btn-primary" id="restBtn" type="button" onclick="location.href='${ctx}/art/artworks/'" value="重置" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-hover table-bordered"> 
		<thead>
			<tr>
				<th>序号</th>
				<th>认证申请人</th>
				<th>身份证号</th>
				<th>手机号</th>
				<th>艺术分类</th>
				<th>艺术级别</th>
				<th>审核状态</th>
				<th>申请时间</th>
				<th>机构</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity" varStatus="i">
			<tr>
				<td>${i.index+1}</td>
				<td>
					<a href="${ctx}/art/auth/form?id=${entity.id}">${entity.name}</a>
				</td>
				<td>${entity.idCard}</td>
				<td>${entity.phone}</td>
				<td>${fns:getDictLabel(entity.artType, 'art_type', '')}</td>
				<td>${fns:getDictLabel(entity.artLevel, 'art_type', '')}</td>
				<td>
					<c:choose>
						<c:when test="${entity.status eq '0'}">
							<font color="red">待审核</font>
						</c:when>
						<c:when test="${entity.status eq '1'}">
							<font color="blue">审核通过</font>
						</c:when>
						<c:otherwise><font color="#ff15cd">审核不通过</font></c:otherwise>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${entity.org.orgName}</td>
				<td>
					<a href="${ctx}/art/auth/delete?id=${entity.id}" onclick="return confirmx('确认要删除该认证申请吗？', this.href)">删除</a>
					<a class="blue-btn" href="${ctx}/art/auth/form?id=${entity.id}">${entity.status eq '0'?'审核':'查看'}</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagingBox">
		<div class="pagination">${page}</div>
	</div>	
</body>
</html>