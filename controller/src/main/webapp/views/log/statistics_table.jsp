<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>任务名</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>执行次数</th>
			<th>成功次数</th>
			<th>失败次数</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fun:length(logStatics) > 0}">
			<c:forEach var="logStatic" items="${logStatics}">
				<tr>
					<td>${logStatic.jobName}</td>
					<td>${logStatic.beginTime}</td>
					<td>${logStatic.endTime}</td>
					<%--<td>${logStatic.timeConsuming}</td>--%>
					<td>${logStatic.executionTimes}</td>
                    <td>${logStatic.successTimes}</td>
					<td>${logStatic.failuresTimes}</td>
				</tr>
			</c:forEach>
		</c:if>

	</tbody>
</table>
<script type="text/javascript">
 $("#logQueryCondition").val('${logQueryCondition}');
</script>


