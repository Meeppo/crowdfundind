<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>创建项目</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate();
			
			$("#btnSave").click(function(){
				$("#inputForm").attr("action","${ctxf}/project/create").submit();
			});

			$("#btnSubmit").click(function(){
				$("#inputForm").attr("action","${ctxf}/project/submit").submit();
			});
			
		});
	</script>
</head>
<body>
	
	<form:form id="inputForm" modelAttribute="project" action="${ctxf}/project/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >类型：</label>
			<div class="controls">
				<form:input path="type" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >金额：</label>
			<div class="controls">
				<form:input path="amount" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >期限：</label>
			<div class="controls">
				<form:input path="duration" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" >预约：</label>
			<div class="controls">
				<form:checkbox path="reservation" value="1" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<form:input path="beginDate" class="input-large"/>
			</div>
		</div>
		<div class="form-actions">
			
			<input id="btnSave" class="btn btn-primary" type="button" value="保 存草稿"/>&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存并提交"/>&nbsp;
			
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
