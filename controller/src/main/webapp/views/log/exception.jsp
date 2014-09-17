<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <%@ include file="/WEB-INF/layouts/base.jsp"%>
    <link type="text/css"
          href="${ctx }/global/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
          rel="stylesheet" />
</head>
<body>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">详细信息</h4>
</div>
<div class="modal-body">
    ${exception}
</div>
<div class="modal-footer">
    <button type="button" class="btn default" data-dismiss="modal">关闭</button>
</div>

</body>
</html>