function queryFailurePolicy(){
	var proposalno = $("#proposalno").val();
	var licenseno = $("#licenseno").val();
	var starttime = $("#startTime").val();
	var endtime = $("#endTime").val();
	
	if(!isNotNull(proposalno) && !isNotNull(licenseno) && !isNotNull(starttime) && !isNotNull(endtime)){
		alert("请填写查询条件！");
		return false;
	}
	
	if((isNotNull(proposalno) && isNotNull(licenseno) && (isNotNull(starttime) || isNotNull(endtime))) 
			|| (isNotNull(proposalno) && isNotNull(licenseno))
			|| (isNotNull(proposalno) && (isNotNull(starttime) || isNotNull(endtime)))
			|| (isNotNull(licenseno) && (isNotNull(starttime) || isNotNull(endtime)))){
		alert("车牌号、投保单号、支付时间只能按一种条件进行查询！");
		return false;
	}
	
	if((isNotNull(starttime) && !isNotNull(endtime)) || (!isNotNull(starttime) && isNotNull(endtime))){
		alert("请将支付时间填写完整！");
		return false;
	}
	$("#queryButton").attr('disabled',true);

	$.ajax({
		url:"/business_monitor/policymonitor/queryPolicy?time="+new Date().getTime(),
		data:{
			"licenseno" : licenseno, 
			"proposalno" : proposalno,
			"startTime" : starttime,
			"endTime" : endtime
		},
		dataType:"json",
		async: true ,
		type:"get",
		success:function(data){
			if(data != "0"){
				var str = "";
				$.each(data, function(i,item){
					str += "<tr>"
							+ "<td>" + item.errormsg + "</td>"
							+ "<td>" + checkNull(item.licenseno) + "</td>"
							+ "<td>" + checkNull(item.id.proposalno) + "</td>"
							+ "<td>" + checkNull(item.policyno) + "</td>"
							+ "<td>" + format(new Date(item.validdate),"yyyy/MM/dd") + "</td>"
							+ "<td>" + format(new Date(item.startdate),"yyyy/MM/dd") + "</td>"
							+ "<td>" + item.starthour + "</td>"
							+ "<td>" + format(new Date(item.enddate),"yyyy/MM/dd") + "</td>"
							+ "<td>" + item.endhour + "</td>"
							+ "<td>" + checkNull(item.paystatus) + "</td>"
							+ "<td>" + (isNotNull(item.tradetime) ? format(new Date(item.tradetime),"yyyy/MM/dd HH:mm:ss") : "") + "</td>"
							+ "<td>" + checkNull(item.validno) + "</td>"
							+ "<td>" + checkNull(item.resultflag) + "</td>"
							+ "<td>" + checkNull(item.jfRemarks) + "</td>"
							+ "<td>" + (isNotNull(item.senddate) ? format(new Date(item.senddate),"yyyy/MM/dd HH:mm:ss") : "") + "</td>"
							+ "<td>" + checkNull(item.id.tradeno) + "</td>"
							+ "<td>" + checkFlag(item.flag)  + "</td>"
							+ "<td>" + checkNull(item.remarks) + "</td>"
						 + "</tr>";
					i++;
				});
				$("#errorData tbody").html("");
				$("#errorData tbody").html(str);
			}else{
				alert("未查到符合条件的数据！");
			}
			$("#queryButton").attr('disabled',false);
		},
		error:function(){
			alert("系统异常！");
			$("#queryButton").attr('disabled',false);
		}
	});
	
}

function checkFlag(flag){
	if(flag == "1"){
		return "已处理";
	}else if(flag == "0"){
		return "未处理";
	}
	return "";
}

