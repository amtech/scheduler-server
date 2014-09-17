<%@ page import="java.sql.Clob" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.IOException" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th class="col-md-1">应用名</th>
			<th>任务名</th>
			<th>计划名</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>耗时</th>
			<th>状态</th>
			<th>异常信息</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fun:length(logs) > 0}">
			<c:forEach var="log" items="${logs}">
				<tr>
					<td>${log.appName}</td>
					<td>${log.jobName}</td>
					<td>${log.triggerName}</td>
					<td>${log.beginTime}</td>
					<td>${log.endTime}</td>
					<td>${log.timeConsuming}s</td>
					<%--<td>${log.status}</td>--%>
                    <c:if test="${log.status=='1'}">
                        <td class="hidden-480"><span class="badge badge-success">&nbsp&nbsp</span></td>

                    </c:if>
                    <c:if test="${log.status=='0'}">
                        <td class="hidden-480"><span
                                class="badge label label-danger">&nbsp&nbsp</span></td>

                    </c:if>
					<td>
					<%--${log.exceptionMsg}--%>
                        <a  href="${ctx}/log/exception/${log.id}" class="btn btn-default btn-sm" data-toggle="modal" data-target="#ajax" >
                            查看详细
                        </a>
                        <div class="modal fade" id="ajax" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                </div>
                            </div>
                        </div>

                    </td>
				</tr>
			</c:forEach>
		</c:if>

	</tbody>
</table>
<script type="text/javascript">
 $("#logQueryCondition").val('${logQueryCondition}');
 $(function(){
     $("#ajax").on("hidden.bs.modal", function() {
         $(this).removeData("bs.modal");
     });
 });
    function exception(logId){
        <%--$('#ajax').modal({--%>
            <%--keyboard: false,--%>
            <%--show: false,--%>
            <%--remote: "${ctx}/log/exception/"+logId--%>
        <%--});--%>
        $("#ajax").load("${ctx}/log/exception/"+logId);
       // $('#ajax').modal('show');
    }

</script>


