<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<div class="portlet box grey">
	<div class="portlet-title">
		<div class="caption">任务：${jobname }的计划信息</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>计划名</th>
						<th>上次执行时间</th>
						<th>下次执行时间</th>
						<%--<th class="hidden-480">״表达式</th>--%>
                        <th>cron表达式</th>
						<th>状态</th>
						<!-- <th>操作</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="trigger" items="${triggerInfos}">
						<tr>
							<td>${trigger.name}</td>
							<td>${trigger.lastStartTime}</td>
							<td>${trigger.nextStartTime}</td>
							<td>${trigger.cronExpression}</td>
							<c:if test="${trigger.status=='1'}">
								<td class="hidden-480">执行中</td>
								<%-- <td>
									<button id="modify_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="preparedModify('${jobId}','${trigger.triggerid}')">修改</button>
									<button id="delete_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="deletetrigger('${trigger.appId}','${jobId}','${trigger.triggerid}')">删除</button>
									<button id="pass_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="pause('${trigger.appId}','${jobId}','${trigger.triggerid}')">停止</button>
									<button id="runonce_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="pause('${trigger.appId}','${jobId}','${trigger.triggerid}')" >立即执行</button>
								</td> --%>
							</c:if>
							<c:if test="${trigger.status=='2'}">
								<td class="hidden-480">等待执行</td>
							<%-- 	<td>
									<button id="modify_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="preparedModify('${jobId}','${trigger.triggerid}')">修改</button>
									<button id="delete_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="deletetrigger('${trigger.appId}','${jobId}','${trigger.triggerid}')">删除</button>
									<button id="pass_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="pause('${trigger.appId}','${jobId}','${trigger.triggerid}')">停止</button>
									<button id="runonce_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="triggerJob('${trigger.appId}','${jobId}','${trigger.triggerid}')">立即执行</button>
								</td> --%>
							</c:if>
							<c:if test="${trigger.status=='3'}">
								<td class="hidden-480">停止</td>
								<%-- <td>
									<button id="modify_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="preparedModify('${jobId}','${trigger.triggerid}')">修改</button>
									<button id="delete_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="deletetrigger('${trigger.appId}','${jobId}','${trigger.triggerid}')">删除</button>
									<button id="start_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="resume('${trigger.appId}','${jobId}','${trigger.triggerid}')">开始</button>
									<button id="triggerJob_${trigger.triggerid}" class="btn btn-default"
										type="button" onclick="triggerJob('${trigger.appId}','${jobId}','${trigger.triggerid}')">立即执行</button>
								</td> --%>
							</c:if>
							<c:if test="${trigger.status=='4'}">
								<td class="hidden-480">失效</td>
								<%-- <td>
									<button id="modify_${trigger.triggerid}" class="btn btn-default"
										type="button">生效</button>
								</td> --%>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</html>