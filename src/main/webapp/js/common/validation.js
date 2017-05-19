/**
 * 验证信息
 */

//验证输入框
$.extend($.fn.validatebox.defaults.rules, {

//汉字验证
    isChs: {
        validator: function (value) {
            var reg = /^[\u4E00-\u9FA5]+$/;
            return reg.test(value);

        },
        message: '请输入汉字'
    },

    isTemp : {
        validator: function (value) {
           var begin = value.lastIndexOf("{");
           var end = value.lastIndexOf("}");
            if(begin > end ){
               value = value.substring(0,begin);
            }
            $(this).val(value);
            return true;
        },
         message: '去掉不合规的模板参数'
    },

    isPlate: {
        validator: function (value) {
           /* var regOne = /^[\u4E00-\u9FA5][a-z_A-Z][\da-z_A-Z^O^I]{5}$/;
            var regTwo = /^[\u4E00-\u9FA5][aA]$/;
            var regThree = /^[\u4E00-\u9FA5][a-z_A-Z][\da-z_A-Z]{5}[\u4E00-\u9FA5]{1,2}$/;
            return regOne.test(value) || regTwo.test(value) || value.endWith("未上牌") || (regThree.test(value) && (value.endWith("临") ||  value.endWith("学") ||
            		value.endWith("公安")) && (value.toString().length >7 && value.toString().length <= 9) 
            		&& !value.endWith("学学") && !value.endWith("学学") && !value.endWith("学临") &&!value.endWith("临学"));*/
        	 var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[a-zA-Z0-9]{4}[a_zA-Z0-9挂学警港澳]{1}$/;
        	 var regTwo = /^[\u4E00-\u9FA5][aA]$/;
        	 return express.test(value) || regTwo.test(value) || value.endWith("未上牌");
        },
        message: '请输入正确的车牌号'
    },

    isTel: {
        validator: function (value) {
            //var regTel = /^(0[0-9]{2,3})?([2-9][0-9]{5,8})+(-[0-9]{1,4})?$/;
            //var regPhone = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        	//var regTel = /(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/;
        	//var regTel = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
            //var regPhone = /^1[34578]\d{9}$/;
            var regTel = /^((0\d{2,3}-\d{7,8})|(1[34578]\d{9}))$/;
            return regTel.test(value);
            //|| regPhone.test(value);

        },
        message: '请输入正确的固定电话格式如0751-88888888或手机号'
    },

    //判断日期选择
    isBirth: {
        validator: function (value) {
            var date = Date.parse(value);
            var now = new Date();
            if (date > now.getTime()) {
                return false;
            }
            return true;
        },
        message: '你选择的日期超过当前日期,请重新选择'
    },

    isChsEng: {
        validator: function (value) {
            var regEng = /^[a-zA-Z]+$/;
            var regChs = /^[\u4E00-\u9FA5]+$/;
            return (regEng.test(value) || regChs.test(value));
        },
        message: '请输入汉字、大写或者小写字母'
    },
//数字验证
    isNum: {
        validator: function (value) {
            var reg = /^[0-9]+$/;
            return reg.test(value);
        },
        message: '请输入数字'
    },

//非负浮点数字验证
    isDouble: {
        validator: function (value) {
            var reg = /^[-\+]?^\d+(\.\d+)+$/;
            return reg.test(value);
        },
        message: '请输入双精度格式'
    },

    //数字验证或非负浮点数字验证
    isNumDou: {
        validator: function (value) {
            var regNum = /^[0-9]+$/;
            var regDou = /^[-\+]?^\d+(\.\d+)+$/;
            return regNum.test(value) || regDou.test(value);
        },
        message: '请输入数字或者双精度浮点数'
    },

//大小写字母验证
    isEngcase: {
        validator: function (value) {
            var reg = /^[a-zA-Z]+$/;
            return reg.test(value);
        },
        message: '请输入大写或者小写字母'
    },

//由数字和26个英文字母组成的字符串
    isNumeng: {
        validator: function (value) {
            var reg = /^[A-Za-z0-9]+$/;
            return reg.test(value);
        },
        message: '请输入字母或者数字'
    },
    
  //由数字,符号和26个英文字母组成的字符串
    isNuMengFuhao: {
        validator: function (value) {
            var reg = /^[A-Za-z0-9*-.%&#$@!+=|]+$/;
            return reg.test(value);
        },
        message: '请输入字母符号或者数字'
    },
//身份证号只能由15-18位数字或字母组成
    isIdcard: {
        validator: function (value) {
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d[17]|X|x)+$/;
            return reg.test(value);
        },
        message: '请输入15或18位身份证号'
    },

//邮编只能6位数
    isZip: {
        validator: function (value) {
            var reg = /^[1-9][0-9]{5}$/;
            return reg.test(value);
        },
        message: '请输入正确的邮编'
    },

//日期验证平年、闰年，大月小月
    isDay: {
        validator: function (value) {
            var reg = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)+$))/;
            return reg.test(value);
        },
        message: '请输入正确日期'
    },

//手机号码验证
    isPhone: {
        validator: function (value) {
            var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            return reg.test(value);
        },
        message: '请输入正确手机号码'
    },

//输入字符最大长度验证
    maxLength: {
        validator: function (value, param) {
            return value.length <= param[0];
        },
        message: '字符长度超过{0}位'
    },

//输入字符最小长度验证
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '字符长度少于{0}位'
    },

//输入字符长度验证
    isLength: {
        validator: function (value, param) {
            return value.length == param[0];
        },
        message: '字符长度必须为{0}位'
    },

//最大值验证
    maxValue: {
        validator: function (value, param) {
            return value <= param[0];
        },
        message: '不能大于最大值{0}'
    },

//最小值验证
    minValue: {
        validator: function (value, param) {
            return value >= param[0];
        },
        message: '不能小于最大值{0}'
    },

//最小值验证
    isNotEmpty: {
        validator: function (value) {
            return (value != null || value != undefined || value != "");
        },
        message: '该输入项为必输项'
    },

    /**
     * 相等验证
     */
    equals: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '字段值不匹配.'
    }

});

String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	}
