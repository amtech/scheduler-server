<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
</head>

<div class="row" style="height: 900px;">
	<div class="col-md-12">
		<div class="portlet box grey">
			<div class="portlet-title">
				<div id="joblisttitle" class="caption">任务信息</div>
			</div>
			<div id="jobtablediv" class="portlet-body">
				<div class="table-responsive">
					<table id="jobtable" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th class="col-md-1"></th>
								<th>任务名</th>
								<th>所属应用</th>
								<th class="hidden-480">状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="app" items="${appList}">
                                <c:forEach var="job" items="${app.jobs}">
                                    <tr>
                                        <td>
                                            <button id="${job.id}"
                                                    onclick="loadPlantTable('${job.id}','${app.id}');"
                                                    class="btn btn-default" type="button">+</button>
                                        </td>

                                        <td>${job.name}</td>
                                        <td>${app.name}</td>
                                        <c:if test="${job.status=='1'}">
                                            <td class="hidden-480"><span class="badge badge-success">&nbsp&nbsp</span></td>

                                        </c:if>
                                        <c:if test="${job.status=='0'}">
                                            <td class="hidden-480"><span
                                                    class="badge label label-danger">&nbsp&nbsp</span></td>

                                        </c:if>
                                        <td>
                                            <button class="btn btn-default" type="button"
                                                    onclick="checkoradd('${job.name}','${app.id}','${job.id}')">新增计划</button>
                                        </td>
                                    </tr>
                                </c:forEach>
								<%--<tr>--%>
									<%--<td>--%>
										<%--<button id="${app.job.id}"--%>
											<%--onclick="loadPlantTable('${app.job.id}','${app.id}');"--%>
											<%--class="btn btn-default" type="button">+</button>--%>
									<%--</td>--%>

									<%--<td>${app.job.name}</td>--%>
									<%--<td>${app.job.app.name}</td>--%>
									<%--<c:if test="${app.job.status=='1'}">--%>
										<%--<td class="hidden-480"><span class="badge badge-success">&nbsp&nbsp</span></td>--%>

									<%--</c:if>--%>
									<%--<c:if test="${app.job.status=='0'}">--%>
										<%--<td class="hidden-480"><span--%>
											<%--class="badge label label-danger">&nbsp&nbsp</span></td>--%>

									<%--</c:if>--%>
									<%--<td>--%>
										<%--<button class="btn btn-default" type="button"--%>
											<%--onclick="checkoradd('${app.job.name}','${app.id}','${app.job.id}')">新增计划</button>--%>
									<%--</td>--%>
								<%--</tr>--%>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
			<div id="crondiv" class="portlet-body form" style="display: none;">
				<div class="form-horizontal" role="form">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-1 control-label">计划名</label>
							<div class="col-md-9">
								<input id="triggername" class="form-control input-inline input-medium"
									type="text" placeholder="Enter text">
							</div>
						</div>
						<div class="form-group" class="example" style="align: center">
							<label class="col-md-1 control-label">cron表达式</label>
							<div class="col-md-9">
								<input id="cronvlue" style="float: left;"
									class="form-control input-inline input-medium" type="text"
									placeholder="Enter text">
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
						<div class="form-group">
							<div class="col-md-9">
								<input id="jobidtoadd" value="" hidden="hidden"/>
								<input id="appidtoadd" value="" hidden="hidden"/>
								<button class="btn btn-default" type="button"
									onclick="addTrigger()">确认</button>
								<button class="btn btn-default" type="button"
									onclick="cance();">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="updatediv" class="portlet-body form" style="display: none;">
	
			</div>
		</div>

	</div>
	<div id="table2" class="col-md-12"></div>
	
</div>



