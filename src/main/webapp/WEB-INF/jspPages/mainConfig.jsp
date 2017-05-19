<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',noheader:true,border:false,disabled:true" class="detailbg" style="height: 600px">
        <div style="display: none">
            <form id="_form" class="_isShow">
                <table class="width_full_sel width_full">
                    <tr class="padtr">
                        <td class="cond_content left_text fright ser-txt">
                            <input class="easyui-textbox" id="_areaCode"
                                   name="areaCode" value="${zdsjbAreaInfo.areaCode}"/>
                        </td>
                        <td class="cond_content left_text fright ser-txt">
                            <input class="easyui-textbox" id="_areaName"
                                   name="areaName" value="${zdsjbAreaInfo.areaName}"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="clearfix boxtit">
            <h4 class="left_text" style="padding-top:8px;padding-bottom:6px;">配置明细信息</h4>
            <%--<div class="right_text boxbtn fright">
                <a href="javascript:void(0)" class="easyui-linkbutton fright martop3"
                   data-options="iconCls:'icon-save',text:'保存'" id="_save">开始执行</a>
            </div>--%>
            <div class="left_text boxbtn fleft" style="color: red" id="_areaInfo">
                <c:if test="${!empty zdsjbAreaInfo}">
                    <b>
                        <span id="111">【${zdsjbAreaInfo.areaCode}】</span>
                        <span id="222">${zdsjbAreaInfo.areaName}</span>
                    </b>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </c:if>
            </div>
        </div>
        <div class="easyui-panel relative" style="height:auto;">

            <div id="add_detail" class="easyui-tabs" style="height:550px;padding: 5px;">
                <div title="Jenkins配置">
                    <div id="jksConfig" style="border-top: 0px"
                         data-options="region:'center',noheader:true,border:false">
                        <div class="tabbtm clearfix"></div>
                        <div id="jks_gridmenu" style="padding:2px 5px;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="jks_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="jks_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="jks_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="jks_save">保存</a>
                        </div>
                        <div style="height:500px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="jks_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'checkbox', checkbox:true"></th>
                                    <th data-options="field:'isSelected',hidden:true">isSelected
                                    </th>
                                    <th data-options="field:'jksHostIp',sortable:true,width:'15%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">IP地址
                                    </th>
                                    <th data-options="field:'jksPort',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                                    </th>
                                    <th data-options="field:'jksUserName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                                    </th>
                                    <th data-options="field:'jksPassword',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                                    </th>
                                    <th data-options="field:'jksPackageUrl',sortable:true,width:'40%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">打包项目路径（URL）
                                    </th>
                                    <th data-options="field:'jksConfigNo',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">排序编号
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="下载服务器配置">
                    <div id="downConfig" style="border-top: 0px"
                         data-options="region:'center',noheader:true,border:false">
                        <div class="tabbtm clearfix"></div>
                        <div id="down_gridmenu" style="padding:2px 5px;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="down_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="down_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="down_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="down_save">保存</a>
                        </div>
                        <div style="height:500px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="down_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'downHostIp',sortable:true,width:'15%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">
                                        IP地址
                                    </th>
                                    <th data-options="field:'downPort',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                                    </th>
                                    <th data-options="field:'downUserName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                                    </th>
                                    <th data-options="field:'downPassword',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                                    </th>
                                    <th data-options="field:'downFileName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">文件名称
                                    </th>
                                    <th data-options="field:'downFilePath',sortable:true,width:'25%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">下载文件路径（URL）
                                    </th>
                                    <th data-options="field:'downFileSavePath',sortable:true,width:'25%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">本地存储路径（URL）
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="上传服务器配置">
                    <div id="upConfig" style="border-top: 0px"
                         data-options="region:'center',noheader:true,border:false">
                        <div class="tabbtm clearfix"></div>
                        <div id="up_gridmenu" style="padding:2px 5px;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="up_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="up_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="up_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="up_save">保存</a>
                        </div>
                        <div style="height:500px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="up_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'upHostIp',sortable:true,width:'15%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">
                                        IP地址
                                    </th>
                                    <th data-options="field:'upPort',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                                    </th>
                                    <th data-options="field:'upUserName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                                    </th>
                                    <th data-options="field:'upPassword',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                                    </th>
                                    <th data-options="field:'upFileName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">文件名称
                                    </th>
                                    <th data-options="field:'upFilePath',sortable:true,width:'25%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">上传服务器路径（URL）
                                    </th>
                                    <th data-options="field:'upSourceFilePath',sortable:true,width:'25%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">上传源文件路径（URL）
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="ZIP服务器配置">
                    <div id="zipConfig" style="border-top: 0px"
                         data-options="region:'center',noheader:true,border:false">
                        <div class="tabbtm clearfix"></div>
                        <div id="zip_gridmenu" style="padding:2px 5px;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="zip_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="zip_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="zip_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="zip_save">保存</a>
                        </div>
                        <div style="height:500px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="zip_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'zipFileFrom',sortable:true,width:'50%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">压缩包源路径（URL）
                                    </th>
                                    <th data-options="field:'zipFileTo',sortable:true,width:'50%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">压缩包存储路径（URL）
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="SQL文件配置">
                    <div id="sqlConfig" style="border-top: 0px"
                         data-options="region:'center',noheader:true,border:false">
                        <div class="tabbtm clearfix"></div>

                        <div id="sql_local_gridmenu" style="padding:2px 5px;">
                            <a class="easyui-linkbutton" style="color:red">【项目组SQL文件配置】</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="sql_local_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="sql_local_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="sql_local_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="sql_local_save">保存</a>
                        </div>
                        <div style="height:200px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="sql_local_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'downHostIp',sortable:true,width:'15%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">
                                        IP地址
                                    </th>
                                    <th data-options="field:'downPort',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                                    </th>
                                    <th data-options="field:'downUserName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                                    </th>
                                    <th data-options="field:'downPassword',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                                    </th>
                                    <th data-options="field:'downFilePath',sortable:true,width:'30%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">SQL文件服务器路径（URL）
                                    </th>
                                    <th data-options="field:'downFileSavePath',sortable:true,width:'30%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">SQL文件本地存储路径（URL）
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <div id="sql_pt_gridmenu" style="padding:2px 5px;">
                            <a class="easyui-linkbutton" style="color:red">【平台SQL文件配置】</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-add',text:'新增'" id="sql_pt_insert">新增</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit',text:'修改'" id="sql_pt_edit">修改</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton"
                               data-options="iconCls:'icon-remove',text:'删除'" id="sql_pt_delete">删除</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton "
                               data-options="iconCls:'icon-save',text:'保存'" id="sql_pt_save">保存</a>
                        </div>
                        <div style="height:200px;border-left: 1px solid #ccc;border-right: 1px solid #ccc;border-bottom: 1px solid #ccc;">
                            <table id="sql_pt_grid" data-options="fitColumns:true">
                                <thead data-options="frozen:false">
                                <tr>
                                    <th data-options="field:'downHostIp',sortable:true,width:'15%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{required:true,validType:'length[0,15]'}}">
                                        IP地址
                                    </th>
                                    <th data-options="field:'downPort',sortable:true,width:'5%',halign:'center',align:'center'"
                                        editor="{type:'numberbox',options:{required:true,min:0,max:65535}}">端口
                                    </th>
                                    <th data-options="field:'downUserName',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">用户名
                                    </th>
                                    <th data-options="field:'downPassword',sortable:true,width:'10%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{validType:'length[0,32]'}}">密码
                                    </th>
                                    <th data-options="field:'downFilePath',sortable:true,width:'30%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">SQL文件服务器路径（URL）
                                    </th>
                                    <th data-options="field:'downFileSavePath',sortable:true,width:'30%',halign:'center',align:'center'"
                                        editor="{type:'validatebox',options:{}}">SQL文件本地存储路径（URL）
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var init = false;

    $.parser.onComplete = function (context) {
        if (init) {
            return;
        }
        init = true;

        common.createDataGrid("jks_grid", "zdsjbJenkinsConfig/getJenkinsServerConfigList.do", "jks_gridmenu", "_form", jksGridParam, false);
        common.createDataGrid("down_grid", "zdsjbDownloadConfig/getDownloadServerConfigList.do", "down_gridmenu", null, downGridParam, false);
        common.createDataGrid("up_grid", "zdsjbUploadConfig/getUploadServerConfigList.do", "up_gridmenu", null, upGridParam, false);
        common.createDataGrid("zip_grid", "zdsjbZipConfig/getZipServerConfigList.do", "zip_gridmenu", null, zipGridParam, false);
        common.createDataGrid("sql_pt_grid", "zdsjbSqlConfig/getPtSqlServerConfigList.do", "sql_pt_gridmenu", null, sqlPtGridParam, false);
        common.createDataGrid("sql_local_grid", "zdsjbSqlConfig/getLocalSqlServerConfigList.do", "sql_local_gridmenu", null, sqlLocalGridParam, false);

        //开始执行
        $("#_save").click(function () {
            $.messager.confirm(
                "提示信息",
                "确定要开始执行吗",
                function (r) {
                    if (r) {
                        $("#jks_save").trigger("click");
                        $("#down_save").trigger("click");
                        $("#up_save").trigger("click");
                        $("#zip_save").trigger("click");
                        $("#sql_Local_save").trigger("click");
                        $("#sql_pt_save").trigger("click");

                        ajaxUtil.post("zdsjbAreaInfo/startInvokeMethod.do",
                            null,
                            false,
                            function (data) {
                                if (data.status == 1) {
                                    messager.msg(9, "执行成功");
                                } else {
                                    messager.msg(3, "执行失败，请查看日志详细信息");
                                }
                            });
                    }
                });
        });


        //Jenkins配置
        $("#jks_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("jks_grid", {});
            //4开启编辑状态
            $("#jks_grid").datagrid("beginEdit", 0);
        });

        $("#jks_edit").click(function () {
            var _rowData = $("#jks_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#jks_grid").datagrid("getRowIndex", _rowData);
                $("#jks_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#jks_delete").click(function () {
            var _rowData = $("#jks_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#jks_grid").datagrid("getChecked");
                            $.each(rowData,function (index, item) {
                                common.deleteRowData("jks_grid", item);
                            });
                            $("#jks_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#jks_save").click(function () {
            var areaCodeVal = $("#_areaCode").textbox('getValue');
            var rowsData = $("#jks_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#jks_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("zdsjbJenkinsConfig/saveJenkinsServerConfig.do",
                {
                    zdsjbJenkinsConfig : JSON.stringify(rowsData),
                    areaCode : areaCodeVal
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("jks_grid");
                        } catch (e) {
                            messager.msg(3, e);
                            return;
                        } finally {
                            messager.msg(9, "信息操作成功");
                        }
                    }
                });

        });


        //Download配置
        $("#down_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("down_grid", {});
            //4开启编辑状态
            $("#down_grid").datagrid("beginEdit", 0);
        });

        $("#down_edit").click(function () {
            var _rowData = $("#down_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#down_grid").datagrid("getRowIndex", _rowData);
                $("#down_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#down_delete").click(function () {
            var _rowData = $("#down_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#down_grid").datagrid("getSelected");
                            common.deleteRowData("down_grid", rowData);
                            $("#down_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#down_save").click(function () {
            var rowsData = $("#down_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#down_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("download/saveDownloadServerConfig.do",
                {
                    downloadVO: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("down_grid");
                        } catch (e) {
                            messager.msg(3, e);
                            return;
                        } finally {
                            messager.msg(9, "信息操作成功");
                        }
                    }
                });

        });


        //Upload配置
        $("#up_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("up_grid", {});
            //4开启编辑状态
            $("#up_grid").datagrid("beginEdit", 0);
        });

        $("#up_edit").click(function () {
            var _rowData = $("#up_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#up_grid").datagrid("getRowIndex", _rowData);
                $("#up_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#up_delete").click(function () {
            var _rowData = $("#up_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#up_grid").datagrid("getSelected");
                            common.deleteRowData("up_grid", rowData);
                            $("#up_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#up_save").click(function () {
            var rowsData = $("#up_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#up_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("upload/saveUploadServerConfig.do",
                {
                    uploadVO: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("up_grid");
                        } catch (e) {
                            messager.msg(3, e);
                            return;
                        } finally {
                            messager.msg(9, "信息操作成功");
                        }
                    }
                });

        });


        //ZIP配置
        $("#zip_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("zip_grid", {});
            //4开启编辑状态
            $("#zip_grid").datagrid("beginEdit", 0);
        });

        $("#zip_edit").click(function () {
            var _rowData = $("#zip_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#zip_grid").datagrid("getRowIndex", _rowData);
                $("#zip_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#zip_delete").click(function () {
            var _rowData = $("#zip_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#zip_grid").datagrid("getSelected");
                            common.deleteRowData("zip_grid", rowData);
                            $("#zip_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#zip_save").click(function () {
            var rowsData = $("#zip_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#zip_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("zip/saveZipServerConfig.do",
                {
                    zipVO: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("zip_grid");
                        } catch (e) {
                            messager.msg(3, e);
                            return;
                        } finally {
                            messager.msg(9, "信息操作成功");
                        }
                    }
                });

        });


        //SQL-pt配置
        $("#sql_pt_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("sql_pt_grid", {});
            //4开启编辑状态
            $("#sql_pt_grid").datagrid("beginEdit", 0);
        });

        $("#sql_pt_edit").click(function () {
            var _rowData = $("#sql_pt_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#sql_pt_grid").datagrid("getRowIndex", _rowData);
                $("#sql_pt_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#sql_pt_delete").click(function () {
            var _rowData = $("#sql_pt_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#sql_pt_grid").datagrid("getSelected");
                            common.deleteRowData("sql_pt_grid", rowData);
                            $("#sql_pt_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#sql_pt_save").click(function () {
            var rowsData = $("#sql_pt_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#sql_pt_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("sql/savePtSqlServerConfig.do",
                {
                    sqlVO: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("sql_pt_grid");
                        } catch (e) {
                            messager.msg(3, e);
                            return;
                        } finally {
                            messager.msg(9, "信息操作成功");
                        }
                    }
                });

        });


        //SQL-local配置
        $("#sql_local_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("sql_local_grid", {});
            //4开启编辑状态
            $("#sql_local_grid").datagrid("beginEdit", 0);
        });

        $("#sql_local_edit").click(function () {
            var _rowData = $("#sql_local_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                var currentIndex = $("#sql_local_grid").datagrid("getRowIndex", _rowData);
                $("#sql_local_grid").datagrid("beginEdit", currentIndex);
            } else {
                messager.msg(3);// 无数据时提示窗
            }

        });

        $("#sql_local_delete").click(function () {
            var _rowData = $("#sql_local_grid").datagrid("getSelected");
            if (_rowData != null && _rowData != undefined) {
                $.messager.confirm(
                    "提示信息",
                    "确定要删除吗",
                    function (r) {
                        if (r) {
                            var rowData = $("#sql_local_grid").datagrid("getSelected");
                            common.deleteRowData("sql_local_grid", rowData);
                            $("#sql_local_save").trigger("click");
                        }
                    }
                )
            } else {
                messager.msg(3);// 无数据时提示窗
            }
        });

        $("#sql_local_save").click(function () {
            var rowsData = $("#sql_local_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#sql_local_grid").datagrid('endEdit', i);
            }

            ajaxUtil.post("sql/saveLocalSqlServerConfig.do",
                {
                    sqlVO: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("sql_local_grid");
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

    /**表格属性**/
    var jksGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: false,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("jks_grid", index, row);
        }
    }

    var downGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: false,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("down_grid", index, row);
        }
    }

    var upGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: true,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("up_grid", index, row);
        }
    }

    var zipGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: true,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("zip_grid", index, row);
        }
    }

    var sqlPtGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: true,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("sql_pt_grid", index, row);
        }
    }

    var sqlLocalGridParam = {
        pagination: false,
        pageSize: 10,
        pageList: [5, 10, 15],
        method: "POST",
        striped: true,
        remoteSort: false,
        singleSelect: true,
        checkOnSelect: true,
        selectOnCheck: true,
        autoSave: true,
        delUnRefresh: true,
        destroyMsg: {
            norecord: {
                msg: "请选择一条记录信息"
            },
            confirm: {
                msg: "是否确认删除选中的信息"
            }
        },
        onDblClickRow: function (rowIndex) {
            //开启编辑状态
            $(this).datagrid('beginEdit', rowIndex);
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("sql_local_grid", index, row);
        }
    }
</script>
</body>
</html>
