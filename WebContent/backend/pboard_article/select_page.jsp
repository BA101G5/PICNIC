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

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
  <font color='red'>�Эץ��H�U���~:
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
        <b>��J�d���s�� (�pAB00000001):</b>
        <input type="text" name="article_no">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="pboard_articleSvc" scope="page" class="com.pboard_article.model.Pboard_ArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="pboard_article.do" >
       <b>��ܯd���s��:</b>
       <select size="1" name="article_no">
         <c:forEach var="pboard_articleVO" items="${pboard_articleSvc.all}" > 
          <option value="${pboard_articleVO.article_no}">${pboard_articleVO.article_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="pboard_article.do" >
       <b>��ܯd�����D:</b>
       <select size="1" name="article_no">
         <c:forEach var="pboard_articleVO" items="${pboard_articleSvc.all}" > 
          <option value="${pboard_articleVO.article_no}">${pboard_articleVO.article_title}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
  
  
<%--    <jsp:useBean id="general_memberSvc" scope="page" class="com.general_member.model.GeneralMemberService" /> --%>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="general_member.do" > -->
<!--        <b><font color=orange>��ܧ@��:</font></b> -->
<!--        <select size="1" name="mem_no"> -->
<%--          <c:forEach var="general_memberVO" items="${general_memberSvc.all}" >  --%>
<%--           <option value="${general_memberVO.mem_no}">${general_memberVO.dname} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="�e�X"> -->
<!--        <input type="hidden" name="action" value="listPboard_Articles_ByGeneralMemberno_A"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->

<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
<!-- <ul>   -->
<!--   <li>    -->
<!--     <FORM METHOD="post" ACTION="pboard_article.do" name="form1"> -->
<!--         <b><font color=blue>�U�νƦX�d��:</font></b> <br> -->
<!--         <b>��J�d���s��:</b> -->
<!--         <input type="text" name="article_no" value="AB00000001"><br> -->
           
<!--        <b>��J�d�����D:</b> -->
<!--        <input type="text" name="article_title" value="[���D]1234"><br> -->
       
<!--        <b>��J�d������:</b> -->
<!--        <input type="text" name="article_text" value="PRESIDENT"><br> -->
    
<!--        <b>��ܧ@��:</b> -->
<!--        <select size="1" name="mem_no" > -->
<!--           <option value=""> -->
<%--          <c:forEach var="general_memberVO" items="${general_memberSvc.all}" >  --%>
<%--           <option value="${general_memberVO.mem_no}">${general_memberVO.dname} --%>
<%--          </c:forEach>    --%>
<!--        </select><br> -->
           
<!--        <b>���Τ��:</b> -->
<!-- 		    <input class="cal-TextBox" -->
<!-- 			onFocus="this.blur()" size="9" readonly type="text" name="hiredate" value="1981-11-17"> -->
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a> -->
		        
<!--         <input type="submit" value="�e�X"> -->
<!--         <input type="hidden" name="action" value="listPboard_Articles_ByCompositeQuery"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<h3>�d���޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/backend/pboard_article/addPboard_Article.jsp'>Add</a> a new Pboard_Article.</li>
</ul>

<!-- <h3><font color=orange>�@�̺޲z</font></h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/general_member/listAllGeneralMember.jsp'>List</a> all GeneralMembers. </li> --%>
<!-- </ul> -->

</body>

</html>
