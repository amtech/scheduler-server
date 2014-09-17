<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
</head>

<div class="row" id="table2">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box grey" >
			<div class="portlet-title" >
				<div class="caption" >
					<i class="fa fa-cogs"></i>配置联系人
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body">
				<table class="table table-bordered table-striped" >
					<thead>
						<tr>
							<th class="col-md-2">应用名</th>

							<th class="col-md-8">联系人</th>
							<th class="col-md-2">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fun:length(appContactsInfos) < 0}">
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td>&nbsp</td>
								<td>&nbsp</td>
								<td>&nbsp</td>
							</tr>
						</c:if>
						<c:if test="${fun:length(appContactsInfos) > 0}">
							<c:forEach var="appContactsInfo" items="${appContactsInfos}">
								<tr>
									<td>${appContactsInfo.appName}</td>
									<td>${appContactsInfo.contactsMails}</td>
									<td>
										<a href="${ctx}/contacts/configlist/${appContactsInfo.appid}" class="btn btn-default" type="button"
											data-target="#config" data-toggle="modal">配置</a>
									</td>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="config" role="basic" data-backdrop="static">
	<div class="page-loading page-loading-boxed">
		<img src="${ctx}/global/img/loading-spinner-grey.gif" alt=""
			class="loading"> <span> &nbsp;&nbsp;Loading... </span>
	</div>
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>
<!-- END PAGE CONTENT-->