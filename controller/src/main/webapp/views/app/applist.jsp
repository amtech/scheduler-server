<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
</head>
  <div id="reductiondiv" style="display: none;" class="row margin-bottom-20">
	<div class="col-md-12">
		<div class="fa-item  ">
			<button class="btn btn-default" onclick="reduction();"> <i class="fa fa-arrow-left"></i> 返回应用列表</button>
		</div>
	</div>
</div>  
<div class="row" style="height: 900px;">
	<div   class="col-md-12">
		
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div id="apptablediv"  class="portlet box grey">
			<div class="portlet-title">
				<div id="tabletitle" class="caption">应用列表</div>
				<div class="tools"></div>
			</div>
			<!-- app 表格 初始显示-->
			<div  class="portlet-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>应用名</th>
							<th>ip</th>
							<th>port</th>
							<th class="hidden-480">状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="app" items="${apps}">
							<tr>
								<td>${app.name}</td>
								<td>${app.ip}</td>
								<td>${app.port}</td>
								<c:if test="${app.status=='1'}">
									<td class="hidden-480"><span class="badge badge-success">&nbsp&nbsp</span></td>
									<td>
										<button class="btn btn-default" type="button"
											onclick="changejobtable('${app.id}','${app.name}');">查看任务</button>
									</td>
								</c:if>
								<c:if test="${app.status=='0'}">
									<td class="hidden-480"><span
										class="badge label label-danger">&nbsp&nbsp</span></td>
									<td>
										<button class="btn btn-default" type="button"
											onclick="changejobtable('${app.id}','${app.name}');">查看任务</button>
										<%--<button class="btn btn-default" type="button">删除</button>--%>
									</td>
								</c:if>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
		
		<div id="jobtablesuperdiv"  class="portlet box grey" style="display: none;">
			
			
		</div>
		
	</div>
	<div id="table2" class="col-md-12"> </div>
</div>
<script language="javascript">
	function changejobtable(appid,name) {
		
		$("#apptablediv").hide();
		$("#jobtablesuperdiv").show();
		 $.ajax({
			url : "${ctx}/app/listJobByAppId",
            data:{
                appId:appid,
                appName:name
            },
			type : "post",
			success : function(text) {
				$("#jobtablesuperdiv").html(text);
			},
			error : function() {
				alert("获取任务信息失败！！");
			}
		});  
		$("#reductiondiv").show();
	}

	function reduction(){
		$("#reductiondiv").hide();
		$("#jobtablesuperdiv").html("");
		$("#jobtablesuperdiv").hide();
		$("#table2").show();
		$("#table2").html("");
		$("#apptablediv").show();
	}
	
</script>
<!-- END PAGE CONTENT-->