<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<link type="text/css"
	href="${ctx }/global/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
</head>

<div class="row">
	<div class="col-md-12">
		<table class="table">
			<tr>
				<td>
					<div>
						<h5 style="float: left;">所属应用</h5>
						<select id="appselect" style="float: left;"
							class=" btn btn-default">
							<option value="">应用列表</option>
						</select>
					</div>
				</td>
				<td>
					<div>
						<h5 style="float: left;">所属任务</h5>
						<select id="jobselect" class=" btn btn-default">
							<option value="">任务列表</option>
						</select>
					</div>
				</td>
				<!-- <td>
					<div>
						<h5 style="float: left;">所属计划</h5>
						<select id="triggerselect" class=" btn btn-default">
							<option value="">计划列表</option>
						</select>
					</div>
				</td> -->
				<td>
					<!-- <div>
						<h5 style="float: left;">状态</h5>
						<select id="statusselect" class=" btn btn-default">
							<option value="1">状态列表</option>
							<option value="1">执行中</option>
							<option value="2">等待执行</option>
							<option value="3">停止</option>
							<option value="4">失效</option>
						</select>
					</div> -->
				</td>
				<td>
					<div id="starttime_main" class="control-group">
						<h5 style="float: left;">从</h5>
						<div id="starttime"
							class="input-group date form_datetime col-md-5">

							<input id="beginTimeVal" class="btn btn-default" size="16" type="text" value=""
								readonly> </input> <span class="input-group-addon">
								<!-- <span
								class="glyphicon glyphicon-remove"></span> --> <span
								class="glyphicon glyphicon-th"></span>
							</span>
						</div>

						<!-- <input type="hidden" id="dtp_input1" value="" /><br/> -->
					</div>

				</td>
				<td>
					<div id="endtime_main" class="control-group">
						<h5 style="float: left;">到</h5>
						<div id="endtime" class="input-group date form_datetime col-md-5"
							data-date-format="dd MM yyyy - HH:ii p"
							data-link-field="dtp_input1">
							<input id="endTimeVal" class="btn btn-default" size="16" type="text" value=""
								readonly>
							<!-- <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> -->
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-th"></span></span>
						</div>

					</div>

				</td>
				<td><button class="btn btn-default" type="button" onclick="queryStatic();">查询</button>
					<button class="btn btn-default" type="button" onclick="exportExcel();">导出Excel</button></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>


		</table>
		<div class="portlet box grey">
			<div class="portlet-title">
				<div class="caption">
					<div class="theme-options" style="display: block;">
						<span style="float: left;">日志列表 </span>
					</div>
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table id="table" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="col-md-1">任务名</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<%--<th class="hidden-480">已执行时间</th>--%>
								<th>执行次数</th>
								<th>成功次数</th>
								<th>失败次数</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<input id="logQueryCondition"  value="" type="hidden"/>
<script type="text/javascript"
	src="${ctx }/global/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${ctx }/global/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(function() {

		$('#starttime').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy/MM/dd hh:ii',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			minuteStep : 1,
			showMeridian : 1,
			pickerPosition : "bottom-left"
		});
		$('#endtime').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy/MM/dd hh:ii',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			minuteStep : 1,
			showMeridian : 1,
			pickerPosition : "bottom-left"
		})
		loadAppOption();
	})

	function loadAppOption() {
		var opt = "";
		$.ajax({
			url : "${ctx}/log/loadAppOption",
			type : "get",
			success : function(data) {
				$.each(data, function(i, item) {
					opt = opt + "<option value=\"" + item.id
							+ "\" onclick=\"selectapp(\'" + item.id + "\');\">"
							+ item.name + "</option>";

				});
				$("#appselect").append(opt);
			},
			error : function() {
				alert("获取应用列表失败！！");
			}
		});
	}

	function selectapp(appid) {
		var opt = "";
		$.ajax({
			url : "${ctx}/log/loadJobOption/" + appid,
			type : "get",
			success : function(data) {
				if (data.length > 0) {
					$.each(data, function(i, item) {
						opt = opt + "<option value='" + item.id
								+ "' >" + item.name + "</option>";
					});
					$("#jobselect").append(opt);
				} else {
					alert("该应用暂无任务");
				}
			},
			error : function() {
				alert("获取应用列表失败！！");
			}
		});
	}

	function queryStatic() {
		/* appId = $("#appselect").val();
		jobId = $("#jobselect").val();
		tirggerId = $("#triggerselect").val();
		status = $("#statusselect").val(); 
		alert(status);*/
		/* alert($("#appselect").val());
		alert($("#statusselect").val()); */
		$.ajax({
			url : "${ctx}/log/queryStatistics",
			type : "post",
			data:{
				appId:$("#appselect").val(),
				jobId:$("#jobselect").val(),
				beginTime:$("#beginTimeVal").val(),
				endTime:$("#endTimeVal").val()
			},
			success : function(data) {
				$("#table").html("");
			 	$("#table").html(data);
			},
			error : function() {
				alert("加载数据失败！！");
			}
		});
	}

	function exportExcel(){
		var logQueryCondition=$("#logQueryCondition").val();
		if(logQueryCondition==""){//没有做过查询
			$.ajax({
				url : "${ctx}/log/statisticsExportExcel",
				type : "post",
				data:{
					appId:$("#appselect").val(),
					jobId:$("#jobselect").val(),
					beginTime:$("#beginTimeVal").val(),
					endTime:$("#endTimeVal").val()
				},
				success : function(data) {
					$("#logQueryCondition").val(data);
					location.href="${ctx}/log/statisticsExportExcel/"+data;
				},
				error : function() {
					alert("文件导出失败！！");
				}
			});
		}else{//做过查询
			location.href="${ctx}/log/statisticsExportExcel/"+logQueryCondition;
		}
		 
	}
</script>
</html>