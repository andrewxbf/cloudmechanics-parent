var common = {
		
		/**
		 * 当前表格中新增一条记录
		 */
		insertRow : function(_domId,_values){
			$("#"+_domId).datagrid("insertRow",{
				index: 0,	// 索引从0开始
				row: _values
			});
			var crows = $("#"+_domId).datagrid("getRows");
			$("#"+_domId).datagrid("loadData",crows);
		},
		/**
		 * 更新表格当前选中行
		 */
		updateCurrentRow : function(_domId, _values) {
			var datagrid = $("#" + _domId);
			if (datagrid) {
				var rowData = datagrid.datagrid("getSelected");
				if (rowData) {
					var index = datagrid.datagrid("getRowIndex", rowData);
					rowData = this.refreshData(rowData, _values);
					datagrid.datagrid("updateRow", {
						index : index,
						row : rowData
					});
					datagrid.datagrid("refreshRow", index);
				}
			}
		},

		/**
		 * 更新表格数据行
		 */
		updateRowData : function(_domId, _rowData, _values) {
			var datagrid = $("#" + _domId);
			if (datagrid) {
				if (_rowData) {
					var index = datagrid.datagrid("getRowIndex", _rowData);
					_rowData = this.refreshData(_rowData, _values);
					datagrid.datagrid("updateRow", {
						index : index,
						row : _rowData
					});
					datagrid.datagrid("refreshRow", index);
				}
			}
		},
		
		/**
		 * 删除表格数据行
		 */
		deleteRowData : function(_domId, _rowData) {
			var datagrid = $("#" + _domId);
			if (datagrid) {
				if (_rowData) {
					var index = datagrid.datagrid("getRowIndex", _rowData);
					datagrid.datagrid("deleteRow", index);
				}
			}
		},

		/**
		 * 更新表格数据行
		 */
		updateRowIndex : function(_domId, _index, _values) {
			var datagrid = $("#" + _domId);
			if (datagrid) {
				//var rowData = datagrid.datagrid("selectRow", _index);
				//if (rowData) {
					//rowData = this.refreshData(rowData, _values);
					datagrid.datagrid("updateRow", {
						index : _index,
						row : _values
					});
					datagrid.datagrid("refreshRow", _index);
				}
			//}
		},

		/**
		 * 刷新数据集
		 */
		refreshData : function(_source, _values) {
			if (_source && _values) {
				for ( var v in _values) {
					_source[v] = _values[v];
				}
			}
			return _source;
		},
		
		/**
		 * 打开窗口
		 * @param _title
		 * @param _href
		 * @param _width
		 * @param _height
		 */
		openWin: function(_title, _href, _width, _height, _domId , _param, oopts ){
			var _winDom, _method="get";
			if(_domId){
				_winDom = "<div id="+_domId+"></div>";
			}else{
				_winDom = "<div></div>";
			}
			//$("body").append(_winDom);
			var _open_win = $(_winDom);
			var opt = {
				title: _title,
				href: _href,
				width: _width,    
			    height: _height,
			    modal: true,
			    minimizable : false,
				maximizable : false,
                collapsible : false,
				resizable : false,
				onClose: function(){
					_open_win.window("destroy");
				},
				onLoad: function(){
					layout.showMenu($(this).find("._load"));
				}
			}
			if(_param){
				_method = "post";
				opt['queryParams'] = _param;
			}
			opt['method'] = _method;
			
			if(oopts){
				for(var oo in oopts){
					opt[oo] = oopts[oo];
				}
			}
			
			_open_win.window(opt);
		},

        showMessageBox: function(_title, _href, _domId , _param, oopts ){
            var _winDom, _method="get";
            if(_domId){
                _winDom = "<div id="+_domId+"></div>";
            }else{
                _winDom = "<div></div>";
            }
            //$("body").append(_winDom);
            var _open_win = $(_winDom);
            var opt = {
                title: _title,
                href: _href,
                width: document.body.clientWidth*0.7 ,
                height: document.body.clientHeight*0.9 ,
                modal: true,
                minimizable : false,
                maximizable : false,
                left:document.body.clientWidth*0.3,
                top:document.body.clientHeight*0.1,
                inline:false,
                draggable : true,
                collapsible : false,
                resizable : false,
                onClose: function(){
                    _open_win.window("destroy");
                },
                onLoad: function(){
                    layout.showMenu($(this).find("._load"));
                }
            }
            if(_param){
                _method = "post";
                opt['queryParams'] = _param;
            }
            opt['method'] = _method;

            if(oopts){
                for(var oo in oopts){
                    opt[oo] = oopts[oo];
                }
            }

            _open_win.window(opt);
        },
		
		closeWin: function(_domId){
			$("#"+_domId).panel("close");
		},
		
		/**
		 * 创建数据表格
		 * @param _domId
		 * @param _url
		 * @param _menu
		 * @param _searchForm
		 * @param oopts
		 */
		createDataGrid: function(_domId, _url, _menu, _searchForm, oopts, _isUnLoad){
			var _grid = $("#"+_domId);
			var opts = {
				url: _url,
				fit: true,
				autoRowHeight: false,
				nowrap: true,
				striped: true,
				rownumbers: true,
				multiSort : true,
				flag: false,
				onHeaderContextMenu : function(e, field) {
					e.preventDefault();
					if (!cmenu) {
						createColumnMenu();
					}
					cmenu.menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			};
			if(_menu){
				opts.toolbar = '#' + _menu;
			}
			if(_searchForm) {
				opts.search = _searchForm;
			}
			if(_isUnLoad){
				opts.tempInit = false;
			}else{
				opts.tempInit = true;
			}
			opts.loader = function(param,success,error){
				if(opts.flag){
					if(opts.tempInit){
						if(!opts.url){
							return false;
						}
						$.ajax({
							type:opts.method,
							url:opts.url,
							data:param,
							dataType:"json",
							success:function(data){
								if(data.status == 1){
									if(data.rows.length ==0){
										_grid.datagrid("loaded");
										success(data);
										_grid.datagrid('getPanel').find('.datagrid-view2').find('.datagrid-body').append("<div class='nodata'>未查询到数据</div>");
									}else{
										success(data);
									}
								}else{
									_grid.datagrid("loaded");
									messager.alert(data.messagecode);
								}
							}
						})
					}else{
						opts.tempInit = true;
						return false;
					}
				}else{
					opts.flag = true;
					return false;
				}
			};
			if(oopts){
				for(var oo in oopts){
					opts[oo] = oopts[oo];
				}
			}

			_grid.datagrid(opts);
			
			// -------------------------------字段筛选-----------------------------------
			var cmenu;
			function createColumnMenu() {
				cmenu = $('<div/>').appendTo('body');
				cmenu.menu({
					onClick : function(item) {
						if (item.iconCls == 'icon-ok') {
							_grid.datagrid('hideColumn', item.name);
							cmenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-empty'
							});
						} else {
							_grid.datagrid('showColumn', item.name);
							cmenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-ok'
							});
						}
						common.resizeFilter(_grid, null, 10);
						_grid.datagrid('fitColumns');
						common.resizeFilter(_grid);
					}
				});
				var fields = _grid.datagrid('getColumnFields');
				for ( var i = 0; i < fields.length; i++) {
					var field = fields[i];
					var col = _grid.datagrid('getColumnOption', field);
					if (col.hidden == true)
						continue;
					cmenu.menu('appendItem', {
						text : col.title,
						name : field,
						iconCls : 'icon-ok'
					});
				}
			}
			// -------------------------------字段筛选-----------------------------------
			common.loadDateGrid(_domId);
		},
		
		/**
		 * 创建行内编辑数据表格
		 * @param _domId
		 * @param _url
		 * @param _menu
		 * @param _searchForm
		 * @param oopts
		 */
		createEDataGrid: function(_domId, _url, _menu, _searchForm, oopts, _isUnLoad){
			var _grid = $("#"+_domId);
			var opts = {
				url: _url,
				fit: true,
				autoRowHeight: false,
				nowrap: true,
				rownumbers: true,
				multiSort : true,
				flag: false,
				onHeaderContextMenu : function(e, field) {
					e.preventDefault();
					if (!cmenu) {
						createColumnMenu();
					}
					cmenu.menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			};
			if(_menu){
				opts.toolbar = '#' + _menu;
			}
			if(_searchForm) {
				opts.search = _searchForm;
			}
			if(_isUnLoad){
				opts.tempInit = false;
			}else{
				opts.tempInit = true;
			}
			opts.loader = function(param,success,error){
				if(opts.flag){
					if(opts.tempInit){
						if(!opts.url){
							return false;
						}
						$.ajax({
							type:opts.method,
							url:opts.url,
							data:param,
							dataType:"json",
							success:function(data){
								if(data.status == 1){
									if(data.rows.length ==0){
										_grid.datagrid("loaded");
										success(data);
										_grid.datagrid('getPanel').find('.datagrid-view2').find('.datagrid-body').append("<div class='nodata'>未查询到数据</div>");
									}else{
										success(data);
									}
								}else{
									_grid.datagrid("loaded");
									messager.alert(data.messagecode);
								}
							}
						})
					}else{
						opts.tempInit = true;
						return false;
					}
				}else{
					opts.flag = true;
					return false;
				}
			};
			if(oopts){
				for(var oo in oopts){
					opts[oo] = oopts[oo];
				}
			}
			_grid.edatagrid(opts);
			
			// -------------------------------字段筛选-----------------------------------
			var cmenu;
			function createColumnMenu() {
				cmenu = $('<div/>').appendTo('body');
				cmenu.menu({
					onClick : function(item) {
						if (item.iconCls == 'icon-ok') {
							_grid.datagrid('hideColumn', item.name);
							cmenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-empty'
							});
						} else {
							_grid.datagrid('showColumn', item.name);
							cmenu.menu('setIcon', {
								target : item.target,
								iconCls : 'icon-ok'
							});
						}
						common.resizeFilter(_grid, null, 10);
						_grid.datagrid('fitColumns');
						common.resizeFilter(_grid);
					}
				});
				var fields = _grid.datagrid('getColumnFields');
				for ( var i = 0; i < fields.length; i++) {
					var field = fields[i];
					var col = _grid.datagrid('getColumnOption', field);
					if (col.hidden == true)
						continue;
					cmenu.menu('appendItem', {
						text : col.title,
						name : field,
						iconCls : 'icon-ok'
					});
				}
			}
			// -------------------------------字段筛选-----------------------------------
			common.loadDateGrid(_domId);
		},
		
		// -------------------------------调节列宽-----------------------------------
		resizeFilter : function(target, field, width) {
			var dg = $(target);
			var header = dg.datagrid('getPanel').find('div.datagrid-header');
			var ff = field ? header.find('input.datagrid-filter[name="' + field
					+ '"]') : header.find('input.datagrid-filter');
			ff.each(function() {
				var name = $(this).attr('name');
				var col = dg.datagrid('getColumnOption', name);
				var cc = $(this).closest('div.datagrid-filter-c');
				var btn = cc.find('a.datagrid-filter-btn');
				if (width != undefined) {
					this.filter.resize(this, width);
				} else {
					this.filter.resize(this, 10);
					this.filter.resize(this, cc.width() - btn._outerWidth());
				}
			});
		},
		// -------------------------------调节列宽-----------------------------------
		
		/**
		 * 查询按钮查询数据
		 * @param _domId
		 */
		loadDateGrid : function(_domId, _param) {
			var _grid = $("#" + _domId);
			var opts = _grid.datagrid("options");
			var _searchFrom;
			if (opts && opts.search) {
				_searchFrom = $("#" + opts.search);
			}
			var param;
			if (_searchFrom) {
				param = this.serializeObject(opts.search);
			} else {
				if(_param){
					param = _param;
				}else{
					param = {};
				}
			}
			_grid.datagrid("load",param);
		},
		
		
		/**
		 * 将form表单内的元素序列化为对象，扩展Jquery的一个方法
		 */
		serializeObject : function(_form) {
			var o = {};
			var form = $("#" + _form).form();
			$.each(form.serializeArray(), function(index) {
				var attr = this['name'];
				if (attr) {
					if (o[attr]) {
						o[attr] = o[attr] + "," + this['value'];
					} else {
						o[attr] = this['value'];
					}
				}
			});
			return o;
		},
		
		serializeAllObject : function(_form) {
			var o = {};
			var form = $("#" + _form).form();
			$.each(form.serializeArray(), function(index) {
				var attr = this['name'];
				if (attr) {
					if (o[attr]) {
						o[attr] = o[attr] + "," + this['value'];
					} else {
						if(this['value']){
							o[attr] = this['value'];
						}else{
							o[attr] = ' ';
						}
						
					}
				}
			});
			return o;
		},

        setFormReadonly : function(_form){
            var _inDatas = $("#" + _form).find("[textboxname]");
            for ( var i = 0; i < _inDatas.length; i++) {
                var _this = $(_inDatas[i]);
                var value = true;
                if (_this.hasClass("easyui-textbox")) {
                    _this.textbox("readonly", value);
                } else if (_this.hasClass("easyui-numberbox")) {
                    _this.numberbox("readonly", value);
                } else if (_this.hasClass("easyui-datebox")) {
                    _this.datebox("readonly", value);
                } else if (_this.hasClass("easyui-datetimebox")) {
                    _this.datetimebox("readonly", value);
                } else if (_this.hasClass("easyui-combobox")) {
                    _this.combobox("readonly", value);
                } else {
                    continue;
                }
            }
        },

		load : function(){
			$("<div class=\"datagrid-mask\" style=\"font-size:12px\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
		     $("<div class=\"datagrid-mask-msg\" style=\"font-size:12px\"></div>").html("页面正在渲染，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
		},

		disLoad : function(){
			$(".datagrid-mask").remove();
		     $(".datagrid-mask-msg").remove();
		},

		addRow: function(_domId){
			var _grid = $("#"+_domId);
			_grid.edatagrid("addRow");
		},
		editRow: function(_domId){
			var _grid = $("#"+_domId);
			var row = _grid.datagrid("getSelected");
			var index = _grid.datagrid("getRowIndex",row);
			_grid.edatagrid("editRow",index);
		},
		saveRow: function(_domId){
			var _grid = $("#"+_domId);
			_grid.edatagrid("saveRow");
		},
		cancelRow: function(_domId){
			var _grid = $("#"+_domId);
			_grid.edatagrid("cancelRow");
		},
		cancelAllRow: function(_domId){
			var _grid = $("#"+_domId);
			_grid.edatagrid("cancelAllRow");
		},
		destroyRow: function(_domId){
			var _grid = $("#"+_domId);
			_grid.edatagrid("destroyRow");
		},
        //添加配件按钮事件
        saveTable: function (_domId) {
            var rowCnt = $("#"+_domId).datagrid("getRows").length;
            for (var i = 0; i < rowCnt; i++) {
                $("#"+_domId).datagrid("endEdit", i);
            }
        },
		
		// -------------------------------自动set数据到文本框、数字框、时间框、日期时间框-----------------------------------
		initialForm : function(id, data) {
			var _inDatas = $("#" + id).find("[textboxname]");
			for ( var i = 0; i < _inDatas.length; i++) {
				var _this = $(_inDatas[i]);
				if (data != undefined && data != null) {
					var value = data[_this.attr("textboxname")];
					if (!value&&value!=0) {
						value = "";
					}
					if (_this.hasClass("easyui-textbox")) {
						_this.textbox("setValue", value);
					} else if (_this.hasClass("easyui-numberbox")) {
						_this.numberbox("setValue", value);
					} else if (_this.hasClass("easyui-numberspinner")) {
                        _this.numberspinner("setValue", value);
                    } else if (_this.hasClass("easyui-datebox")) {
						_this.datebox("setValue", value);
					} else if (_this.hasClass("easyui-datetimebox")) {
						_this.datetimebox("setValue", value);
					} else if (_this.hasClass("easyui-combobox")) {
						_this.combobox("select", value);
					} else {
						continue;
					}
				}
			}
			var _hiddens = $("#" + id).find("input[type='hidden']");
			for ( var i = 0; i < _hiddens.length; i++) {
				var name = $(_hiddens[i]).attr("name");
				var value = data[name];
				if (value !== undefined) {
					$(_hiddens[i]).val(value);
				}
			}

			var _text = $("#" + id).find("input[class='Wdate']");
			if (_text != null && _text.length > 0) {
				for ( var i = 0; i < _text.length; i++) {
					var name = $(_text[i]).attr("name");
					var value = data[name];
					if (value !== undefined) {
						$(_text[i]).val(value);
					}
				}
			}
		}
}

	var STYLE = {
		checkbox : {
			cursor : "pointer",
			background : "transparent url('data:image/gif;base64,R0lGODlhDwAmAKIAAPr6+v///+vr68rKyvT09Pj4+ICAgAAAACH5BAAAAAAALAAAAAAPACYAAANuGLrc/mvISWcYJOutBS5gKIIeUQBoqgLlua7tC3+yGtfojes1L/sv4MyEywUEyKQyCWk6n1BoZSq5cK6Z1mgrtNFkhtx3ZQizxqkyIHAmqtTsdkENgKdiZfv9w9bviXFxXm4KP2g/R0uKAlGNDAkAOw==') no-repeat center top",
			verticalAlign : "middle",
			height : "19px",
			width : "18px",
			display : "block"
		},
		span : {
			"float" : "left",
			display : "block",
			margin : "0px 4px",
			marginTop : "5px"
		},
		label : {
			marginTop : "4px",
			marginRight : "8px",
			display : "block",
			"float" : "left",
			fontSize : "16px",
			cursor : "pointer"
		}
	};

	function rander(target) {
		var jqObj = $(target);
		jqObj.css('display', 'inline-block');
		var Checkboxs = jqObj.children('input[type=checkbox]');
		Checkboxs.each(function() {
			var jqCheckbox = $(this);
			var jqWrap = $('<span/>').css(STYLE.span);
			var jqLabel = $('<label/>').css(STYLE.label);
			var jqCheckboxA = $('<a/>').data('lable', jqLabel).css(
					STYLE.checkbox).text(' ');
			var labelText = jqCheckbox.data('lable', jqLabel).attr('label');
			jqCheckbox.hide();
			jqCheckbox.after(jqLabel.text(labelText));
			jqCheckbox.wrap(jqWrap);
			jqCheckbox.before(jqCheckboxA);
			if (jqCheckbox.prop('checked')) {
				jqCheckboxA.css('background-position', 'center bottom');
			}

			jqLabel.click(function() {
				(function(ck, cka) {
					ck.prop('checked', !ck.prop('checked'));
					var y = 'top';
					if (ck.prop('checked')) {
						y = 'bottom';
					}
					cka.css('background-position', 'center ' + y);
				})(jqCheckbox, jqCheckboxA);
			});

			jqCheckboxA.click(function() {
				$(this).data('lable').click();
			});
		});
	}

	$.fn.checkbox = function(options, param) {
		if (typeof options == 'string') {
			return $.fn.checkbox.methods[options](this, param);
		}

		options = options || {};
		return this.each(function() {
			if (!$.data(this, 'checkbox')) {
				$.data(this, 'checkbox', {
					options : $.extend({}, $.fn.checkbox.defaults, options)
				});
				rander(this);
			} else {
				var opt = $.data(this, 'checkbox').options;
				$.data(this, 'checkbox', {
					options : $.extend({}, opt, options)
				});
			}
		});
	};

	function check(jq, val, check) {
		var ipt = jq.find('input[value=' + val + ']');
		if (ipt.length) {
			ipt.prop('checked', check).each(function() {
				$(this).data('lable').click();
			});
		}
	}

	$.fn.checkbox.methods = {
		getValue : function(jq) {
			var checked = jq.find('input:checked');
			var val = {};
			checked.each(function() {
				var kv = val[this.name];
				if (!kv) {
					val[this.name] = this.value;
					return;
				}

				if (!kv.sort) {
					val[this.name] = [ kv ];
				}
				val[this.name].push(this.value);
			});
			return val;
		},
		check : function(jq, vals) {
			if (vals && typeof vals != 'object') {
				check(jq, vals);
			} else if (vals.sort) {
				$.each(vals, function() {
					check(jq, this);
				});
			}
		},
		unCheck : function(jq, vals) {
			if (vals && typeof vals != 'object') {
				check(jq, vals, true);
			} else if (vals.sort) {
				$.each(vals, function() {
					check(jq, this, true);
				});
			}
		},
		checkAll : function(jq) {
			jq.find('input').prop('checked', false).each(function() {
				$(this).data('lable').click();
			});
		},
		unCheckAll : function(jq) {
			jq.find('input').prop('checked', true).each(function() {
				$(this).data('lable').click();
			});
		}
	};

	$.fn.checkbox.defaults = {
		style : STYLE
	};

	if ($.parser && $.parser.plugins) {
		$.parser.plugins.push('checkbox');
	}


