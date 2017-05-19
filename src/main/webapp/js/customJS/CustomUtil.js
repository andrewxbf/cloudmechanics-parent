var customUtil = {
	/**
	 * 加载车系/车型
	 * 
	 * @param {}
	 *            _series 车系下拉框ID
	 * @param {}
	 *            _type 车型下拉框ID
	 * @param {}
	 *            _isComb 是否显示全部
	 * @param {}
	 *            _brandId 加载车系时候品牌ID
	 * @param {}
	 *            _brandCode 加载车系时候品牌代码
     * @param _seriesOpts 车系下拉框方法参数
	 */
	loadSeriesModels : function(_series, _type, _isComb, _brandId,_seriesOpts) {
		// 加载车系下拉框
		var url = "auto/querySeriesCodeName.do";
		var param = {};
		if (_brandId) {
			param = {
				BRAND_ID : _brandId
			};
		}
		var loadItems = system.loadComboItems;
		var resetItems = system.resetComboItems;
		var opts = null;
		if (_series) {
			opts = {
				onSelect : function(e) {
					if (e && e != undefined && e != null && e.COMBO_KEY != "") {
						url = "auto/queryModelCodeName.do";
						param = {
							SERIES_CODE : e.COMBO_KEY
						};
						// var copts = null;
						loadItems(_type, url, param, null, _isComb);
					} else {
						resetItems(_type, _isComb);
					}
				},
				onChange : function() {
					resetItems(_type, _isComb);
				}
			};
		}
        if(_seriesOpts){
            for(var oo in _seriesOpts){
                opts[oo] = _seriesOpts[oo];
            }
        }
		loadItems(_series, url, param, opts, _isComb);
		if (_type && _isComb)
			resetItems(_type, _isComb);
	},

	/**
	 * 只查询OEM下发的车系并只显示code的下拉框
	 * @param _series
	 * @param _type
	 * @param _isComb
	 * @param _brandId
	 * @param _seriesOpts
	 */
	loadOemSeriesModelsCode : function(_series, _type, _isComb, _isOem,_seriesOpts) {
		// 加载车系下拉框
		var url = "auto/querySeriesCode.do";
		var param = {};
		if (_isOem != null) {
			param = {
				OEM_FLAG : _isOem===true ? 1 : 0
			};
		}
		var seriesId = null;
		var loadItems = system.loadComboItems;
		var resetItems = system.resetComboItems;
		var opts = null;
		if (_series) {
			opts = {
				onSelect : function(e) {
					if(seriesId == null){
						if(e != undefined && e != null && e != null && e.COMBO_KEY != ""){
							seriesId = e.COMBO_KEY;
						}
					}else{
						if(e != undefined && e != null && e != null && e.COMBO_KEY != "" && seriesId == e.COMBO_KEY){
                               return;
						}else{
							seriesId = e.COMBO_KEY;
						}
					}
					if (e && e != undefined && e != null && e.COMBO_KEY != "") {
						url = "auto/queryModelCodeName.do";
						param = {
							SERIES_CODE : e.COMBO_KEY
						};
						// var copts = null;
						loadItems(_type, url, param, null, _isComb);
					} else {
						resetItems(_type, _isComb);
					}
				},
				onChange : function(newVal,oldVal) {
					if(newVal != null && oldVal != null && newVal == oldVal){
						return;
					}
					resetItems(_type, _isComb);
				}
			};
		}
        if(_seriesOpts){
            for(var oo in _seriesOpts){
                opts[oo] = _seriesOpts[oo];
            }
        }
		loadItems(_series, url, param, opts, _isComb);
		if (_type && _isComb)
			resetItems(_type, _isComb);
	},
	// 反选适用车型
	selectSuitedModels : function(_series, _type, _seriesCode, _typeCode) {
		$("#" + _series).combobox("select", _seriesCode);
		$("#" + _type).combobox("select", _typeCode);
	},

	/**
	 * 将status值0|1翻译成已停用|已启用
	 */
	StatusName: function (value) {
		if (value === 1) {
			return "停用";
		} else {
			return "启用";
		}
	},
	/**
	 * 布尔型字段显示复选框 author : lihj
	 */
	ShowCheckBox : function(value) {
		if (value == 1) {
			return "<span class='easyui-checkbox'><input name='' type='checkbox' disabled='true' checked='checked' /></span>";
		} else if (value == 0) {
			return "<span class='easyui-checkbox'><input type='checkbox' disabled='true'/></span>";
		} else /*
				 * if(!value || value == undefined || value == '' || value ==
				 * null)
				 */{
			return value;
		}

	},
	/**
	 * 键盘回车事件
	 */
	EnterKeyPress : function(_fromId, _clickId, callback) {
		customUtil.KeyPress(13, _fromId, _clickId, callback);
	},
	/**
	 * 键盘事件注册
	 */
	KeyPress : function(_keyId, _fromId, _clickId, callback) {
		if (_keyId && _fromId && _clickId) {
			var $form = $("#" + _fromId); // 所有的form元素
			$form.keypress(function(e) { // 这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
				var key = e.which; // e.which是按键的值
				if (key == _keyId) {
					$("#" + _clickId).trigger("click");
					if (callback) {
						callback();
					}
				}
			})

		}
	},
	uploadfile : function(_fileElementId, _domId) {
		var filePathStr = $("#"+_fileElementId).val();
		if (!(/[.]/.exec(filePathStr))){
			messager.msg(3, "文件格式不正确,请重新上传");
			return
		}
		var _fileName = filePathStr.replace(/.*(\/|\\)/, "");
		//置灰“添加”按钮，避免连续上传
		$("#"+_fileElementId).parent().find("a").linkbutton({text: '等待中…'});
        //$("#"+_fileElementId).attr("readonly","readonly");

        $.ajaxFileUpload({
			url : "basicdata/fileupload.do", // 用于文件上传的服务器端请求地址
			secureuri : false, // 是否需要安全协议，一般设置为false
			fileElementId : _fileElementId, // 文件上传域的ID
			dataType : 'json', // 返回值类型 一般设置为json
			success : function(data) // 服务器成功响应处理函数
			{
				if (data.ATTACHMENT_ID != "") {
					data.ATTACHMENT_NAME = _fileName;
					common.insertRow(_domId, data);
				} else {
					messager.msg(3, "上传失败");
				}
			},
			error : function(data) {
				messager.msg(3, "上传失败");
			},
			complete : function(){
				//上传成功，取消置灰
                $("#"+_fileElementId).parent().find("a").linkbutton({text: '添加'});
                //$("#"+_fileElementId).removeAttr("disabled");
			}
		});
	},
	downloadfile : function(_attachmenId) {
		ajaxUtil.post("basicdata/assembleDownLoadFileUrl.do", {
			attachmenId : _attachmenId
		}, false, function(data) {
			if (data.url != null) {
				window.open(data.url);
			}
		});
	},
	deletefile : function(_attachmenId, callback) {
		ajaxUtil.post("basicdata/deleteFile.do", {
			attachmenId : _attachmenId
		}, false, function(data) {
			if (callback) {
				callback(data.RESULT);
			}
		});
	}
}
