<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			$('.carousel').carousel({
			  interval: false
			});
			
			//审核提交
			$("#btnSubmit").click(function(){
				//判断是否选中了审核
				$("#status-help").text("*");
				var status = $("input[name='status']:checked").val();
				if(typeof(status)!="undefined"){
					$("#inputForm").submit();
				}else{
					$("#status-help").text("*请选择审核结果");
				}
			});
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/art/artworks/">作品列表</a></li>
		<li class="active"><a href="${ctx}/art/artworks/view?id=${artWorks.id}">作品${artWorks.status eq '0'?'审核':'查看'}</a></li>
	</ul>
	<div class="container" style="background: #f5f5f5;">
		<!-- 标题 -->
		<div>
			<h4 style="text-align:center; color: #1d6ba2;font-size:20px;margin: 85px auto 0;letter-spacing:2px;">${artWorks.title}</h4>
			<p style="margin:22px auto 6px;text-align:right;font-size:14px;color:#333;overflow:hidden;font-weight:normal;padding-right:20px;font-family:宋体;">作者：${artWorks.user.nickName}&nbsp;&nbsp;&nbsp;发布日期：<fmt:formatDate value="${artWorks.updateDate}" pattern="yyyy/MM/dd"/></p>
		</div>
		<!-- 正文 -->
		<!-- 图文 -->
		<div style="margin: 10px;">
			<!-- 图文 视频文 -->
			<c:choose>
				<c:when test="${artWorks.modelType eq '0' }">
					<!-- 图片轮播 -->
					<div>
						<div id="myCarousel" class="carousel slide">
							<ol class="carousel-indicators">
								<c:forEach items="${artWorks.imgList}" var="e" varStatus="i">
									<li data-target="#myCarousel" data-slide-to="${i.index}" class="${i.index==0?'active':''}"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${artWorks.imgList}" var="e" varStatus="i">
									<div class="item ${i.index==0?'active':''}">
										<img style="height: 400px;width: 100%;" src="${e}" alt="">
										<!-- <div class="carousel-caption">
											<h4></h4>
											<p></p>
										</div> -->
									</div>
								</c:forEach>
							</div>
							<a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
							<a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
						</div>
					</div>
				</c:when>
				<c:when test="${artWorks.modelType eq '1'}">
					<div>
						<div id="myCarousel" class="carousel slide">
							<div class="carousel-inner">
								<c:forEach items="${artWorks.videoList}" var="e" varStatus="i">
									<div class="item">
										<video src="${e}" />
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			<!-- 内容描述 -->
			<div style="margin-bottom: 10px;">
				<div style="text-indent: 2em;font-size: 16px;">${artWorks.textContent}</div>
			</div>
			<!-- 审核 -->
			<form:form id="inputForm" modelAttribute="artWorks" action="${ctx}/art/artworks/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<div class="control-group" style="color: red;">
					<label class="control-label">认证审核：</label>
					<div class="controls">
						<form:radiobuttons path="status" items="${fns:getDictList('check_status')}" itemLabel="label" itemValue="value" disabled="${not empty artWorks.id && artWorks.status eq '0'?false:true}"  htmlEscape="false" class="required"/>
						<span class="help-inline"><font color="red" id="status-help">*</font></span>
					</div>
				</div> 
				<div class="control-group" style="color: red;">
					<label class="control-label">审核备注：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required" readonly="${not empty artWorks.id && artWorks.status eq '0'?false:true}" placeholder="填写审核意见"/>
					</div>
				</div>
				<div class="form-actions">
					<c:if test="${not empty artWorks.id && artWorks.status eq '0'}">
						<input id="btnSubmit" class="btn btn-warning" type="button" value="提交"/>&nbsp;
					</c:if>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
			</form:form>
			<!-- 评论 -->
			<!-- <div style="margin-top: 20px;">
				<div style="border-left: 6px solid #2fa4e7;">
					<h5>评论(50)</h5>
				</div>
				<br>
				<div style="width: 100%;">
					<textarea rows="4" style="width: 90%; resize: none;"></textarea>
					<button class="btn btn-danger">提交</button>
				</div>
				<div class="bs-docs-example">
					<div class="media">
						<a class="pull-left"> <img class="media-object" alt="64x64"
							src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg"
							style="width: 64px; height: 64px;">
						</a>
						<div class="media-body">
							<h5 class="media-heading">风一样的男人</h5>
							Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
							scelerisque ante sollicitudin commodo. Cras purus odio,
							vestibulum in vulputate at, tempus viverra turpis. Fusce
							condimentum nunc ac nisi vulputate fringilla. Donec lacinia
							congue felis in faucibus.
						</div>
					</div>
					<div class="media">
						<a class="pull-left"> <img class="media-object" alt="64x64"
							src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg"
							style="width: 64px; height: 64px;">
						</a>
						<div class="media-body">
							<h5 class="media-heading">Media heading</h5>
							Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
							scelerisque ante sollicitudin commodo. Cras purus odio,
							vestibulum in vulputate at, tempus viverra turpis. Fusce
							condimentum nunc ac nisi vulputate fringilla. Donec lacinia
							congue felis in faucibus.
							<div class="media">
								<a class="pull-left"> <img class="media-object" alt="64x64"
									src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg"
									style="width: 64px; height: 64px;">
								</a>
								<div class="media-body">
									<h5 class="media-heading">Media heading</h5>
									Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
									scelerisque ante sollicitudin commodo. Cras purus odio,
									vestibulum in vulputate at, tempus viverra turpis. Fusce
									condimentum nunc ac nisi vulputate fringilla. Donec lacinia
									congue felis in faucibus.
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> -->
		</div>
	</div>
</body>
</html>