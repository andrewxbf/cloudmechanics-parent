<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>上传服务器配置</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center'" class="_sub_center">
        <div class="easyui-layout" data-options="fit:true">
            <!-- 主表操作菜单栏 -->
            <div id="_gridmenu"
                 class="clearfix tabtop ">
                <h4 class="fleft">上传服务器配置</h4>

                <a class="easyui-linkbutton fright martop3 tabtop-btn" id="111"></a>
                <a href="javascript:void(0)"
                   class="easyui-linkbutton fright martop3 tabtop-btn"
                   data-options="iconCls:'icon-save',text:'保存'"
                   id="_save">保存</a>
                <a href="javascript:void(0)"
                   class="easyui-linkbutton fright martop3 tabtop-btn"
                   data-options="iconCls:'icon-remove',text:'删除'"
                   id="_delete">删除</a>
                <a href="javascript:void(0)"
                   class="easyui-linkbutton fright martop3 tabtop-btn"
                   data-options="iconCls:'icon-edit',text:'修改'"
                   id="_edit">修改</a>
                <a href="javascript:void(0)" class="easyui-linkbutton fright martop3 tabtop-btn"
                   data-options="iconCls:'icon-add',text:'新增'"
                   id="_insert">新增</a>
            </div>
            <!-- 主表查询数据表格区域 -->
            <div data-options="region:'west',noheader:true,border:false" class="_sub_west"
                 style="height: 93%;width: 100%;border-right:#e7e7eb 1px solid;">
                <table id="_grid" data-options="fitColumns:true">
                    <thead data-options="frozen:false">
                    <tr>
                        <%--<th data-options="field:'HOST_CODE',sortable:true,width:'200',halign:'center',align:'center'"
                            editor="{type:'validatebox',options:{required:true,validType:'length[0,16]'}}">编号
                        </th>--%>
                        <th data-options="field:'upHostIp',sortable:true,width:'200',halign:'center',align:'center'"
                            editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">IP地址
                        </th>
                        <th data-options="field:'upPort',sortable:true,width:'200',halign:'center',align:'center'"
                            editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                        </th>
                        <th data-options="field:'upUserName',sortable:true,width:'200',halign:'center',align:'center'"
                            editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                        </th>
                        <th data-options="field:'upPassword',sortable:true,width:'200',halign:'center',align:'center'"
                            editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var init = false;

    $.parser.onComplete = function (context) {
        if (init)
            return;
        init = true;

        common.createDataGrid("_grid", "upload/getUploadServerConfigList.do", "_gridmenu", null, gridParam, false);

        $("#_insert").click(
            function () {
                //1 先取消所有的选中状态
                common.insertRow("_grid", {});
                //4开启编辑状态
                $("#_grid").datagrid("beginEdit", 0);
            });

        $("#_edit").click(
            function () {
                var _rowData = $("#_grid").datagrid("getSelected");
                if (_rowData != null && _rowData != undefined) {
                    var currentIndex = $("#_grid").datagrid("getRowIndex", _rowData);
                    $("#_grid").datagrid("beginEdit", currentIndex);
                } else {
                    messager.msg(3);// 无数据时提示窗
                }

            });

        $("#_delete").click(function () {
                var _rowData = $("#_grid").datagrid("getSelected");
                if (_rowData != null && _rowData != undefined) {
                    $.messager.confirm(
                        "提示信息",
                        "确定要删除吗",
                        function (r) {
                            if (r) {
                                var rowData = $("#_grid").datagrid("getSelected");
                                common.deleteRowData("_grid", rowData);
                                $("#_save").trigger("click");
                            }
                        }
                    )
                } else {
                    messager.msg(3);// 无数据时提示窗
                }
            }
        );

        $("#_save").click(
            function () {
                var rowsData = $("#_grid").datagrid('getRows');

                for (var i = rowsData.length - 1; i >= 0; i--) {
                    $("#_grid").datagrid('endEdit', i);
                }

                ajaxUtil.post("upload/saveUploadServerConfig.do",
                    {
                        uploadVO: JSON.stringify(rowsData)
                    },
                    false,
                    function (data) {
                        if (data.status == 1) {
                            try {
                                common.loadDateGrid("_grid");
                            } catch (e) {
                                messager.msg(3, e);
                                return;
                            } finally {
                                messager.msg(9, "信息操作成功");
                            }
                        }
                    });

            });

    }
    ;

    var gridParam = {
        pagination: true,
        singleSelect: true,
        checkOnSelect: true,
        selectOnCheck: true,
        pageSize: 10,
        pageList: [10, 50, 100],
        method: "POST",
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        }
    };
</script>
</body>
</html>
