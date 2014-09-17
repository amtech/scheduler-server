//去掉字符串头尾空格
function trim(s){
  var strReturn;
  strReturn = leftTrim(s);
  strReturn = rightTrim(strReturn);
  return strReturn;
}

//去掉字符串头空格
function leftTrim(strValue){
  var re =/^\s*/;
  if(strValue==null){
	  return null;
  }
  strValue= "" + strValue;
  var strReturn = strValue.replace(re,"");
  return strReturn;
}

//去掉字符串尾空格
function rightTrim(strValue){
  var re =/\s*$/;
  if(strValue==null){
	  return null;
  }
  var strReturn = strValue.replace(re,"");
  return strReturn;
}

//格式化日期
var format = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}

//校验空节点值
function checkNull(str){
	if(str == null || 'undefined' == typeof(str)){
		return "";
	}
	return trim(str);
}

//是否为空
function isNotNull(obj){
	if(obj != null && trim(obj) != "" && obj != "null" && 'undefined' != typeof(obj)){
		return true;
	}
	return false;
}