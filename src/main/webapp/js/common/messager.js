var messager={
		/**
		 * msg框
		 * @param msg		提示信息内容
		 * @param location 	位置
		 * 					屏幕位置 	123
		 * 							456
		 *  						789
		 */
		msg : function(location, msg){
			//alertMsg(msg);
			switch(location){
			case 3:
				if(undefined == msg || !msg){
					msg = "请选择一条记录信息"
				}
				$.messager.show({
					title:'提示信息',
					msg:msg,
					showType:'show',
					style:{
						left:'',
						right:0,
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
				break;
			case 9:
				if(undefined == msg || !msg){
					msg = "操作成功"
				}
				$.messager.show({
					title:'提示信息',
					msg:msg,
					showType:'show'
				});
				break;
			default:
				$.messager.show({
					title:'提示信息',
					msg:"操作成功",
					showType:'show'
				});
			}
		},
		/**
		 * alert框
		 * @param msg
		 */
		alert : function(msg){
			//alertMsg(msg, "alert", butTxt1);
			$.messager.alert('提示信息', msg, 'warning');
		},
		/**
		 * confirm框
		 * @param msg
		 * @param fn
		 */
		confirm : function(msg, fn, butTxt1, butTxt2, butTxt3){
			//alertMsg(msg, "confirm", fn, butTxt1, butTxt2, butTxt3);
		}
}

function alertMsg(msg, mode, fn, butTxt1, butTxt2, butTxt3) { //mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
    msg = msg || '';
    mode = mode || 0;
    var top = document.body.scrollTop || document.documentElement.scrollTop;
    var isIe = (document.all) ? true : false;
    var isIE6 = isIe && !window.XMLHttpRequest;
    var sTop = document.documentElement.scrollTop || document.body.scrollTop;
    var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
    var winSize = function(){
        var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
        // innerHeight获取的是可视窗口的高度，IE不支持此属性
        if (window.innerHeight && window.scrollMaxY) {
            xScroll = document.body.scrollWidth;
            yScroll = window.innerHeight + window.scrollMaxY;
        } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }

        if (self.innerHeight) {    // all except Explorer
            windowWidth = self.innerWidth;
            windowHeight = self.innerHeight;
        } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else if (document.body) { // other Explorers
            windowWidth = document.body.clientWidth;
            windowHeight = document.body.clientHeight;
        }

        // for small pages with total height less then height of the viewport
        if (yScroll < windowHeight) {
            pageHeight = windowHeight;
        } else {
            pageHeight = yScroll;
        }

        // for small pages with total width less then width of the viewport
        if (xScroll < windowWidth) {
            pageWidth = windowWidth;
        } else {
            pageWidth = xScroll;
        }

        return{
            'pageWidth':pageWidth,
            'pageHeight':pageHeight,
            'windowWidth':windowWidth,
            'windowHeight':windowHeight
        }
    }();
    //alert(winSize.pageWidth);
    //遮罩层
    var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#666;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
    styleStr += (isIe) ? "filter:alpha(opacity=80);" : "opacity:0.8;"; //遮罩层DIV
    var shadowDiv = document.createElement('div'); //添加阴影DIV
    shadowDiv.style.cssText = styleStr; //添加样式
    shadowDiv.id = "shadowDiv";
    //如果是IE6则创建IFRAME遮罩SELECT
    if (isIE6) {
        var maskIframe = document.createElement('iframe');
        maskIframe.style.cssText = 'width:' + winSize.pageWidth + 'px;height:' + (winSize.pageHeight + 30) + 'px;position:absolute;visibility:inherit;z-index:-1;filter:alpha(opacity=0);';
        maskIframe.frameborder = 0;
        maskIframe.src = "about:blank";
        shadowDiv.appendChild(maskIframe);
    }
    
    //弹出框
    var styleStr1;
    if (mode && (mode === "alert" || mode === "confirm")) {
    	styleStr1 = 'display:block;position:fixed;_position:absolute;left:' + (winSize.windowWidth / 2 - 200) + 'px;top:' + (winSize.windowHeight / 2 - 150) + 'px;_top:' + (winSize.windowHeight / 2 + top - 150)+ 'px;'; //弹出框的位置
    }else{
    	styleStr1 = 'display:block;position:fixed;_position:absolute;left:' + (winSize.windowWidth - 450) + 'px;top:15px;_top:' + (winSize.windowHeight / 2 + top - 150)+ 'px;'; //弹出框的位置
    }
    var alertBox = document.createElement('div');
    alertBox.id = 'alertMsg';
    alertBox.style.cssText = styleStr1;
    //创建弹出框里面的内容P标签
    var alertMsg_info = document.createElement('P');
    alertMsg_info.id = 'alertMsg_info';
    alertMsg_info.innerHTML = msg;
    alertBox.appendChild(alertMsg_info);
    if (mode === "alert" || mode === "confirm") {
    document.body.insertBefore(shadowDiv, document.body.firstChild); //遮罩层加入文档
    //创建按钮
    var btn1 = document.createElement('a');
    btn1.id = 'alertMsg_btn1';
    btn1.href = 'javas' + 'cript:void(0)';
    if(butTxt1){
    	btn1.innerHTML = '<cite>'+butTxt1+'</cite>';
    }else{
    	btn1.innerHTML = '<cite>确定</cite>';
    }
    btn1.onclick = function () {
        document.body.removeChild(alertBox);
        document.body.removeChild(shadowDiv);
        if(typeof fn == "function"){
        	fn(true);
        }
    };
    alertBox.appendChild(btn1);
        if (mode === "confirm") {
            var btn2 = document.createElement('a');
            btn2.id = 'alertMsg_btn2';
            btn2.href = 'javas' + 'cript:void(0)';
            if(butTxt2){
            	btn2.innerHTML = '<cite>'+butTxt2+'</cite>';
            }else{
            	btn2.innerHTML = '<cite>取消</cite>';
            }
            btn2.onclick = function () {
                document.body.removeChild(alertBox);
                document.body.removeChild(shadowDiv);
                if(typeof fn == "function"){
                	fn(false);
            	}
            };
            alertBox.appendChild(btn2);
            
            if(butTxt3){
            	var btn3 = document.createElement('a');
            	btn3.id = 'alertMsg_btn3';
            	btn3.href = 'javas' + 'cript:void(0)';
                if(butTxt3){
                	btn3.innerHTML = '<cite>'+butTxt3+'</cite>';
                }
                btn3.onclick = function () {
                    document.body.removeChild(alertBox);
                    document.body.removeChild(shadowDiv);
                    if(typeof fn == "function"){
                    	fn("-99");
                	}
                };
                alertBox.appendChild(btn3);
            }
        }
    }else{
    	isNullData($(alertBox),8)
    }
    document.body.appendChild(alertBox);
	
}

function isNullData(item, t) {
	if (t >= 0) {
		item.css({
			filter : 'alpha(opacity=' + 10 * t + ')', /* ie 有效 */
			'-moz-opacity' : 0.1 * t, /* Firefox 有效 */
			opacity : 0.1 * t
		/* 通用，其他浏览器 有效 */
		});
		//setTimeout(isNullData, 100, item, t - 0.3);
		setTimeout(function(){isNullData(item, t - 0.3)}, 100 );
	} else {
		item.remove();
	}
}