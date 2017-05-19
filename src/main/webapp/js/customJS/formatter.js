// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};
var formatter = {
    digitUppercase:function(n) {
        var fraction = ['角', '分'];
        var digit = [
            '零', '壹', '贰', '叁', '肆',
            '伍', '陆', '柒', '捌', '玖'
        ];
        var unit = [
            ['元', '万', '亿'],
            ['', '拾', '佰', '仟']
        ];
        var head = n < 0 ? '欠': '';
        n = Math.abs(n);
        var s = '';
        for(var i = 0; i < fraction.length; i++) {
            s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
        }
        s = s || '整';
        n = Math.floor(n);
        for(var i = 0; i < unit[0].length && n > 0; i++) {
            var p = '';
            for(var j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[n % 10] + unit[1][j] + p;
                n = Math.floor(n / 10);
            }
            s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
        }
        return head + s.replace(/(零.)*零元/, '元')
                .replace(/(零.)+/g, '零')
                .replace(/^整$/, '零元整');
    },
	changeToDecimal:function(value,precision){
		if ((value != "0" && value == "") || value == null || value == undefined) {
			return "";
		}
		var value = Math.round(parseFloat(value) * 100) / 100;
		var xsd = value.toString().split(".");
		if (xsd.length == 1) {
			value = value.toString() + ".";
			for(i=0;i<precision;i++){
					value = value.toString() + "0";
			}
			return value;
		}
		if (xsd.length > 1) {
			if (xsd[1].length < precision) {
				for(i=0;i<precision-xsd[1].length;i++){
					value = value.toString() + "0";
				}
			}
			return value;
		}  
    },
	changeTwoDecimal:function(value){
		//return "￥"+formatter.changeToDecimal(value,2);
		return isNaN(value)?value:formatter.changeToDecimal(value,2);
    },
    changeToRMB:function(value){
    	if (value == null || value == undefined ||(value != '0' && value == "")) {
    			return "";
    	}
		return isNaN(value)?value:"￥"+formatter.changeToDecimal(value,2);
    },
    changeToM2:function(value){
    	if (value == null || value == undefined ||(value != '0' && value == "")) {
    			return "";
    	}
		return isNaN(value)?value:formatter.changeToDecimal(value,2)+"㎡";
    },
    changeToHour:function(value){
    	if (value == null || value == undefined ||(value != '0' && value == "")) {
    			return "";
    	}
		return isNaN(value)?value:formatter.changeToDecimal(value,2)+"h";
    },
    changeFourDecimal:function(value){
		//return "￥"+formatter.changeToDecimal(value,2);
    	return formatter.changeToDecimal(value,4);
    },
    
    //EasyUI用DataGrid用日期格式化
    DateFormatter: function (value, rec, index) {
        if (value == undefined||value == "") {
            return "";
        }
        /*json格式时间转js时间格式*/
        var date = new Date(value.replace("-", "/").replace("-", "/"));
        return date.Format("yyyy-MM-dd");
    },
    //EasyUI用DataGrid用日期格式化
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined||value == "") {
            return "";
        }
        /*json格式时间转js时间格式*/
        var date = new Date(value);
        return date.Format("yyyy-MM-dd hh:mm:ss");
    },
    //得到红色文本
    redText:function(value, rec, index){
    	if (value != undefined) {
			return "<a style='color: red;'>"+value+"</a>";
		}
    },
    
    /**
     * 四舍五入
     * 
     * @param num 数值
     * @param v 精确度
     * @returns {Number}
     */
    decimal : function(s, n) {   
    	if(undefined == s || !s){
    		return '0.00';
    	}
    	n = n > 0 && n <= 20 ? n : 2;   
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
        var l = s.split(".")[0].split("").reverse(),   
        r = s.split(".")[1];   
        t = "";   
        for(i = 0; i < l.length; i ++ ) {   
        	t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
        }   
        return t.split("").reverse().join("") + "." + r;   
    },
	accAdd : function(arg1, arg2) {
		var r1, r2, m, c;
        try { r1 = arg1.toString().split(".")[1].length; } catch (e) { r1 = 0; }
        try { r2 = arg2.toString().split(".")[1].length; } catch (e) { r2 = 0; }
        c = Math.abs(r1 - r2);
        m = Math.pow(10, Math.max(r1, r2));
        if (c > 0) {
            var cm = Math.pow(10, c);
            if (r1 > r2) {
                arg1 = Number(arg1.toString().replace(".", ""));
                arg2 = Number(arg2.toString().replace(".", "")) * cm;
            } else {
                arg1 = Number(arg1.toString().replace(".", "")) * cm;
                arg2 = Number(arg2.toString().replace(".", ""));
            }
        } else {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", ""));
        }
        return (arg1 + arg2) / m;
    },
    accSub : function (arg1, arg2) {
    	if(undefined == arg1 || !arg1){
    		arg1 = 0.00;
    	}
    	if(undefined == arg2 || !arg2){
    		arg2 = 0.00;
    	}
    	var r1, r2, m, n;
    	try { r1 = arg1.toString().split(".")[1].length; } catch(e) { r1 = 0; }
    	try { r2 = arg2.toString().split(".")[1].length; } catch(e) { r2 = 0; }
    	m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    	n = (r1 >= r2) ? r1 : r2;
    	return ((arg1 * m - arg2 * m) / m).toFixed(n);
	},
    accMul : function(arg1, arg2) {
    	if(undefined == arg1 || !arg1){
    		arg1 = 0.00;
    	}
    	if(undefined == arg2 || !arg2){
    		arg2 = 0.00;
    	}
	    var m=0, s1=arg1.toString(), s2=arg2.toString();
	    try{ m+=s1.split(".")[1].length; } catch(e) { }
	    try{ m+=s2.split(".")[1].length; } catch(e) { }
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
    },
    accDiv : function(arg1, arg2) {
        var t1 = 0, t2 = 0, r1, r2;
        try { t1 = arg1.toString().split(".")[1].length; } catch (e) { }
        try { t2 = arg2.toString().split(".")[1].length; } catch (e) { }
        with (Math) {
            r1 = Number(arg1.toString().replace(".", ""));
            r2 = Number(arg2.toString().replace(".", ""));
            return (r1 / r2) * pow(10, t2 - t1);
        }
    },
    addToRMB : function(arg1, arg2) {
    	return formatter.changeToRMB(formatter.accAdd(arg1, arg2));
    },
    subToRMB : function(arg1, arg2) {
    	return formatter.changeToRMB(formatter.accSub(arg1, arg2));
    },
    mulToRMB : function(arg1, arg2) {
    	return formatter.changeToRMB(formatter.accMul(arg1, arg2));
    },
    
    bg4Status:function(value){
		var StatusName = system.getDictValue(value);
		var background;
		switch (StatusName) {
		case '新建':
		case '已预约':
		case '在修':
			background = '#299be4';
			break;
		case '派工':
		case '进厂':
			background = '#efad20';
			break;
		case '完工':
			background = '#dda0dd';
			break;
		case '结算':
			background = '#00ba09';
            break;
        case '已取消':
            background = '#00ac00';
            break;
		default:
			StatusName = '作废';
			background = '#aaaaaa';
			break;
		}
		return "<span style='background:"+background+";color:#fff;padding:3px;border-radius:7px;'>"+StatusName+"</span>"
	}
    
}
