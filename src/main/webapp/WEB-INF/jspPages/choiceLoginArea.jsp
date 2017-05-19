<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="easyui-layout" data-options="fit:true">
    <div id="_north" class="_sub_north" data-options="region:'north',height:'auto'">
        <form id="_search" class="_isShow">
            <table class="width_full_sel width_full">
                <tr class="padtr">
                    <td class="cond_label left_text snavwid fleft">
                        <span class="snav"><i class="snav-icon1"></i>升级地区选择</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',noheader:true,border:false,disabled:true" class="detailbg" style="height: 600px">
        <div id="_gridmenu" class="clearfix tabtop">
            <%--<h4 class="left_text" style="padding-top:8px;padding-bottom:6px;">升级地区选择</h4>--%>
            <a class="easyui-linkbutton fright martop3 tabtop-btn" id="111"></a>
            <a href="javascript:void(0)"
               class="easyui-linkbutton fright martop3 tabtop-btn"
               data-options="iconCls:'icon-save',text:'保存'"
               id="_save">保存</a>
            <i class="fright  tabtop-line">|</i>
            <a href="javascript:void(0)"
               class="easyui-linkbutton fright martop3 tabtop-btn"
               data-options="iconCls:'icon-remove',text:'删除'"
               id="_delete">删除</a>
            <i class="fright  tabtop-line">|</i>
            <a href="javascript:void(0)"
               class="easyui-linkbutton fright martop3 tabtop-btn"
               data-options="iconCls:'icon-edit',text:'修改'"
               id="_edit">修改</a>
            <i class="fright  tabtop-line">|</i>
            <a href="javascript:void(0)" class="easyui-linkbutton fright martop3 tabtop-btn"
               data-options="iconCls:'icon-add',text:'新增'"
               id="_insert">新增</a>
            <i class="fright  tabtop-line">|</i>
            <a href="javascript:void(0)" class="easyui-linkbutton fright martop3" style="color:blue"
               data-options="iconCls:'icon-edit',text:'详细'" id="_detail">详细配置</a>
            <i class="fright  tabtop-line">|</i>
            <div class="right_text boxbtn fright">
                <a href="javascript:void(0)" class="easyui-linkbutton fright martop3" style="color:red"
                   data-options="iconCls:'icon-ok',text:'执行'" id="_invokeResult">开始产出成果</a>
            </div>
        </div>
        <div data-options="region:'west',noheader:true,border:false" class="_sub_west"
             style="height: 93%;width: 100%;border-right:#e7e7eb 1px solid;">
            <table id="_grid" data-options="fitColumns:true">
                <thead data-options="frozen:false">
                <th data-options="field:'areaCode',sortable:true,width:'8%',halign:'center',align:'center'"
                    editor="{type:'validatebox',options:{required:true,validType:'length[0,16]'}}">地区代码
                </th>
                <th data-options="field:'areaName',sortable:true,width:'10%',halign:'center',align:'center'"
                    editor="{type:'validatebox',options:{required:true,validType:'length[0,32]'}}">地区名称
                </th>
                <th data-options="field:'areaToken',sortable:true,width:'10%',halign:'center',align:'center'"
                    editor="{type:'text',options:{readonly:true,required:true}}">地区令牌
                </th>
                <th data-options="field:'invokeStatus',sortable:true,width:'8%',halign:'center',align:'center',
                        formatter:method.invokeStatus" editor="{type:'text',options:{readonly:true}}">执行状态
                </th>
                <th data-options="field:'invokeResult',sortable:true,width:'8%',halign:'center',align:'center',
                        formatter:method.invokeResult,readonly:true" editor="{type:'text',options:{readonly:true}}">执行结果
                </th>
                <th data-options="field:'invokeStartTime',sortable:true,width:'15%',halign:'center',align:'center'"
                    editor="{type:'datetimebox',options:{readonly:true}}">执行开始时间
                </th>
                <th data-options="field:'invokeEndTime',sortable:true,width:'15%',halign:'center',align:'center'"
                    editor="{type:'datetimebox',options:{readonly:true}}">执行结束时间
                </th>
                <th data-options="field:'areaNo',sortable:true,width:'5%',halign:'center',align:'center'"
                    editor="{type:'numberbox',options:{min:1,max:99,validType:'length[0,2]'}}">排序编号
                </th>
                <th data-options="field:'areaRemark',sortable:true,width:'20%',halign:'center',align:'center'"
                    editor="{type:'validatebox',options:{validType:'length[0,256]'}}">备注
                </th>

                <%--<c:if test="${zdsjbAreaInfo != null}">
                <c:forEach var="areaInfo" items="${zdsjbAreaInfo}">
                <tr style="line-height: 50px;">
                    <a class="_td_a" href="javaScript:void(0);">
                        <td style="background-color: #1094fa">
                            【${areaInfo.areaCode}】${areaInfo.areaName}
                        </td>
                    </a>
                </tr>
                </c:forEach>
                </c:if>--%>
            </table>
            <%--<ul class="fleft clearfix" style="margin-left: 100px;">
                <li style="height: 50px"></li>
                <c:if test="${zdsjbAreaInfo != null}">
                    <c:forEach var="areaInfo" items="${zdsjbAreaInfo}">
                        <li id="menu${areaInfo.areaNo}" style="height: 50px">
                            <a href="javascript:void(0)"
                               class="showCenter">【${areaInfo.areaCode}】${areaInfo.areaName}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>--%>
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

        common.createDataGrid("_grid", "zdsjbAreaInfo/getZdsjbAreaInfoList.do", "_gridmenu", null, gridParam, false);

        var endEditRows = function () {
            var rowsData = $("#_grid").datagrid('getRows');

            for (var i = rowsData.length - 1; i >= 0; i--) {
                $("#_grid").datagrid('endEdit', i);
            }
        }

        $("#_insert").click(function () {
            //1 先取消所有的选中状态
            common.insertRow("_grid", {});
            //4开启编辑状态
            $("#_grid").datagrid("beginEdit", 0);
        });

        $("#_edit").click(function () {
            endEditRows();

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
                                ajaxUtil.post("zdsjbAreaInfo/deleteZdsjbAreaInfo.do", {zdsjbAreaInfo: JSON.stringify(rowData)}, false, function (data) {
                                    if (data.status == 1) {
                                        messager.msg(9, "删除成功！");
                                    } else {
                                        messager(3, "删除失败！");
                                    }
                                });
                            }
                        }
                    )
                } else {
                    messager.msg(3);// 无数据时提示窗
                }
            }
        );