<script type="text/javascript">

    $(function(){
        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });
    });

    function getExp(){
        window.frames["iframeName"].getExp();
    }

    function ret(){
        $("#cronvlue").val($("#iframeId").contents().find('body').find("#mycron").val());
    }


	function loadPlantTable(jobid, appid) {
		var button = $("#" + jobid);
		if (button.text() == "+") {
			$(".btn").button('reset');
			button.text("-");
			$("#table2").html("");

			$.ajax({
				url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
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

	function checkoradd(jobName, appid,jobid) {
		$.ajax({
			url : "${ctx}/app/check/" + appid,
			type : "post",
			success : function(a) {
				if (a == 'true') {
					$("#joblisttitle").text("增加计划到任务：" + jobName);
					$("#jobtablediv").hide();
					$("#table2").hide();
					$("#crondiv").show();
					$("#jobidtoadd").val(jobid);
					$("#appidtoadd").val(appid);
				}
				if (a == "false") {

				}
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}
	function addTrigger(){
        var appId = $("#appidtoadd").val();
        var jobId = $("#jobidtoadd").val();
		$.ajax({
			url : "${ctx}/trigger/addTrigger",
			type : "post",
            data:{
                appId:appId,
                jobId:jobId,
                triggerName:$("#triggername").val(),
                cronValue:$("#cronvlue").val()
            },
            dataType:"json",
			success : function(a) {
                alert(a.msg);
                $("#crondiv").hide();
				$("#jobtablediv").show();

                //loadPlantTable(appId,jobId);
                $.ajax({
                    url : "${ctx}/trigger/listByAppidJobId/" + jobId + "/" + appId,
                    type : "get",
                    success : function(text) {
                        $("#table2").html(text);
                        $("#table2").show();

                    },
                    error : function() {
                        alert("获取计划信息失败！！");
                    }
                });
			},
			error : function() {
				alert("操作失败！！");
			}
		});
		
	}

	function preparedModify(jobid,triggerid){
		$.ajax({
			url : "${ctx}/trigger/preparedModify/"+triggerid+"/"+jobid,
			type : "post",
			success : function(data) {
				$("#updatediv").html(data);
			},
			error : function() {
				alert("操作失败！！");
			}
		});
		$("#joblisttitle").text("修改计划");
		$("#jobtablediv").hide();
		$("#table2").hide();
		$("#updatediv").show();
	}
	function canceModify(){
		$("#joblisttitle").text("任务信息");
		$("#jobtablediv").show();
		$("#table2").show();
		$("#updatediv").html("");
	}

	function modify(appId,jobId,triggerId){
		var triggerName=$("#updatatriggername").val();
		var cronValue=$("#updatacronvlue").val();
		$.ajax({
			url : "${ctx}/trigger/modify",
            data:{
                appId:appId,
                jobId:jobId,
                triggerId:triggerId,
                triggerName:triggerName,
                cronValue:cronValue
            },
			type : "post",
			success : function(data) {
                alert(data.msg);
				$("#joblisttitle").text("任务信息");
				$("#jobtablediv").show();
				$("#table2").show();
				$("#updatediv").hide();
				$.ajax({
					url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
					type : "get",
					success : function(text) {
						$("#table2").html(text);
					},
					error : function() {
						alert("获取计划信息失败！！");
					}
				});
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}

	function deleteTrigger(appid,jobid,triggerid){
		$.ajax({
			url : "${ctx}/trigger/delete",
            data:{
                appId:appid,
                triggerId:triggerid
            },
			type : "post",
			success : function(data) {
                $('#deleteModal').modal('hide');
				$.ajax({
					url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
					type : "get",
					success : function(text) {
						$("#table2").html(text);
					},
					error : function() {
						alert("获取计划信息失败！！");
					}
				});
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}

	function pauseTrigger(appid,jobid,triggerid){
		$.ajax({
			url : "${ctx}/trigger/pause/"+appid+"/"+triggerid,
			type : "post",
			success : function(data) {
				alert(data.msg);
				$.ajax({
					url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
					type : "get",
					success : function(text) {
                        alert(text.msg);
						$("#table2").html(text);
					},
					error : function() {
						alert("获取计划信息失败！！");
					}
				});
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}

	function resume(appid,jobid,triggerid){
		$.ajax({
			url : "${ctx}/trigger/resume/"+appid+"/"+triggerid,
			type : "post",
			success : function(data) {
				alert(data.msg);
				$.ajax({
					url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
					type : "get",
					success : function(text) {
						$("#table2").html(text);
					},
					error : function() {
						alert("获取计划信息失败！！");
					}
				});
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}

	function triggerJob(appid,jobid,triggerid){
		$.ajax({
			url : "${ctx}/trigger/triggerJob/"+appid+"/"+jobid,
			type : "post",
			success : function(data) {
				alert(data.msg);
				$.ajax({
					url : "${ctx}/trigger/listByAppidJobId/" + jobid + "/" + appid,
					type : "get",
					success : function(text) {
						$("#table2").html(text);
					},
					error : function() {
						alert("获取计划信息失败！！");
					}
				});
			},
			error : function() {
				alert("操作失败！！");
			}
		});
	}
	
	function cance(){
		$("#joblisttitle").text("任务信息");
		$("#jobtablediv").show();
		$("#table2").show();
		$("#crondiv").hide();
	}
</script>
</html>
<!-- END PAGE CONTENT-->