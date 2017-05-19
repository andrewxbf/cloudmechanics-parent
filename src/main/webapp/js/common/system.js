var sysdata={
				/**
				 * 字典key-value
				 */
				dictionary : {},
				/**
				 * 字典数据集{dict_no:[{key-value}]}
				 */
				dictionaryArray : {},
				/**
				 * 省份id-name
				 */
				province : {},
				/**
				 * 字典数据集{0:[{key-value}]}
				 */
				provinceArray : {},
				/**
				 * 城市id-name
				 */
				city : {},
				/**
				 * 字典数据集{provinceid:[{key-value}]}
				 */
				cityArray : {},
				/**
				 * 区县id-name
				 */
				county : {},
				/**
				 * 字典数据集{cityid:[{key-value}]}
				 */
				countyArray : {},
				/**
				 * boolean值
				 */
				booleanData : [{COMBO_KEY:1,COMBO_VALUE:'是'},{COMBO_KEY:0,COMBO_VALUE:'否'}]
			};

var system ={

		/**
		 * 设置主页面最外层div宽高
		 */
		/*setOuterMostSize : function(divId){
			var _outerMostDiv=$("#"+divId);
			var winWidth=system.getWindowWidth();
			var _screenWidth = winWidth-17 < 1024 ? 1024 : winWidth-17;
			_outerMostDiv.css("width", _screenWidth);
			_outerMostDiv.css("height", system.getWindowHeight()-17);
		},*/
		/**
		 * 设置子页面最外层div宽高
		 */
		/*setSubOuterMostSize : function(divId){
			var _subOuterMostDiv=$("#"+divId);
			var winWidth=system.getWindowWidth();
			var _screenWidth = winWidth-17 < 850 ? 850 : winWidth-17;
			_subOuterMostDiv.css("width", _screenWidth);
			_subOuterMostDiv.css("height", system.getWindowHeight()-17);
		},*/
		/**
		 * 查询条件自适应
		 */
		/*resetFormLayout : function(divId,formId){
			var _divWidth=$("#"+divId).width();
			var _search=$("#"+formId);
			_search.find(".width_full").css("width",_divWidth);
			_search.find(".cond_label").children().css("width",_divWidth*0.09);
			_search.find(".cond_content").children().css("width",_divWidth*0.19);
			_search.find(".width_10").css("width",_divWidth*0.16);
		},*/
		
		/*getWindowWidth : function(){
			var winWidth;
			 //获取窗口宽度   
			 if (window.innerWidth){
			  winWidth = window.innerWidth;  
			 }else if((document.body) && (document.body.clientWidth)){
			  winWidth = document.body.clientWidth;
			 }
			 return winWidth;
		},
		getWindowHeight : function(){
			var winHeight;
			 //获取窗口高度   
			 if (window.innerHeight){
			  winHeight = window.innerHeight;  
			 }else if((document.body) && (document.body.clientHeight)){
			  winHeight = document.body.clientHeight;
			 }
			 return winHeight;
		},*/
		  
		/**
		 * 创建字典类下拉框
		 * @param _domId
		 * @param _dictNo
		 * @param oopts
         * @param _force 强制更新
		 * @returns {String}
		 */
		createDictCombo: function(_domId, _dictNo, oopts, _isCombo, _selIndex,_force) {
			var _combo, data;
			if (_dictNo == undefined || _dictNo == null || _dictNo == '') {
				return '';
			}
			if (sysdata.dictionaryArray[_dictNo] == undefined || _force) {
				var url = "basicdata/dicts.do";
				param = {
					dictNo : _dictNo
				};
				ajaxUtil.post(url, param, false, function(_data){
					data = _data.rows;
					for ( var i = 0; i < data.length; i++) {
						sysdata.dictionary[data[i]['COMBO_KEY']] = data[i]['COMBO_VALUE'];
					}
					sysdata.dictionaryArray[_dictNo] = data;
					if(_isCombo){
						jsondata = [{"COMBO_VALUE":"全部","COMBO_KEY":""}];
						data = jsondata.concat(data);
					}
					_combo = system.createComboItem(_domId, data, oopts);
					if(_selIndex != null && _selIndex != undefined){
						_combo.combobox('selectedIndex', _selIndex);
					}
				});
			}else{
				data = sysdata.dictionaryArray[_dictNo];
				if(_isCombo){
					jsondata = [{"COMBO_VALUE":"全部","COMBO_KEY":""}];
					data = jsondata.concat(data);
				}
				_combo = system.createComboItem(_domId, data, oopts);
				if(_selIndex != null && _selIndex != undefined){
					_combo.combobox('selectedIndex', _selIndex);
				}
			}
		},
		
		getDictValue: function(_key){
			var _value;
			if (_key == undefined || _key == null || _key == '') {
				return '';
			}
			_key = _key + '';
			if (sysdata.dictionary[_key] == undefined) {
				if(_key.length>4){
					var _dictNo = _key.substr(0, 4);
					system.getDictionary(_dictNo);
				}
			}
			return sysdata.dictionary[_key];
			
			/*var url = "basicdata/dictvalue.do";
			param = {
					key : _key
			};
			ajaxUtil.post(url, param, false, function(_data){
				_value = _data.dictValue;

			});
			return _value;*/
		},
		
		/**
		 * 根据字典号加载字典
		 * 
		 * @param dictNo
		 * @returns
		 */
		getDictionary : function(_dictNo) {
			if (_dictNo == undefined || _dictNo == null || _dictNo == '') {
				return '';
			}
			if (sysdata.dictionaryArray[_dictNo] == undefined) {
				var url = "basicdata/dicts.do";
				param = {
					dictNo : _dictNo
				};
				ajaxUtil.post(url, param, false, function(_data){
					data = _data.rows;
					for ( var i = 0; i < data.length; i++) {
						sysdata.dictionary[data[i]['COMBO_KEY']] = data[i]['COMBO_VALUE'];
					}
					sysdata.dictionaryArray[_dictNo] = data;
				});
			}
			return sysdata.dictionaryArray[_dictNo];
		},
		
		/**
		 * 创建下拉框
		 * @param _domId
		 * @param _data
		 * @param oopts
		 */
		createComboItem: function(_domId, _data, oopts){
			var _combo = $("#"+_domId);
			var opts={
					valueField: 'COMBO_KEY',
					textField: 'COMBO_VALUE',
					data : _data
			}
			if(oopts){
				for(var oo in oopts){
					opts[oo] = oopts[oo];
				}
			}
			_combo.combobox(opts);
			return _combo;
		},
		
		resetComboItems : function(_domId, _isCombo) {
			var combo = $("#" + _domId);
			var data = [];
			if(_isCombo){
				jsondata = [{"COMBO_VALUE":"全部","COMBO_KEY":""}];
				data = jsondata.concat(data);
			}
			if (combo) {
				var options = {
					value: "",
					valueField : 'COMBO_KEY',
					textField : 'COMBO_VALUE',
					data : data
				};
				combo.combobox(options);
			}
		},
		
		
		loadComboItems : function(_domId, _url, _param, oopts, _isComb, target, value, offset) {
			var data;
			if(target && value && target[value] != null && target[value] != undefined && target[value].length >0){
				data = target[value];
			}else{
				ajaxUtil.post(_url, _param, false, function(_data){
					if (_data.status != 0) {
						data = _data.rows;
						if(target && value){
							target[value]=data;
						}
					}else{
						return;
					}
				});
			}
			if(_isComb){
				jsondata = [{"COMBO_VALUE":"全部","COMBO_KEY":"","selected":true}];
				data = jsondata.concat(data);
			}
			var combo = $("#" + _domId);
			if (combo && data) {
				var options = {
					valueField : 'COMBO_KEY',
					textField : 'COMBO_VALUE',
					data : data
				};
                if(offset){
                    options['offset'] = offset;
                }
				if (oopts) {
					for ( var o in oopts) {
						options[o] = oopts[o];
					}
				}
				combo.combobox(options);
			}
		},
		
		loadComboDefItems : function(_domId, _url, _param, oopts, _isComb, target, value, offset) {
			var data;
			if(target && value && target[value] != null && target[value] != undefined){
				data = target[value];
			}else{
				ajaxUtil.post(_url, _param, false, function(_data){
					if (_data.status != 0) {
						data = _data.rows;
						if(target && value){
							target[value]=data;
						}
					}else{
						return;
					}
				});
			}
			if(_isComb){
				jsondata = {'COMBO_VALUE':' - ','COMBO_KEY':''};
				data.unshift(jsondata);
			}
			var combo = $("#" + _domId);
			if (combo && data) {
				var options = {
					valueField : 'COMBO_KEY',
					textField : 'COMBO_VALUE',
					data : data
				};
                if(offset){
                    options['offset'] = offset;
                }
				if (oopts) {
					for ( var o in oopts) {
						options[o] = oopts[o];
					}
				}
				combo.combobox(options);
			}
		},
		
		/**
		 * 获取省份数据
		 */
		getProvinceVal : function(_key){
			return sysdata.province[_key];
		},
		/**
		 * 获取城市
		 */
		getCityVal : function(_key){
			return sysdata.city[_key];
		},
		/**
		 * 获取区县
		 */
		getCountyVal : function(_key){
			return sysdata.county[_key];
		},
		
		/**
		 * 初始化省份城市区县下拉框
		 */
		loadComboArea : function(_province, _city, _county, _isComb){
			var _timestamp;
			var url = "basicdata/regionsInfo.do";
			var param = {
				regionId : -1,
				regionType : 16000010
			};
			var loadItems = system.loadComboItems;
			var resetItems = system.resetComboItems;
			var opts = null;
			if(_city){
				opts = {
						onSelect : function(e) {
							if (e && e != undefined && e != null && e.COMBO_KEY != "") {
								var provinceid = e.COMBO_KEY;
								param = {
										regionId : e.COMBO_KEY,
										regionType : 16000015
									};
								var copts = null;
								if(_county){
									copts = {
											onSelect : function(e) {
												if (e && e != undefined && e != null && e.COMBO_KEY != "") {
													var cityid = e.COMBO_KEY;
													param = {
															regionId : e.COMBO_KEY,
															regionType : 16000020
														};
													 _timestamp = new Date().getTime();
													loadItems(_county, url+"?timestamp="+_timestamp, param, null, _isComb, sysdata.countyArray, cityid);
												}
											},onChange : function(){
												resetItems(_county, _isComb);
											}
										}
								}
								 _timestamp = new Date().getTime();
								loadItems(_city, url+"?timestamp="+_timestamp, param, copts, _isComb, sysdata.cityArray, provinceid);
							}else{
								resetItems(_city, _isComb);
								resetItems(_county, _isComb);
							}
						},onChange : function(){
								resetItems(_city, _isComb);
								resetItems(_county, _isComb);
						}
					};
				
			}
			 _timestamp = new Date().getTime();
			loadItems(_province, url+"?timestamp="+_timestamp, param, opts, _isComb, sysdata.provinceArray, 'p0');
			if(_city && _isComb) resetItems(_city, _isComb);
			if(_county && _isComb) resetItems(_county, _isComb);
		},
		/**
		 * 初始化适用车型下拉框
		 */
		loadSuitedModels : function(_brand, _series, _type, _isComb,_suitedModel,_suitedId){
			// 加载车型车系下拉框
			var url = "repair/getClSysAutoBrandCombo.do";
			var param = {};
			var loadItems = system.loadComboItems;
			var resetItems = system.resetComboItems;
			var opts = null;
			var brandId = null;
			var	seriesId = null;
			if(_series){
				opts = {
						onSelect : function(e) {
							if(brandId != null && e != undefined && e != null && brandId == e.COMBO_KEY){
								return;
							}
							if (e && e != undefined && e != null && e.COMBO_KEY != "") {
								url = "repair/getClSysAutoSeriesCombo.do";
								param = {
									BRAND_ID : e.COMBO_KEY
								};
								brandId = e.COMBO_KEY;
								var copts = null;
								if(_type){
									copts = {
											onSelect : function(e) {
												if(seriesId != null && e != undefined && e != null && seriesId == e.COMBO_KEY){
													return;
												}
												if (e && e != undefined && e != null && e.COMBO_KEY != "") {
													url = "repair/getClSysAutoModelCombo.do";
													param = {
														SERIES_ID : e.COMBO_KEY
													};
													seriesId = e.COMBO_KEY;
													dopts = {
														onSelect : function(e) {
															if (e && e != undefined && e != null && e.COMBO_KEY != ""){
																if (_suitedModel) {
																	var brand = $("#"+_brand).combobox("getText");
																	var series = $("#"+_series).combobox("getText");
																	var text = brand + "-" + series +"-" +e.COMBO_VALUE+";";
																	var modelArray = _suitedModel.split(',');
																	$.each(modelArray,function(index,value){
																		$("#"+value).textbox("setValue",text);
																	})
																}
																if (_suitedId) {
																	var brandKey = $("#"+_brand).combobox("getValue");
																	var seriesKey = $("#"+_series).combobox("getValue");
																	var keyText = brandKey + "," + seriesKey +"," +e.COMBO_KEY+";";
																	var modelIdArray = _suitedId.split(',');
																	$.each(modelIdArray,function(index,value){
																		$("#"+value).textbox("setValue",keyText);
																	})
																}
																
															}
														}
													}
													loadItems(_type, url, param, dopts, _isComb);
												}
											},onChange : function(newValue, oldValue){
												resetItems(_type, _isComb);
											}
										}
								}
								loadItems(_series, url, param, copts, _isComb);
							}else{
								resetItems(_series, _isComb);
								resetItems(_type, _isComb);
							}
						},onChange : function(newValue, oldValue){
								resetItems(_series, _isComb);
								resetItems(_type, _isComb);
						}
					};
			}
			loadItems(_brand, url, param, opts, _isComb);
			if(_series && _isComb) resetItems(_series, _isComb);
			if(_type && _isComb) resetItems(_type, _isComb);
		},
		/**
		 * 初始化适用车型下拉框
		 */
		loadSuitedModelsAndAutoQuery : function(_brand, _series, _type, _isComb,_suitedModel,_suitedId,_loadDateGridDomId){
			// 加载车型车系下拉框
			var url = "repair/getClSysAutoBrandCombo.do";
			var param = {};
			var loadItems = system.loadComboItems;
			var resetItems = system.resetComboItems;
			var opts = null;
			if(_series){
				opts = {
						onSelect : function(e) {
							if (e && e != undefined && e != null && e.COMBO_KEY != "") {
								url = "repair/getClSysAutoSeriesCombo.do";
								param = {
									BRAND_ID : e.COMBO_KEY
								};
								var copts = null;

								if(_type){
									copts = {
											onSelect : function(e) {
												if (e && e != undefined && e != null && e.COMBO_KEY != "") {
													url = "repair/getClSysAutoModelCombo.do";
													param = {
														SERIES_ID : e.COMBO_KEY
													};
													dopts = {
														onSelect : function(e) {
															if (e && e != undefined && e != null && e.COMBO_KEY != ""){
																if (_suitedModel) {
																	var brand = $("#"+_brand).combobox("getText");
																	var series = $("#"+_series).combobox("getText");
																	var text = brand + "-" + series +"-" +e.COMBO_VALUE+";";
																	var modelArray = _suitedModel.split(',');
																	$.each(modelArray,function(index,value){
																		$("#"+value).textbox("setValue",text);
																	})
																}
																if (_suitedId) {
																	var brandKey = $("#"+_brand).combobox("getValue");
																	var seriesKey = $("#"+_series).combobox("getValue");
																	var keyText = brandKey + "," + seriesKey +"," +e.COMBO_KEY+";";
																	var modelIdArray = _suitedId.split(',');
																	$.each(modelIdArray,function(index,value){
																		$("#"+value).textbox("setValue",keyText);
																	})
																}
															}
														},onChange : function(){
															common.loadDateGrid(_loadDateGridDomId);//自动查询
														}
													}
													loadItems(_type, url, param, dopts, _isComb);
												}
											},onChange : function(){
												resetItems(_type, _isComb);
												common.loadDateGrid(_loadDateGridDomId);//自动查询
											}
										}
								}
								loadItems(_series, url, param, copts, _isComb);
							}else{
								resetItems(_series, _isComb);
								resetItems(_type, _isComb);
							}
						},onChange : function(){
								resetItems(_series, _isComb);
								resetItems(_type, _isComb);
								common.loadDateGrid(_loadDateGridDomId);//自动查询
						}
					};
			}
			loadItems(_brand, url, param, opts, _isComb);
			if(_series && _isComb) resetItems(_series, _isComb);
			if(_type && _isComb) resetItems(_type, _isComb);
		},
		//反选适用车型
		selectSuitedModels : function(_brand, _series, _type, _suitedId){
			var suitedIdArray = _suitedId.split(";");
			var modelArray = suitedIdArray[0].split(",");
			$("#"+_brand).combobox("select",modelArray[0]);
			$("#"+_series).combobox("select",modelArray[1]);
			$("#"+_type).combobox("select",modelArray[2]);
		},
		
		/**
		 * 初始化省份城市区县基础数据
		 */
		loadBasisArea : function() {
			if(sysdata.province['initial']==undefined ||sysdata.province['initial']==null ||sysdata.province['initial']==false){
				sysdata.province['initial']=true;
				var _timestamp;
				var url = "basicdata/regionsInfo.do";
				var param = {
					regionId : -1,
					regionType : 16000010
				};
				 _timestamp = new Date().getTime();
				ajaxUtil.post(url+"?timestamp="+_timestamp, param, true, function(_data){
					if (_data.status != 0) {
						var jsondata = _data.rows;
						for ( var i = 0; i < jsondata.length; i++) {
							sysdata.province[jsondata[i]['COMBO_KEY']] = jsondata[i]['COMBO_VALUE'];
						}
					}
				});
					
				var param = {
						regionId : -2,
						regionType : 16000015
					};
				 _timestamp = new Date().getTime();
				ajaxUtil.post(url+"?timestamp="+_timestamp, param, true, function(_data){
					if (_data.status != 0) {
						var jsondata = _data.rows;
						for ( var i = 0; i < jsondata.length; i++) {
							sysdata.city[jsondata[i]['COMBO_KEY']] = jsondata[i]['COMBO_VALUE'];
						}
					}
				});

				var param = {
						regionId : -3,
						regionType : 16000020
					};
				 _timestamp = new Date().getTime();
				ajaxUtil.post(url+"?timestamp="+_timestamp, param, true, function(_data){
					if (_data.status != 0) {
						var jsondata = _data.rows;
						for ( var i = 0; i < jsondata.length; i++) {
							sysdata.county[jsondata[i]['COMBO_KEY']] = jsondata[i]['COMBO_VALUE'];
						}
					}
				});
			}
		}
}