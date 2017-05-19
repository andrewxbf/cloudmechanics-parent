<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<!-- <link rel="stylesheet" type="text/css" href="themes/black/easyui.css">-->
   		<link rel="stylesheet" type="text/css" href="../themes/bootstrap/easyui.css">
   		<link rel="stylesheet" type="text/css" href="../css/common/common.css">
   		<link rel="stylesheet" type="text/css" href="../themes/demo.css">
		<!-- <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro-blue/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro-gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro-green/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro-orange/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/metro-red/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/ui-cupertino/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/ui-dark-hive/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/ui-pepper-grinder/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/ui-sunny/easyui.css"> -->

		<link rel="stylesheet" type="text/css" href="../themes/icon.css">
		
		<script type="text/javascript" src="../easyUI/jquery.min.js"></script>
		<script type="text/javascript" src="../easyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../easyUI/window.util.js"></script>
		<script type="text/javascript" src="../locale/easyui-lang-zh_CN.js"></script>

		<!-- 富文本ueditor  -->
		<script type="text/javascript" src="../js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="../js/ueditor/ueditor.all.min.js"> </script>
		<script type="text/javascript" src="../js/ueditor/lang/zh-cn/zh-cn.js"></script>

		<script type="text/javascript" src="../extension/datagrid/jquery.edatagrid.js"></script>
		<script type="text/javascript" src="../extension/datagrid/datagrid-detailview.js"></script>
		<script type="text/javascript" src="../extension/datagrid/datagrid-bufferview.js"></script>
		
		<script type="text/javascript" src="../js/common/jQuery.md5.js"></script>
		<script type="text/javascript" src="../js/common/common.js"></script>
		<script type="text/javascript" src="../js/customJS/formatter.js"></script>
		<script type="text/javascript" src="../js/customJS/FormatMoney.js"></script>
		<script type="text/javascript" src="../js/common/messager.js"></script>
		<script type="text/javascript" src="../js/common/system.js"></script>
		<script type="text/javascript" src="../js/common/layout.js"></script>
		<script type="text/javascript" src="../js/common/ajaxUtil.js"></script>
		<script type="text/javascript" src="../js/common/validation.js"></script>
		<script type='text/javascript' src='../js/common/jquery-ui.min.js'></script>

		<script type="text/javascript" src="../js/common/extend.js"></script>
		<script type="text/javascript" src="../js/customJS/PinYin.js"></script>
		<script type="text/javascript" src="../js/customJS/DateUtil.js"></script>
		<script type="text/javascript" src="../js/customJS/CustomUtil.js"></script>
		<script type="text/javascript" src="../js/customJS/autoType.js"></script>
		<script type="text/javascript" src="../js/customJS/ajaxfileupload.js"></script>
        <!-- FAVICONS -->
        <link rel="shortcut icon" type="image/ico" href="../images/favicon.ico">
        <link rel="icon" type="image/ico" href="../images/favicon.ico">
        <script type="text/javascript">
        
        window.onload = function() {  
            if (navigator.userAgent.indexOf('Firefox') >= 0) {
                //alert("检测到您正使用火狐浏览器，请将 about:config 中 browser.backspace_action 设置为 2");
                return;
            }
            document.getElementsByTagName("body")[0].onkeydown = function() {
                if(event.keyCode==8) {
                    var elem = event.srcElement || event.currentTarget;
                    var name = elem.nodeName;
                    if(name!='INPUT' && name!='TEXTAREA') {
                        return _stopIt(event);  
                    }  
                    var type_e = elem.type.toUpperCase();  
                    if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')) {
                        return _stopIt(event);  
                    }  
                    if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){  
                        return _stopIt(event);  
                    }  
                }  
            };
        };
        function _stopIt(e) {  
            if(e.returnValue) {  
                e.returnValue = false ;  
            }  
            if(e.preventDefault){  
                e.preventDefault();  
            }
            return false;  
        }  
        </script>
	</head>
</html>
