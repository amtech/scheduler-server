<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
</head>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true"></button>
	<h4 class="modal-title">添加联系人</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<div class="form-horizontal" role="form">
				<div class="form-body">
					<div class="form-group">
						<label class="col-md-2 control-label">姓名</label>
						<div class="col-md-10">
							<input  id="contactsName" class="form-control input-inline input-medium" type="text"
								placeholder="Enter text">
                            <span id="nameValidate" class="label"></span>
						</div>
					</div>
					<div class="form-group" class="example" style="align: center">
						<label class="col-md-2 control-label">邮箱</label>
						<div class="col-md-10">
							<input  id="contactsMail" style="float: left;"
								class="form-control input-inline input-medium" type="text"
								placeholder="Enter text">
							<%--<button type="button" class="btn btn-default">测试邮件</button>--%>
                            <span id="mailValidate" class="label"></span>
						</div>

					</div>
					<div class="form-group" class="example" style="align: center">
						<label class="col-md-2 control-label">手机号</label>
						<div class="col-md-10">
							<input id="phoneNo" style="float: left;"
								class="form-control input-inline input-medium" type="text"
								placeholder="Enter text">
                            <span id="phoneNoValidate" class="label"></span>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	<button id="save" type="button" class="btn blue" onclick="addContacts();">确定</button>
</div>
