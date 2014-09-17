<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/layouts/base.jsp"%>

<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>姓名</th>
			<th>邮箱</th>
			<th>手机号</th>
			<th class="hidden-480">操作</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="contacts" items="${contacts}">
			<tr>
				<td>${contacts.name}</td>
				<td>${contacts.mail}</td>
				<td>${contacts.phoneNo}</td>
				<td>
                    <a id="toModify"
                       href="${ctx}/contacts/toModify?contactsId=${contacts.id}"
                       class="btn btn-default" data-target="#ajax2" data-toggle="modal" >修改</a>
                    <a id="delete" href="#deleteModal" class="btn btn-default" data-toggle="modal">删除</a>
                    <!-- Button trigger modal -->
                    <%--<button class="btn btn-default" data-toggle="modal" data-target="#myModal">--%>
                        <%--删除--%>
                    <%--</button>--%>

                    <!-- Modal -->
                    <div class="modal" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">提示信息</h4>
                                </div>
                                <div class="modal-body">
                                    <p class="lead">确定要删除此条记录?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn blue" onclick="del('${contacts.id}');">确定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
			</tr>
		</c:forEach>

	</tbody>
</table>
