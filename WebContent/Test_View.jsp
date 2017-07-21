<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
		<title>Insert title here</title>
		
		<script type="text/javascript">
			
			function checkBoxShow(event){
				var button = event.target;
				var checkBox = button.previousSibling.previousSibling;
				checkBox.style.display="inline";
				button.type="hidden";
			}
		</script>
	</head>
	
	<body>
		查詢所有管理員
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm.do" >
	        <input type="submit" value="查詢">
	        <input type="hidden" name="button" value="get_adm_one_data">
	    </FORM> 
	    
	           管理員編號+權限編號（MA00000005PU00000004）
	    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/k_man_men_data.do" >
	        <b>輸入編號:</b>
	        <input type="text" name="admno">
	        <input type="submit" value="送出">
	        <input type="hidden" name="button" value="back">
	    </FORM>
	  
	  	加團/退團
	  
	  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm.do" >
	  		<input type="text" name="MEM_NO">
	        <input type="submit" value="查詢">
	        <input type="hidden" name="button" value="enterOrQuit">
	    </FORM> 
	  	
	  	歷史清單
	  
	  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm.do" >
	  		<input type="text" name="MEM_NO">
	        <input type="submit" value="查詢">
	        <input type="hidden" name="button" value="history">
	    </FORM> 
	  	
	  	評價/檢舉
	  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm.do" >
	  		<input type="text" name="MEM_NO">
	        <input type="submit" value="查詢">
	        <input type="hidden" name="button" value="commend">
	    </FORM> 
	  	
	  	coin
	  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm.do" >
	  		<input type="text" name="MEM_NO">
	        <input type="submit" value="查詢">
	        <input type="hidden" name="button" value="coin">
	    </FORM>
	  	
	  	
	  	權限編號（PU00000008）
	      
	        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/test.do" enctype="multipart/form-data">
				<div style="display:none;">
					<input type="checkbox" name="checked" class="error" value="1" />
					<input type="checkbox" name="checked" class="error" value="2" />
					<input type="checkbox" name="checked" class="error" value="3" />
				</div>
					<input type="button" name="ddsfsdf" onclick="checkBoxShow(event)" value="button">
				
					<input type="checkbox" name="checked" class="error" value="4" />
					<input type="checkbox" name="checked" class="error" value="5" />
					<input type="submit" value="送出">
			</FORM>
	</body>
</html>