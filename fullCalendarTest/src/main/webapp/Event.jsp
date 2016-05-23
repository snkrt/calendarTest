<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventForm</title>

<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<style type="text/css">
#calendar {
	width: 960px;
	margin: 20px auto 10px auto
}

.fancy {
	width: 300px;
	height: auto
}

.fancy h3 {
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #d3d3d3;
	font-size: 14px
}

.fancy form {
	padding: 10px
}

.fancy p {
	height: 28px;
	line-height: 28px;
	padding: 4px;
	color: #999
}

.input {
	height: 20px;
	line-height: 20px;
	padding: 2px;
	border: 1px solid #d3d3d3;
	width: 100px
}

.btn {
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	padding: 5px 12px;
	cursor: pointer
}

.btn_ok {
	background: #360;
	border: 1px solid #390;
	color: #fff
}

.btn_cancel {
	background: #f0f0f0;
	border: 1px solid #d3d3d3;
	color: #666
}

.btn_del {
	background: #f90;
	border: 1px solid #f80;
	color: #fff
}

.sub_btn {
	height: 32px;
	line-height: 32px;
	padding-top: 6px;
	border-top: 1px solid #f0f0f0;
	text-align: right;
	position: relative
}

.sub_btn .del {
	position: absolute;
	left: 2px
}
</style>

<script type="text/javascript" src="js/jquery.form.min.js">
	
	$(document).ready(function(){ 
		
		
	    $(".datepicker").datepicker();//调用日历选择器 
	    $("#isallday").click(function(){//是否是全天事件 
	        if($("#sel_start").css("display")=="none"){ 
	            $("#sel_start,#sel_end").show(); 
	        }else{ 
	            $("#sel_start,#sel_end").hide(); 
	        } 
	    }); 
	     
	    $("#isend").click(function(){//是否有结束时间 
	        if($("#p_endtime").css("display")=="none"){ 
	            $("#p_endtime").show(); 
	        }else{ 
	            $("#p_endtime").hide(); 
	        } 
	        $.fancybox.resize();//调整高度自适应 
	    }); 
	    
	    $("#del_event").click(function(){ 
	        if(confirm("您确定要删除吗？")){ 
	            var eventid = <%=request.getParameter("id")%>; 
	            
	            $.get("AddEditEvent?action=del&id="+eventid,function(msg){ 
	               
	                    $.fancybox.close(); 
	                    $('#calendar').fullCalendar('refetchEvents'); 
        }); 
     }     
	});
	    
	
	$(function(){ 
	    //提交表单 
	    $('#add_form').ajaxForm({ 
	        beforeSubmit: showRequest, //表单验证 
	        success: showResponse //成功返回 
	    });  
	}); 
	 
	function showRequest(){ 
	    var events = $("#event").val(); 
	    if(events==''){ 
	        alert("请输入日程内容！"); 
	        $("#event").focus(); 
	        return false; 
	    } 
	} 
	 
	
	
	
	function showResponse(responseText, statusText, xhr, $form){ 
	    if(statusText=="success"){     
	        if(responseText==1){ 
	            $.fancybox.close();//关闭弹出层 
	            $('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据 
	        }else{ 
	            alert(responseText); 
	        } 
	    }else{ 
	        alert(statusText); 
	    } 
	} 
	
	});
</script> 

</head>
<body>
	<div class="fancy">
		<h3>新建預約</h3>
		<form id="add_form" action="AddEditEvent" method="post">
			<input type="hidden" name="action" value="add">
			<p>
				預約人員：<input type="text" class="input" name="title" id="title"
					style="width: 100px" placeholder="輸入學號....">
			</p>
			<p>
				預約日期：<input type="text" class="input datepicker" name="startdate"
					id="startdate" value=<%=request.getParameter("start")%>>
					<span id="sel_start" style="display: none">
						<select name="s_hour">
							<option value="00">00</option> 
							<!--省略多个option，下同-->
						</select>: 
						<select name="s_minute">
							<option value="00" selected>00</option> 
						</select> 
					</span>
			</p>
			<p id="p_endtime" style="display: none">
				結束時間：<input type="text" class="input datepicker" name="enddate"	
					id="enddate" value=<%=request.getParameter("end") %>> <span
					id="sel_end" style="display: none"><select name="e_hour">
						<option value="00">00</option> 
				</select>: <select name="e_minute">
						<option value="00" selected>00</option>
				</select> </span>
			</p>
			<!-- <p>
				<label><input type="checkbox" value="1" id="isallday"
					name="isallday" checked> 全天</label> <label><input
					type="checkbox" value="1" id="isend" name="isend"> 结束时间</label>
			</p> -->
			<div class="sub_btn">
				<span class="del">
				<input type="button" class="btn btn_del" id="del_event" value="刪除"  onclick="del()"></span> 
				<script type="text/javascript">
				function del(){
				
		        if(confirm("您確定要删除吗？")){ 
		            var eventid = <%=request.getParameter("id")%>; 
		            
		            $.get("AddEditEvent?action=del&id="+eventid,function(msg){ 
		               
		                    $.fancybox.close(); 
		                    $('#calendar').fullCalendar('refetchEvents'); //重新获取所有事件数据 
		                   /*  if(msg==1){//删除成功 
		                }else{ 
		                    alert(msg);     
		                }  */
		            }); 
		        } }
				</script>
				<input type="submit" class="btn btn_ok" value="確定">
				<!-- <input type="button" class="btn btn_cancel" value="取消" onClick="$.fancybox.close()"> -->
			</div>
		</form>
	</div>
</body>
</html>