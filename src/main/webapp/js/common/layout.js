$(function(){
		$("._menu").click(function(){
			var _content = $(this).find("._content");
			var _sub_ul = $(this).nextAll();
			if(_content.hasClass("_content_plus")){
				_sub_ul.show(1000);
				_content.removeClass("_content_plus");
				_content.addClass("_content_minus");
			}else if(_content.hasClass("_content_minus")){
				_sub_ul.hide();
				_content.removeClass("_content_minus");
				_content.addClass("_content_plus");
			}
		});
		
		//$("._sub_menu").click(function(){
		//	var _sub_menu = $(this);
		//	$(".selected").removeClass("selected");
		//	$(this).addClass("selected");
		//	$("#_layout_center").panel({
		//		href: _sub_menu.attr("url"),
		//		onLoad: function(){
		//			//layout.onLoadShow($("._sub_north").find("form"));
		//			layout.showMenu($("._load"));
		//		}
		//	});
		//});

		$(".top-center li").click(function(){
			$(this).addClass("li_cur").siblings().removeClass("li_cur");
		});
		
		//system.loadBasisArea();
	});

var layout = {
		
	/**
	 * 查询条件显示
	 */
	showMenu: function(_this){
		var $button = $(_this); //被点击的按键对象
		if($button.length){
			var $form = $button.closest("form"); //按键所在的form
			if($form.length){
				var $_north = $form.closest("._sub_north"); //form所在的头部
				if($_north.length){
					var $_center = $_north.closest(".easyui-layout").find("._sub_center"); //form关联的中间部
					if($_center.length){
						var _north = $_north.panel("panel"); //子页面头部
						var _center = $_center.panel("panel"); //子页面中间
						var _old_north_height = _north.height(); //旧头部高
						var _new_center_height, _north_height,_center_height;  //新中间高  头部高  中间高
						//$form.find("tr:eq(1)").addClass("trSeparate");
						if($form.hasClass("_isShow")){  //判断form是否有_isShow样式
							$form.find(".padtr").removeClass("trSeparate");
							$form.removeClass("_isShow"); // 去除form _isShow样式
							$form.find("tr:gt(0)").hide(); //隐藏第一行之后的查询条件
							_north_height = _north.height(); //头部高
							_center_height = _center.height(); //中间高
						}else{
							$form.find(".padtr").addClass("trSeparate");
							$form.addClass("_isShow");
							$form.find("tr:gt(0)").show(1000);
							_north_height = _north.height();
							_center_height = _center.height();
						}
						$.parser.parse($form); //渲染查询条件 
						_new_center_height = _center_height+_old_north_height-_north_height; //计算新的中间高
						$_center.panel("resize",{top:_north_height-1,height:_new_center_height-1}); //调整中间面板
					}
				}
			}
		}
	},
	
	/**
	 * 加载页面时查询条件隐藏
	 */
	onLoadShow: function(_form){
		//var $button = $(_this); //被点击的按键对象
		var $form = $(_form); //按键所在的form
		var $_north = $form.closest("._sub_north"); //form所在的头部
		var $_center = $_north.closest(".easyui-layout").find("._sub_center"); //form关联的中间部
		var _north = $_north.panel("panel"); //子页面头部
		var _center = $_center.panel("panel"); //子页面中间
		var _old_north_height = _north.height(); //旧头部高
		var _new_center_height, _north_height,_center_height;  //新中间高  头部高  中间高
		if($form.hasClass("_isShow")){  //判断form是否有_isShow样式
			$form.removeClass("_isShow"); // 去除form _isShow样式
			$form.find("tr:gt(0)").hide(); //隐藏第一行之后的查询条件
			_north_height = _north.height(); //头部高
			_center_height = _center.height(); //中间高
		}
		$.parser.parse($form); //渲染查询条件 
		_new_center_height = _center_height+_old_north_height-_north_height; //计算新的中间高
		$_center.panel("resize",{top:_north_height-1,height:_new_center_height}); //调整中间面板
	},
	
	/**
	 * 重置按钮
	 * @param _this
	 */
	reset: function(_this){
		var $button = $(this); //被点击的按键对象
		var $form = $button.closest("form"); //按键所在的form
		$form.form("reset");
	}
}

