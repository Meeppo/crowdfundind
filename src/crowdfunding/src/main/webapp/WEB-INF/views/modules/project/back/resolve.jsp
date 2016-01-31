<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/project/project?status=1">待审核项目</a></li>
		<li class="active"><a href="${ctx}/project/project/list">所有项目</a></li>
	</ul>
	
	<form:form id="inputForm" modelAttribute="project" action="${ctx}/project/project/${project.status eq '1'?'verify':'' }" method="post" class="form-horizontal">
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
				<form:input path="beginDate" cssClass="input-large Wdate required" readonly="true" maxlength="20"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</div>
		</div>
		<c:if test="${project.status eq '1' }">
		<div class="control-group">
			<label class="control-label" >审核结果：</label>
			<div class="controls">
				<input type="radio" name="isPass" value="true" class="input-large" />通过
				<input type="radio" name="isPass" value="false" class="input-large" />未通过
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" >备注：</label>
			<div class="controls">
			<c:if test="${project.status eq '1' }">
				<form:textarea path="verifyRemarks" rows="4"/>
			</c:if>
			</div>
		</div>
		<div class="form-actions">

			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
