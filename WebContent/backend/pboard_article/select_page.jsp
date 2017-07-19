<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Pboard_Article: Home</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Pboard_Article: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Pboard_Article: Home</p>

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
  <li><a href='listAllPboard_Article.jsp'>List</a> all Pboard_Articles. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="pboard_article.do" >
        <b>輸入留言編號 (如AB00000001):</b>
        <input type="text" name="article_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="pboard_articleSvc" scope="page" class="com.pboard_article.model.Pboard_ArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="pboard_article.do" >
       <b>選擇留言編號:</b>
       <select size="1" name="article_no">
         <c:forEach var="pboard_articleVO" items="${pboard_articleSvc.all}" > 
          <option value="${pboard_articleVO.article_no}">${pboard_articleVO.article_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="pboard_article.do" >
       <b>選擇留言標題:</b>
       <select size="1" name="article_no">
         <c:forEach var="pboard_articleVO" items="${pboard_articleSvc.all}" > 
          <option value="${pboard_articleVO.article_no}">${pboard_articleVO.article_title}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
  
  
<%--    <jsp:useBean id="general_memberSvc" scope="page" class="com.general_member.model.GeneralMemberService" /> --%>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="general_member.do" > -->
<!--        <b><font color=orange>選擇作者:</font></b> -->
<!--        <select size="1" name="mem_no"> -->
<%--          <c:forEach var="general_memberVO" items="${general_memberSvc.all}" >  --%>
<%--           <option value="${general_memberVO.mem_no}">${general_memberVO.dname} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="listPboard_Articles_ByGeneralMemberno_A"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->

<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<!-- <ul>   -->
<!--   <li>    -->
<!--     <FORM METHOD="post" ACTION="pboard_article.do" name="form1"> -->
<!--         <b><font color=blue>萬用複合查詢:</font></b> <br> -->
<!--         <b>輸入留言編號:</b> -->
<!--         <input type="text" name="article_no" value="AB00000001"><br> -->
           
<!--        <b>輸入留言標題:</b> -->
<!--        <input type="text" name="article_title" value="[標題]1234"><br> -->
       
<!--        <b>輸入留言內文:</b> -->
<!--        <input type="text" name="article_text" value="PRESIDENT"><br> -->
    
<!--        <b>選擇作者:</b> -->
<!--        <select size="1" name="mem_no" > -->
<!--           <option value=""> -->
<%--          <c:forEach var="general_memberVO" items="${general_memberSvc.all}" >  --%>
<%--           <option value="${general_memberVO.mem_no}">${general_memberVO.dname} --%>
<%--          </c:forEach>    --%>
<!--        </select><br> -->
           
<!--        <b>雇用日期:</b> -->
<!-- 		    <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="hiredate" value="1981-11-17"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
		        
<!--         <input type="submit" value="送出"> -->
<!--         <input type="hidden" name="action" value="listPboard_Articles_ByCompositeQuery"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<h3>留言管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/backend/pboard_article/addPboard_Article.jsp'>Add</a> a new Pboard_Article.</li>
</ul>

<!-- <h3><font color=orange>作者管理</font></h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/general_member/listAllGeneralMember.jsp'>List</a> all GeneralMembers. </li> --%>
<!-- </ul> -->

</body>

</html>
