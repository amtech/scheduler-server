<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>

<%--<script src="${ctx }/global/jqurey-cron/jquery-cron.js"></script>--%>
<%--<script src="${ctx }/global/jquery-gentleSelect/jquery-gentleSelect.js"></script>--%>
<%--<link type="text/css"--%>
	<%--href="${ctx }/global/jquery-gentleSelect/jquery-gentleSelect.css"--%>
	<%--rel="stylesheet" />--%>
<%--<link href="${ctx }/global/jqurey-cron/jquery-cron.css" rel="stylesheet"--%>
	<%--type="text/css" />--%>
</head>


<div class="portlet-title">
	<div id="joblisttitle" class="caption">应用:${appname}的任务列表</div>
	<div class="tools"></div>
</div>
<!-- app 表格 初始显示-->
<div id="jobtablediv" class="portlet-body">
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th class="col-md-1"></th>
				<th>任务名</th>
				<th>所属应用</th>
				<th class="hidden-480">状态</th>
				<!-- <th>操作</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="job" items="${jobs}">
				<tr>
					<td>
						<button id="${job.id}"
							onclick="loadPlantTable2('${job.id}','${appid}');"
							class="btn btn-default" type="button">+</button>
					</td>


					<td>${job.name}</td>
					<td>${appname}</td>
					<c:if test="${job.status=='1'}">
						<td class="hidden-480"><span class="badge badge-success">&nbsp&nbsp</span></td>

					</c:if>
					<c:if test="${job.status=='0'}">
						<td class="hidden-480"><span class="badge label label-danger">&nbsp&nbsp</span></td>

					</c:if>
					<%-- <td>
						<button class="btn btn-default" type="button"
							onclick="checkoradd('${job.id}','${appid}')">新增计划</button>  --%><%-- <a class="btn btn-default ajaxify" type="button" href="${ctx}/trgger/loadAddtrigger/${job.id}/${job.app.id}">新增计划</a>
					</td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="crondiv" class="portlet-body form" style="display: none;">
	<div class="form-horizontal" role="form">
		<div class="form-body">
			<div class="form-group">
				<label class="col-md-1 control-label">计划名</label>
				<div class="col-md-9">
					<input class="form-control input-inline input-medium" type="text"
						placeholder="Enter text">
				</div>
			</div>
			<div class="form-group" class="example" style="align: center">
				<label class="col-md-1 control-label">cron表达式</label>
				<div class="col-md-9">
					<input id="cronvlue" style="float: left;"
						class="form-control input-inline input-medium" type="text"
						placeholder="Enter text">
					<%--<div id="selector" style="height: 34px;"></div>--%>
                    <!-- Button trigger modal -->
                    <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#myModal">
                        <span class="glyphicon glyphicon-plus-sign"></span>
                    </button>

                    <!-- Modal -->
                    <div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">CronExpression</h4>
                                </div>
                                <div class="modal-body">
                                    <iframe id="iframeId" name="iframeName" src="${ctx}/views/job/cronmaker.html" scrolling="no" frameborder="0"></iframe>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" onclick="getExp();">生成</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="ret()">确认返回</button>
                                </div>
                            </div>
                        </div>
                    </div>
				</div>

			</div>
		</div>
	</div>
</div>

<script language="javascript">
//$(document).ready(function() {
//	$('#selector').cron({
//		onChange : function() {
//			$('#cronvlue').val($(this).cron("value"));
//		}
//	});
//});

function getExp(){
    window.frames["iframeName"].getExp();
}

function ret(){
    $("#cronvlue").val($("#iframeId").contents().find('body').find("#mycron").val());
}

function loadPlantTable2(jobid, appid) {
	var button = $("#" + jobid);
	if (button.text() == "+") {
	
		$(".btn").button('reset');
		button.text("-");
		$("#table2").html("");
		$.ajax({
			url : "${ctx}/app/listTriggerByAppidJobId/" + jobid + "/" + appid,
			type : "get",
			success : function(text) {
				$("#table2").html(text);
			},
			error : function() {
				alert("获取计划信息失败！！");
			}
		});
	} else {
		$(".btn").button('reset');
		$("#table2").html("");
	}
}

/* function checkoradd(jobid, appid) {
	$.ajax({
		url : "${ctx}/app/check/" + appid,
		type : "post",
		success : function(a) {
			if (a == 'true') {
				$("#joblisttitle").text("增加计划");
				$("#jobtablediv").hide();
				$("#table2").hide();
				$("#crondiv").show();
			}
			if (a == "false") {

			}
		},
		error : function() {
			alert("操作失败！！");
		}
	});
} */
</script>
<!-- END PAGE CONTENT-->