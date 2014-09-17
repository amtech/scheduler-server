<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
</head>

<div class="row">
	<div class="col-md-12">
		<table class="table">
			<tr>
				<td><a id="addcontactsbtn"
					href="${ctx}/views/contacts/add.jsp"
					class="btn btn-default" data-target="#ajax" data-toggle="modal">新增联系人</a></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<div class="portlet box grey">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-cogs"></i>联系人列表
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body" id="table">

			</div>
		</div>

	</div>

</div>

<div class="modal fade" id="ajax" role="basic" data-backdrop="static">
	<div class="page-loading page-loading-boxed">
		<img src="${ctx}/global/img/loading-spinner-grey.gif" alt=""
			class="loading"> <span> &nbsp;&nbsp;Loading... </span>
	</div>
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>

<div class="modal fade" id="ajax2" role="basic" data-backdrop="static">
    <div class="page-loading page-loading-boxed">
        <img src="${ctx}/global/img/loading-spinner-grey.gif" alt=""
             class="loading"> <span> &nbsp;&nbsp;Loading... </span>
    </div>
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>

</html>
 <script type="text/javascript">

$(function(){
	$.ajax({
		url : "${ctx}/contacts/list",
		type : "get",
		success : function(text) {
		 	$('#table').html(text);  
		},
		error : function() {
			alert("加载失败！！");
		}
	});

    $('body').on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });
//    $("#ajax2").on("hidden.bs.model",function(e){$(this).removeData();});
});
 
function addContacts(){
	var name=$('#contactsName').val();
	var mail=$('#contactsMail').val();
	var phoneNo=$('#phoneNo').val();
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var regPartton=/1[3-8]+\d{9}/;
	if(name==null||name==''){
        $('#nameValidate').addClass("label-danger");
        $('#nameValidate').text("用户名不能为空");
		return false;
	}else if(mail==null||mail==''){
        $('#mailValidate').addClass("label-danger");
        $('#mailValidate').text("邮箱不能为空");
		return false;
	}else if(!reg.test(mail)){
        $('#mailValidate').addClass("label-danger");
        $('#mailValidate').text("邮箱格式不正确");
        return false;
    }else if(!phoneNo || phoneNo==null){
        $('#phoneNoValidate').addClass("label-danger");
        $('#phoneNoValidate').text("手机号码不能为空");
        return false;
    }else if(!regPartton.test(phoneNo)){
        $('#phoneNoValidate').addClass("label-danger");
        $('#phoneNoValidate').text("手机号码格式不正确");
        return false;
    }else{
        $.ajax({
            url : "${ctx}/contacts/add/" + name + "/" + mail + "/" + phoneNo,
            type : "post",
            success : function(text) {
                //alert("保存成功");
//                $('#contactsName').val('');
//                $('#contactsMail').val('');
//                $('#phoneNo').val('');
                $('#ajax').modal('hide')
                $.ajax({
                    url : "${ctx}/contacts/list",
                    type : "get",
                    success : function(text) {
                        $('#table').html(text);
                    },
                    error : function() {
                        alert("加载失败！！");
                    }
                });
            },
            error : function() {
                alert("操作失败！！");
            }
        });
        return true;
    }

	
}

function modify(){
    var name=$('#contactsName').val();
    var mail=$('#contactsMail').val();
    var phoneNo=$('#phoneNo').val();
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var regPartton=/1[3-8]+\d{9}/;
    if(name==null||name==''){
        $('#nameValidate').addClass("label-danger");
        $('#nameValidate').text("用户名不能为空");
        return false;
    }else if(mail==null||mail==''){
        $('#mailValidate').addClass("label-danger");
        $('#mailValidate').text("邮箱不能为空");
        return false;
    }else if(!reg.test(mail)){
        $('#mailValidate').addClass("label-danger");
        $('#mailValidate').text("邮箱格式不正确");
        return false;
    }else if(!phoneNo || phoneNo==null){
        $('#phoneNoValidate').addClass("label-danger");
        $('#phoneNoValidate').text("手机号码不能为空");
        return false;
    }else if(!regPartton.test(phoneNo)){
        $('#phoneNoValidate').addClass("label-danger");
        $('#phoneNoValidate').text("手机号码格式不正确");
        return false;
    }else{
        $.ajax({
            url:"${ctx}/contacts/modify",
            data:{
                id:$('#contactsId').val(),
                name:$('#contactsName').val(),
                mail:$('#contactsMail').val(),
                phoneNo:$('#phoneNo').val()
            },
            type:"post",
            success : function(text) {
//                alert("修改成功");
//                $('#contactsName').val();
//                $('#contactsMail').val();
//                $('#phoneNo').val();
                $('#ajax2').modal('hide');
                $.ajax({
                    url : "${ctx}/contacts/list",
                    type : "get",
                    success : function(text) {
                        $('#table').html(text);
                    },
                    error : function() {
                        alert("加载失败！！");
                    }
                });
            },
            error : function() {
                alert("加载失败！！");
            }

        });
        return true;
    }

}

function del(contactsId){
    $.ajax({
        url:"${ctx}/contacts/delete?contactsId="+contactsId,
        type:"get",
        success:function(text){
            $('#deleteModal').modal('hide');
            $('#table').html(text);
        },
        error:function(){
            alert("删除失败!");
        }
    });
}

function sendMail(){
    $.ajax({
        url:"${ctx}/contacts/sendMail?mail="+$('#contactsMail').val(),
        type:"get",
        dataType:"json",
        success:function(msg){
            alert(msg.status);
        },
        error:function(){
            alert("发送失败!");
        }
    });
}

</script>