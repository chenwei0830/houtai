<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作品列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//查看评论列表
			$(".pl-list").click(function(){
				top.layer.open({
					title:['查看评论','background:#3daae9;color:#fff;'],
					type:2,
					maxmin: true,
					content: '${ctx}/art/artworks/',
					area:['900px','600px']
				});
			});
			
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
		<li class="active"><a href="${ctx}/art/artworks/">作品列表</a></li>
		<li><a href="${ctx}/art/artworks/form">作品${not empty artWorks.id?'编辑':'新增'}</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="artWorks" action="${ctx}/art/artworks/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<c:if test="${!artWorks.bePersonal}">
				<li><label>机构：</label>
					<form:select path="org.id" class="input-medium">
						<form:option value="" label="-请选择-"/>
						<form:options items="${fns:getOrgList()}" itemLabel="orgName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>标题：</label>
					<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium" cssStyle="width:80px;"/>
				</li>
				<li><label>作者：</label>
					<form:input path="user.nickName" htmlEscape="false" maxlength="200" class="input-medium" cssStyle="width:80px;"/>
				</li>
			</c:if>
			<li>
				<label>发布日期：</label>
				<input id="beginDate" name="beginDate" type="text" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${artWorks.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;--&nbsp;<input id="endDate" name="endDate" type="text" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${artWorks.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>作品分类：</label>
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
				<th>标题</th>
				<th>作品分类</th>
				<th>展示模式</th>
				<th>审核状态</th>
				<th>作者</th>
				<th>发布时间</th>
				<th>机构</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity" varStatus="i">
			<tr>
				<td>${i.index+1}</td>
				<td>
					<a href="${ctx}/art/artworks/view?id=${entity.id}">${fns:abbr(entity.title,50)}</a>
				</td>
				<td>${fns:getDictLabel(entity.artType, 'art_type', '')}</td>
				<td>${fns:getDictLabel(entity.modelType, 'art_model_type', '')}</td>
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
				<td>${entity.user.nickName}</td>
				<td>
					<fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${entity.org.orgName}</td>
				<td>
					<c:choose>
						<c:when test="${entity.status eq '0'}">
							<shiro:hasPermission name="art:artworks:edit">
								<a class="blue-btn" href="${ctx}/art/artworks/form?id=${entity.id}">修改</a>
								<a href="${ctx}/art/artworks/delete?id=${entity.id}" onclick="return confirmx('确认要删除该作品吗？', this.href)">删除</a>
							</shiro:hasPermission>
							<shiro:hasPermission name="art:artworks:check">
								<a class="blue-btn" href="${ctx}/art/artworks/form?id=${entity.id}">审核</a>
							</shiro:hasPermission>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/art/artworks/view?id=${entity.id}">查看</a>
							<a class="pl-list" title="待审核评论5条">评论<font color="red">(5)</font></a>
						</c:otherwise>
					</c:choose>
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