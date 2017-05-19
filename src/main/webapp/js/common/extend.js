$.extend($.fn.combobox.methods, { 
    selectedIndex: function (jq, index) { 
        if (!index) { 
            index = 0; 
        } 
        $(jq).combobox({ 
            onLoadSuccess: function () { 
                var opt = $(jq).combobox('options'); 
                var data = $(jq).combobox('getData'); 

                for (var i = 0; i < data.length; i++) { 
                    if (i == index) { 
                        $(jq).combobox('setValue', eval('data[index].' + opt.valueField)); 
                        break; 
                    } 
                } 
            }
        }); 
    } 
});


$.extend($.fn.datagrid.methods, {
    addEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor;
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});