//        var $grid = $("#_grid"); //所有的form元素
//        $grid.keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
//            var key = e.which; //e.which是按键的值
//            if (key == 13) {
//                $("#_save").trigger("click");
//            }
//        });

        $("#_save").click(function () {
            endEditRows();

            var rowsData = $("#_grid").datagrid('getRows');
            if (rowsData.length <= 0 || rowsData == undefined) {
                messager.msg(3, "没有要保存的数据");
                return;
            }

            ajaxUtil.post("zdsjbAreaInfo/saveZdsjbAreaInfo.do",
                {
                    zdsjbAreaInfo: JSON.stringify(rowsData)
                },
                false,
                function (data) {
                    if (data.status == 1) {
                        try {
                            common.loadDateGrid("_grid");
                        } catch (e) {
                            messager.msg(3);
                            return;
                        } finally {
                            messager.msg(9, "保存成功");
                        }
                    }
                });

        });

        $("#_detail").click(function () {
            var _rowData = $("#_grid").datagrid("getSelected");

            if (_rowData != null && _rowData != undefined) {
                $("#_layout_center").panel({
                    href: "zdsjbAreaInfo/showDetailConfig.do",
                    method: "post",
                    queryParams: {zdsjbAreaInfo: JSON.stringify(_rowData)},
                    onLoad: function () {
                        layout.showMenu($("._load"));
                    }
                });
            } else {
                messager.msg(3, "未选中任何数据");// 无数据时提示窗
            }
        });

    }

    /**表格属性**/
    var gridParam = {
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
            /*var rowsData = $("#_grid").datagrid('getRows');

             for (var i = rowsData.length - 1; i >= 0; i--) {
             $("#_grid").datagrid('endEdit', i);
             }

             //开启编辑状态
             $(this).datagrid('beginEdit', rowIndex);*/
            var _rowData = $("#_grid").datagrid("getSelected");

            if (_rowData != null && _rowData != undefined) {
                $("#_layout_center").panel({
                    href: "zdsjbAreaInfo/showDetailConfig.do",
                    method: "post",
                    queryParams: {zdsjbAreaInfo: JSON.stringify(_rowData)},
                    onLoad: function () {
                        layout.showMenu($("._load"));
                    }
                });
            } else {
                messager.msg(3, "未选中任何数据");// 无数据时提示窗
            }
        },
        onEndEdit: function (index, row) {
            common.updateRowIndex("_grid", index, row);
        }
    }

    var method = {
        invokeStatus: function (value) {
            if (value == 1) {
                return "进行中";
            } else if (value == 0) {
                return "已结束";
            }
        },
        invokeResult: function (value) {
            if (value == 1) {
                return "成功";
            } else if (value == 0) {
                return "失败";
            }
        }
    }

</script>
</body>
</html>
