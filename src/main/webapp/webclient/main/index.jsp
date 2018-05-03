<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path+"/" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page contentType="text/html;charset=UTF-8" buffer="128kb" autoFlush="true" import="java.util.*" %><%
	//response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");

	java.util.Calendar cal=java.util.Calendar.getInstance();
	Date date=new Date();
	cal.setTime(date);
	cal.add(java.util.Calendar.DAY_OF_YEAR, -1);
	String startDate=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	String endDate=new SimpleDateFormat("yyyy-MM-dd").format(date);

	String sql=null;
	String SelectTemplateSession=new Date().getTime()+"";
	response.setHeader("Access-Control-Allow-Origin", "*");
	%>

  <head>
    <base href="<%=basePath%>">
    
    <title>json转换为table查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="webclient/javascript/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="webclient/javascript/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="webclient/javascript/easyui/themes/snow_icon.css">
	<script type="text/javascript" src="webclient/javascript/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="webclient/javascript/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="webclient/javascript/shoukaiseki/logger-1.0.js"></script>
	<!--
	<script type="text/javascript" src="webclient/javascript/shoukaiseki/jquery-plugin/multilinetable-1.0.js"></script>
	-->
	<style type="text/css">
	/**外边框线**/
		.table_outside_border {
							border:1px solid #A9A9A9;
							width:99%;margin:0 auto;
		} 
		
		/**div 内table不撑出来**/
		.table_outside_border_div {
				overflow:auto;
		} 
	
		.table_outside_border_div table
			{
			   width:100%;
			   overflow-x:auto;
			   table-layout:fixed; WORD-BREAK: break-all; WORD-WRAP: break-word
		}
	
	
		.mainTablecss table {
			border-top-width: 0px;
			border-right-width: 0px;
			border-bottom-width: 0px;
			border-left-width: 0px;
		}
        .maintable {  min-height: 25px; line-height: 25px; text-align: center; border-collapse: collapse;}

		
	</style>
	
	<script type="text/javascript">



        function setTopicError() {
            Logger.log("setTopicError");
//            var from =  $("#addtopicForm");
            var from =  document.getElementById("addtopicForm");
            from.action="<%=basePath%>main/submit";
            from.submit();

        }


	</script>
	
</head>
<body  class="easyui-layout" style="overflow:auto;">
	<div  align="left" style="width:70%;margin:0 auto;">
		json:
        <br>
		<textarea id="topictextarea" rows="10" cols="134" name="jsonfrom" form="addtopicForm" >${jsonfrom}</textarea>
		<form id="addtopicForm" align="right"   method="post">
			<br>

			<button type="button" name="button" onclick="setTopicError()">json转换为table查看</button>
		</form>
	</div>
	<br>
	
	<c:if test="${not empty errorstr}">错误提示:${errorstr}</c:if>
	<div id="answerRecorder"  style="width:99%;margin:0 auto;">
		${htmlstr}
	</div>

	<br>
	<br>
	<br>
	<br>
</body>

<script type="text/javascript">

	$(function() {
        Logger.log("asas啊啊");

        $('#topictextarea').change(function(){
            Logger.log("topictextarea.change");
            Logger.log($(this).val());
            var oldvalue=$(this).val();
            var newvalue=oldvalue.replace(/ /g,' ');
            $(this).val(newvalue);
        });
    });



</script>


<script type="text/javascript">
    //重载页面前记录滚动条位置
    window.onbeforeunload = function () {
        scrollPos = document.body.scrollTop;
        document.cookie = "scrollTop=" + scrollPos; //存储滚动条位置到cookies中
    }

     //重载页面后滚动条回到当前位置
    window.onload = function () {
        Logger.log("onload");
        Logger.log(document.cookie);
        if (document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
            var arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
            document.documentElement.scrollTop = parseInt(arr[1]);
            document.body.scrollTop = parseInt(arr[1]);

            Logger.log("set scrollTop");
            Logger.log(document.body.scrollTop);
        }
		
		$('#copytimudaan').click(function() {
		 var src=$('#timudaansrc').text();
		 //Logger.log(src);
		 //openwindow('springanswerrecorder/copyanswerrecorder','src');
	 });
    }
	 // url 转向网页的地址  
 // name 网页名称，可为空  
 function openwindow(url,name)  
 {  
	 iWidth="750px";
	 iHeight="400px";
	 //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽  
	 var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;  
	 var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;  
	 myWindow=window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');  

	 myWindow.focus();
 }  
</script>
</html>