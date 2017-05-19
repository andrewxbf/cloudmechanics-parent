var dateUtil = {
	/**
	 * 日期格式化 // 格式 YYYY/yyyy/YY/yy 表示年份 // MM/M 月份 // W/w 星期 // dd/DD/d/D 日期 //
	 * hh/HH/h/H 时间 // mm/m 分钟 // ss/SS/s/S 秒
	 */
	getCurrentDateTime : function(datetime) {
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1;// js从0开始取
		var date = datetime.getDate();
		var hour = datetime.getHours();
		var minutes = datetime.getMinutes();
		var second = datetime.getSeconds();

		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (second < 10) {
			second = "0" + second;
		}

		var time = year + "-" + month + "-" + date + " " + hour + ":" + minutes
				+ ":" + second; // 2009-06-12 17:18:05
		// alert(time);
		return time;
	},

	dateAdd : function(interval, number, date) {
		/*
		 * 功能:实现VBScript的DateAdd功能. 参数:interval,字符串表达式，表示要添加的时间间隔.
		 * 参数:number,数值表达式，表示要添加的时间间隔的个数. 参数:date,时间对象. 返回:新的时间对象. var now = new
		 * Date(); var newDate = DateAdd( "d ",5,now); ---------------
		 * DateAdd(interval,number,date) -----------------
		 */
		switch (interval) {
		case "y": // 加年
			date.setFullYear(date.getFullYear() + number);
			return date;
			break;
		case "q": // 加季度
			date.setMonth(date.getMonth() + number * 3);
			return date;
			break;
		case "m": // 加月
			date.setMonth(date.getMonth() + number);
			return date;
			break;
		case "w": // 加周
			date.setDate(date.getDate() + number * 7);
			return date;
			break;
		case "d": // 加日
			date.setDate(date.getDate() + number);
			return date;
			break;
		case "h": // 加小时
			date.setHours(date.getHours() + number);
			return date;
			break;
		case "m": // 加分钟
			date.setMinutes(date.getMinutes() + number);
			return date;
			break;
		case "s": // 加秒
			date.setSeconds(date.getSeconds() + number);
			return date;
			break;
		default: // 默认加天
			date.setDate(d.getDate() + number);
			return date;
			break;
		}
	},

	getTimeStr : function(addDayCount) {
		var datetime = new Date();
		datetime.setDate(datetime.getDate() + addDayCount);// 获取addDayCount天后的日期
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1;// js从0开始取
		var date = datetime.getDate();
		var hour = datetime.getHours();
		var minutes = datetime.getMinutes();
		//var second = datetime.getSeconds();

		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		//if (second < 10) {
		//	second = "0" + second;
		//}

		var time = year + "-" + month + "-" + date + " " + hour + ":" + minutes;
		//		+ ":" + second; // 2009-06-12 17:18:05
		// alert(time);
		return time;
	},

	/**
	 * 格式化日期
	 */
	formateDateSec : function(datetime, isDate) {
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1;// js从0开始取
		var date = datetime.getDate();
		var hour = datetime.getHours();
		var minutes = datetime.getMinutes();
		var second = datetime.getSeconds();

		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (second < 10) {
			second = "0" + second;
		}

		var time = null;

		if (isDate) {
			time = year + "-" + month + "-" + date; // 2009-06-12
		} else {
			time = year + "-" + month + "-" + date + " " + hour + ":" + minutes
					+ ":" + second; // 2009-06-12 17:18:05
		}
		// alert(time);
		return time;
	},

	dateParser : function(date) {
		if (!date)
			return new Date();

		var y = date.substring(0, 4);
		var m = date.substring(5, 7);
		var d = date.substring(8, 10);
		var h = date.substring(11, 14);
		var min = date.substring(15, 17);
		var sec = date.substring(18, 20);

		if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min)
				&& !isNaN(sec)) {
			return new Date(y, m - 1, d, h, min, sec);
		} else {
			return new Date();
		}
	},

	/**
	 * 格式化日期
	 * 
	 * @returns {String}
	 */
	formateDate : function(date, isDate) {
		var m;
		if (typeof (date) == "string") {
			var array = date.split("-");
			date = new Date(array[0], array[1], array[2].substr(0, 2));
			m = date.getMonth();
		} else if (!date) {
			date = new Date();
			m = date.getMonth() + 1;
		}
		var y = date.getFullYear();
		var d = date.getDate();
		var h = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		var str = y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
				+ ':' + (min < 10 ? ('0' + min) : min) + ':'
				+ (sec < 10 ? ('0' + sec) : sec);
		if (isDate) {
			str = y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d);
		}
		return str;
	},
	formatterDate : function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
				+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	}
}
