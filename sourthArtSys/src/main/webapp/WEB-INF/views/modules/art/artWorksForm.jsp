<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/modules/artworks/js/artworks.js?${randomTimes}"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/art/artworks/">作品列表</a></li>
		<li class="active"><a href="${ctx}/art/artworks/form">作品<shiro:hasPermission name="art:artworks:edit">${not empty artWorks.id?'修改':'新增'}</shiro:hasPermission></a></li>
	</ul><br/>
	<div class="edit-container">
		<!-- 中间内容 -->
		<div class="mid-content">
			<div style="padding-top: 20px;margin-bottom: 100px;">
				<!-- 标题 -->
				<div class="control-group">
					<div class="controls">
						<div contentEditable="true" style="text-align: center;font-size: 16px;" id="art-title">请输入标题</div>
					</div>
				</div>
				<!-- 内容板块 -->
				<div style="margin-top: 20px;" id="content-box">
					<div contentEditable="true" style="font-size: 14px;text-indent:2em;" class="art-content" data-type="text" >请输入正文</div>
				</div>
			</div>
		</div>
		
		<!-- 右侧多媒体 -->
		<div class="right-banner">
			<table class="table">
				<thead><tr><th>多媒体</th></tr></thead>
				<tbody>
					<tr class="tool-btn" data-type="pic">
						<td style="cursor: pointer;">
							<i class="icon-picture"></i>&nbsp;&nbsp;&nbsp;&nbsp;图片
							<input type="file" class="tool-file" accept="image/*" style="width: 160px;" id="pic-selected"/>
						</td>
					</tr>
					<tr class="tool-btn" data-type="video">
						<td style="cursor: pointer;">
							<i class="icon-facetime-video"></i>&nbsp;&nbsp;&nbsp;&nbsp;视频
							<input type="file" class="tool-file" accept="video/*" style="width: 160px;" id="video-selected"/>
						</td>
					</tr>
					<tr class="tool-btn" data-type="music">
						<td style="cursor: pointer;">
							<i class="icon-music"></i>&nbsp;&nbsp;&nbsp;&nbsp;背景音乐
							<input type="file" class="tool-file" accept="audio/*" style="width: 160px;" id="music-selected"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 底部 -->
		<div class="foot-banner">
			<div style="margin-right: 200px;margin-top: 5px;float: right;">
				<a class="btn btn-primary" href="">保存</a><a class="btn" href="" style="margin-left: 20px;">预览</a>
			</div>
			<div  style="margin-left: 200px;margin-top: 10px;float: left;width: 300px;"  class="t-overflow" id="background-music">
			</div>
		</div>
	</div>
</body>
</html>