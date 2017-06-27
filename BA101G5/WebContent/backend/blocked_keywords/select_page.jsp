<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>BA101G5 Blocked_Keywords: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>BA101G5 Blocked_Keywords: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for BA101G5 Blocked_Keywords: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllBlocked_Keywords.jsp'>List</a> all Blocked_Keywordss. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="blocked_keywords.do" >
        <b>輸入關鍵字編號 (如BK00000001):</b>
        <input type="text" name="keyword_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blocked_keywordsSvc" scope="page" class="com.blocked_keywords.model.Blocked_KeywordsService" />
   
  <li>
     <FORM METHOD="post" ACTION="blocked_keywords.do" >
       <b>選擇關鍵字編號:</b>
       <select size="1" name="keyword_no">
         <c:forEach var="blocked_keywordsVO" items="${blocked_keywordsSvc.all}" > 
          <option value="${blocked_keywordsVO.keyword_no}">${blocked_keywordsVO.keyword_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="blocked_keywords.do" >
       <b>選擇關鍵字:</b>
       <select size="1" name="keyword_no">
         <c:forEach var="blocked_keywordsVO" items="${blocked_keywordsSvc.all}" > 
          <option value="${blocked_keywordsVO.keyword_no}">${blocked_keywordsVO.keyword}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>關鍵字管理</h3>

<ul>
  <li><a href='addBlocked_Keywords.jsp'>Add</a> a new Blocked_Keywords.</li>
</ul>

</body>

</html>
