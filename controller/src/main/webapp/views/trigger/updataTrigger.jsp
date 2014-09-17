<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
</head>
<div class="form-horizontal" role="form">
	<div class="form-body">
		<div class="form-group">
			<label class="col-md-1 control-label">计划名</label>
			<div class="col-md-9">
				<input id="updatatriggername"
					class="form-control input-inline input-medium" type="text"
					placeholder="Enter text" value="${trigger.name}">
			</div>
		</div>
		<div class="form-group" class="example" style="align: center">
			<label class="col-md-1 control-label">cron表达式</label>
			<div class="col-md-9">
				<input id="updatacronvlue" style="float: left;"
					class="form-control input-inline input-medium" type="text"
					placeholder="Enter text" value="${trigger.cronExpression}">

                <!-- Button trigger modal -->
                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#updateModal">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </button>

                <!-- Modal -->
                <div class="modal" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">CronExpression</h4>
                            </div>
                            <div class="modal-body">
                                <iframe id="updateFrameId" name="updateFrameName" src="${ctx}/views/job/cronmaker.html" scrolling="no" frameborder="0"></iframe>
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
				<button class="btn btn-default" type="button" onclick="modify('${trigger.appId}','${jobId}','${trigger.id }');">确认</button>
				<button class="btn btn-default" type="button" onclick="canceModify();">取消</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

    function getExp(){
        window.frames["updateFrameName"].getExp();
    }

    function ret(){
        $("#updatacronvlue").val($("#updateFrameId").contents().find('body').find("#mycron").val());
    }
</script>
</html>