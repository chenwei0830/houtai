<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//查看评论列表
			$("#sh-btn").click(function(){
				var id = $(this).data("id");
				top.layer.open({
					title:['查看评论','background:#3daae9;color:#fff;'],
					type:2,
					maxmin: true,
					content: ctx+'/art/comment/form?id='+id,
					area:['900px','600px'],
					btn:['确认', '取消'],
					yes: function(index, layero){
						var $body = top.layer.getChildFrame('body', index);
						$.ajax({
							url:ctx+'/art/comment/save',
							type:"POST",
							data: $body.find('#inputForm').serialize(),
							dataType:"text",
							success:function(data){
								if(data.code == 0){
									$("#searchForm").submit();
									top.layer.close(index);
								}else{
									top.layer.msg("审核失败");
								}
							},
							error:function(){
								top.layer.msg("系统错误");
							}
						})
				  	},
				  	btn2: function(index, layero){
				  		top.layer.close(index);
			  	  	},
			  	  	cancel: function(){
			  	  		top.layer.close(index);
			  	 	}
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
		<li class="active"><a href="${ctx}/art/comment/">评论列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="artWorksComment" action="${ctx}/art/comment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构：</label>
				<form:select path="org.id" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getOrgList()}" itemLabel="orgName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>作品标题：</label>
				<form:input path="artWorks.title" htmlEscape="false" maxlength="200" class="input-medium" cssStyle="width:80px;"/>
			</li>
			<li><label>评论人：</label>
				<form:input path="user.nickName" htmlEscape="false" maxlength="200" class="input-medium" cssStyle="width:80px;"/>
			</li>
			<li><label>作品分类：</label>
				<form:select path="artWorks.artType" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_release_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns" style="margin-left: 20px;">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input class="btn btn-primary" id="restBtn" type="button" onclick="location.href='${ctx}/art/comment/'" value="重置" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-hover table-bordered"> 
		<thead>
			<tr>
				<th>序号</th>
				<th>评论内容</th>
				<th>评论人</th>
				<th style="width: 60px;">审核状态</th>
				<th style="width: 70px;">评论时间</th>
				<th>所属作品</th>
				<th>作品分类</th>
				<th style="width: 70px;">机构</th>
				<th style="width: 60px;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity" varStatus="i">
			<tr>
				<td>${i.index+1}</td>
				<td>${entity.content}</td>
				<td>${entity.user.nickName}</td>
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
				<td><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${entity.artWorks.title}</td>
				<td>${fns:getDictLabel(entity.artWorks.artType, 'art_type', '')}</td>
				<td>${entity.org.orgName}</td>
				<td>
					<c:if test="${entity.status eq '0'}"><a id="sh-btn" title="审核操作" style="cursor: pointer;" data-id="${entity.id}">审核</a></c:if>
					<a href="${ctx}/art/comment/delete?id=${entity.id}" onclick="return confirmx('确认要删除该通知吗？', this.href)">删除</a>
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