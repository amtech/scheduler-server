<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>

</head>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true"></button>
	<h4 class="modal-title">配置联系人</h4>
</div>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12" style=" overflow: auto;">
			<table class="table table-bordered table-striped"  >
				<thead>
					<tr>
						<th class="col-md-2">姓名</th>

						<th class="col-md-8">邮箱</th>
						<th class="col-md-2">选择</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="contactsConfigInfo" items="${contactsConfigInfos}">
						<tr>
							<td>${contactsConfigInfo.contactsName}</td>
							<td>${contactsConfigInfo.contactsMail}</td>
							<c:if test="${contactsConfigInfo.flag=='1'}">
								<td><input id="${contactsConfigInfo.contactsId}" name="checkbox" checked="checked"  type="checkbox"></td>
							</c:if>
							<c:if test="${contactsConfigInfo.flag=='0'}">
								<td><input id="${contactsConfigInfo.contactsId}" name="checkbox" type="checkbox"></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
    function config(){
        var array = new Array();
        var i = 0;
        $(":checked").each(function(){
            if($(this).prop("checked")){
                array[i]=$(this).attr("id");
                i++;
            }
        })
        var str = array.join(',');
        $.ajax({
            url:"${ctx}/contacts/config",
            data:{
                contactsIds:str,
                appId:"${appId}"
            },
            type:"post",
            dataType:"json",
            success:function(result){
                $('#config').modal('hide');
                $.ajax({
                    url:"${ctx}/contacts/configinfo",
                    type:"get",
                    success : function(text) {
                        $('#table2').html(text);
                    },
                    error : function() {
                        alert("加载失败！！");
                    }
                });
            },
            error:function(){
                alert("配置失败");
            }
        });
    }
</script>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	<button id="save" type="button" class="btn blue" onclick="config();">确认</button>
</div>