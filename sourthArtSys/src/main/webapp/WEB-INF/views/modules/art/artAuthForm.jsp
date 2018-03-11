<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>认证新增</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/easyUpload/easy-upload.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctxStatic}/easyUpload/easyUpload.js"></script>
	<style type="text/css">
		.auth-img{
			width: 300px;
			height: 300px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('#easyContainer').easyUpload({
			      allowFileTypes: '*.jpg;*.png;*.jpeg;*.bmp;',//允许上传文件类型，格式';*.doc;*.pdf'
			      allowFileSize: 100000,//允许上传文件大小(KB)
			      selectText: '选择文件',//选择文件按钮文案
			      multi: true,//是否允许多文件上传
			      multiNum: 5,//多文件上传时允许的文件数
			      showNote: true,//是否展示文件上传说明
			      note: '提示：最多上传5个文件，支持格式为jpeg、png、jpg的图片',//文件上传说明
			      showPreview: true,//是否显示文件预览
			      url: '${ctx}/art/auth/upload',//上传文件地址
			      fileName: 'file',//文件filename配置参数
			      formParam: {
			        //token: $.cookie('token_cookie')//不需要验证token时可以去掉
			      },//文件filename以外的配置参数，格式：{key1:value1,key2:value2}
			      timeout: 30000,//请求超时时间
			      okCode: 200,//与后端返回数据code值一致时执行成功回调，不配置默认200
			      successFunc: function(res) {
			   		var fileArr = res.success;
			   		var fileIds = "";
			   		fileArr.forEach(function(item,index){
			   			fileIds += item.data+",";
		            });
			        $("#files").val(fileIds.substring(0,fileIds.length-1));
			      },//上传成功回调函数
			      errorFunc: function(res) {
			        console.log('失败回调', res);
			      },//上传失败回调函数
			      deleteFunc: function(res) {
			    	  var fileArr = res.success;
	   				  var fileIds = "";
				   	  fileArr.forEach(function(item,index){
				   		fileIds += item.data+",";
			          });
				      $("#files").val(fileIds.substring(0,fileIds.length-1));
			      }
			    });
			
			//保存
			$("#btnSubmit").click(function(){
				resetHelpLine();
				if(validateForm()){
					$("#inputForm").submit();
				}
			});
			
			//审核
			$("#btnCheck").click(function(){
				//判断是否选中了审核
				$("#status-help").text("*");
				var status = $("input[name='status']:checked").val();
				if(typeof(status)!="undefined"){
					$("#inputForm").submit();
				}else{
					$("#status-help").text("*请选择审核结果");
				}
			});
			
			
			//重置提示语
			function resetHelpLine(){
				$("#orgid-help").text("*");
				$("#openid-help").text("*");;
				$("#name-help").text("*");;
				$("#idcard-help").text("*");
				$("#arttype-help").text("*");;
				$("#artlevel-help").text("*");;
				$("#files-help").text("*选择完图片后，记得上传");
			}
			//校验表单
			function validateForm(){
				
				if($("#org\\.id").val()==""){
					$("#orgid-help").text("*请选择机构");
					top.layer.msg("请选择机构");
					return false;
				}
				if($("#openId").val()==""){
					$("#openid-help").text("*请填写openId");
					top.layer.msg("请填写openId");
					return false;
				}
				
				if($("#phone").val()==""){
					$("#phone-help").text("*请填写手机号");
					top.layer.msg("请填写手机号");
					return false;
				}else{
					var pattern = /^1[34578]\d{9}$/;
					if(!pattern.test($("#phone").val())){
						$("#phone-help").text("*手机号格式错误");
						top.layer.msg("手机号格式错误");
						return false;
					}
				}
				
				if($("#name").val()==""){
					$("#name-help").text("*请填写姓名");
					top.layer.msg("请填写姓名");
					return false;
				}else{
					var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
					if(!pattern.test($("#name").val())){
						$("#name-help").text("*姓名只能为中文");
						top.layer.msg("姓名只能为中文");
						return false;
					}
				}
				
				if($("#idCard").val()==""){
					$("#idcard-help").text("*请填写身份证号");
					top.layer.msg("请填写身份证号");
					return false;
				}else{
					var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
					if(!pattern.test($("#idCard").val())){
						$("#idcard-help").text("*身份证号格式错误");
						top.layer.msg("身份证号格式错误");
						return false;
					}
				}
				
				if($("#artType").val()==""){
					$("#arttype-help").text("*请选择艺术类别");
					top.layer.msg("请选择艺术类别");
					return false;
				}
				if($("#artLevel").val()==""){
					$("#artlevel-help").text("*请选择艺术级别");
					top.layer.msg("请选择艺术级别");
					return false;
				}
				if($("#files").val()==""){
					$("#files-help").text("*请选择并上传证书");
					top.layer.msg("请选择并上传证书");
					return false;
				}
				
				return true;
			}
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/art/auth/">认证列表</a></li>
		<li class="active"><a href="${ctx}/art/auth/form?id=${artAuth.id}">认证<shiro:hasPermission name="art:auth:edit">${not empty artAuth.id?'审核':'新增'}</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="artAuth" action="${ctx}/art/auth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="files" id="files"/>
		<div class="control-group">
			<label class="control-label">机构：</label>
			<div class="controls">
				<form:select path="org.id" class="input-xlarge required" cssStyle="width:120px;" disabled="${empty artAuth.id?false:true}">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getOrgList()}" itemLabel="orgName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="orgid-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">openId：</label>
			<div class="controls">
				<form:input path="openId" htmlEscape="false" maxlength="200" class="input-xlarge required" readonly="${empty artAuth.id?false:true}"/>
				<span class="help-inline"><font color="red" id="openid-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="200" class="input-xlarge required" readonly="${empty artAuth.id?false:true}"/>
				<span class="help-inline"><font color="red" id="phone-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required" readonly="${empty artAuth.id?false:true}"/>
				<span class="help-inline"><font color="red" id="name-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idCard" htmlEscape="false" maxlength="200" class="input-xlarge required" readonly="${empty artAuth.id?false:true}"/>
				<span class="help-inline"><font color="red" id="idcard-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">艺术分类：</label>
			<div class="controls">
				<form:select path="artType" class="input-xlarge required" cssStyle="width:120px;" disabled="${empty artAuth.id?false:true}">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="arttype-help">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">艺术级别：</label>
			<div class="controls">
				<form:select path="artLevel" class="input-xlarge required" cssStyle="width:120px;" disabled="${empty artAuth.id?false:true}">
					<form:option value="" label="-请选择-"/>
					<form:options items="${fns:getDictList('art_level')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red" id="artlevel-help">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">上传证书：</label>
			<div class="controls">
				 <c:choose>
				 	<c:when test="${empty artAuth.id }">
				 		<div id="easyContainer"></div>
				 		<span class="help-inline"><font color="red" id="files-help">*选择完图片后，记得上传</font></span>
				 	</c:when>
				 	<c:otherwise>
				 		<c:forEach items="${artAuth.imgList}" var="img" varStatus="i">
				 			<img alt="" src="${img.imgUrl}"  class="img-rounded auth-img" />
				 		</c:forEach>
				 	</c:otherwise>
				 </c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证费用支付状态：</label>
			<div class="controls">
				<form:radiobuttons path="feePayStatus" items="${fns:getDictList('auth_feepay_status')}" itemLabel="label" itemValue="value" disabled="${empty artAuth.id?false:true}"  htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> 确认后不能进行修改。</span>
			</div>
		</div>
		<c:if test="${not empty artAuth.id}">
			<div class="control-group" style="color: red;">
				<label class="control-label">认证审核：</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictList('check_status')}" itemLabel="label" itemValue="value" disabled="${not empty artAuth.id && artAuth.status eq '0'?false:true}"  htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red" id="status-help">*</font></span>
				</div>
			</div> 
			<div class="control-group" style="color: red;">
				<label class="control-label">审核备注：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required" readonly="${not empty artAuth.id && artAuth.status eq '0'?false:true}" placeholder="填写审核意见"/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<c:if test="${not empty artAuth.id && artAuth.status eq '0'}">
				<shiro:hasPermission name="art:auth:edit"><input id="btnCheck" class="btn btn-warning" type="button" value="审核"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<c:if test="${empty artAuth.id}">
				<shiro:hasPermission name="art:auth:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>