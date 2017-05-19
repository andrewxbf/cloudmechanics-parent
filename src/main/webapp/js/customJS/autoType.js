var autoType = {
		/**
		 * 
		 * @param _brandId
		 * @param _seriesId
		 * @returns {___anonymous675_732}
		 */
		getCodeById : function(_brandId,_seriesId){
			var brandId,seriesId,brandcode,seriesCode;
			if (_brandId !=null)
			{
			var brand = $("#"+_brandId);
			brandId =  brand.combobox("getValue");
			var brandData = brand.combobox("getData");
			$.each(brandData,function(index,value){
				if (brandId == value.COMBO_KEY) {
						brandcode  = value.BRAND_CODE;
				}
					})
			}
			if (_seriesId)
			{
				var series =  $("#"+_seriesId);
				seriesId =  series.combobox("getValue");
				var seriesData =  series.combobox("getData");
				$.each(seriesData,function(index,value){
						if (seriesId == value.COMBO_KEY) {
								seriesCode  = value.SERIES_CODE;
						}
				})
			}
			var param = {
				brandcode:brandcode,
				seriescode:seriesCode
			}
			return param;
		}
}