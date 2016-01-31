<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待审核项目列表</title>
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
		<li class="active"><a href="${ctxf}/project/myList">所有项目</a></li>
		<li><a href="${ctxf}/project/save">新建项目</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/project/project/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div>
			
			<label>创建开始：</label>
			<input id="createDateStart"  name="createDateStart"  type="text" readonly="readonly" maxlength="20" class="input-small Wdate"  value="<fmt:formatDate value="${leave.createDateStart}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			<label>创建结束：</label>
			<input id="createDateEnd" name="createDateEnd" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="<fmt:formatDate value="${leave.createDateEnd}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>标题</th>
			<th>类型</th>
			<th>金额</th>
			<th>期限</th>
			<th>进度</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="project">
			<tr>
				<td>${project.title}</td>
				<td>${project.type}</td>
				<td>${project.amount}</td>
				<td>${project.duration}</td>
				<td>${project.percent}%</td>
				<td>
					
					<a href="${ctx}/sys/workflow/processMap?processInstanceId=${project.fundProcessInstId}" class="fancybox"  data-fancybox-type="iframe">跟踪</a>
				<c:if test="${project.status eq '0' }">
					<a href="${ctxf}/project/save?id=${project.id}">修改</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
