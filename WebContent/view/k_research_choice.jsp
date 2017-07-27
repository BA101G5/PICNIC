<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.administrator.model.*"
		 import="com.purview_detail.model.*"
		 import="com.purview.model.*"
		 import = "java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!----------->

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Picnic野餐網</title>
<meta name="description" content="">
<meta name="keywords" content="">
<jsp:include page="/mustinclude/head.jsp" />
<!----------->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

<!-- -------- -->
<title>Insert title here</title>
<style type="text/css">
  div.k_class   #wrapper {
        width:850px;
        margin:100px auto;
        font-family:Verdana, sans-serif;
    }
  div.k_class   legend {
        color:#0481b1;
        font-size:16px;
        padding:0 10px;
        background:#fff;
        -moz-border-radius:4px;
        box-shadow: 0 1px 5px rgba(4, 129, 177, 0.5);
        padding:5px 40px;
        text-transform:uppercase;
        font-family:Helvetica, sans-serif;
        font-weight:bold;
    }
  div.k_class   fieldset {
        border-radius:4px;
        background: #fff; 
        background: -moz-linear-gradient(#fff, #f9fdff);
        background: -o-linear-gradient(#fff, #f9fdff);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#f9fdff)); /
        background: -webkit-linear-gradient(#fff, #f9fdff);
        padding:20px;
        border-color:rgba(4, 129, 177, 0.4);
    }
    
    <!---->
@import url('https://fonts.googleapis.com/css?family=Open+Sans:400,600,700');
@import url('https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css');


div.k_class div.select_tabs {
  min-width: 320px;
  max-width: 800px;
  padding: 10px;
  margin: 0 auto;
  background: #FFF;
}

div.k_class section {
  display: none;
  padding: 20px 0 0;
  border-top: 1px solid #ddd;
}

div.k_class input.tabs {
  display: none;
}

div.k_class label.tabs {
  display: inline-block;
  margin: 0 0 -1px;
  padding: 15px 25px;
  font-weight: 600;
  text-align: center;
  color: #bbb;
  border: 1px solid transparent;
}

div.k_class label.tabs:before {
  font-family: fontawesome;
  font-weight: normal;
  margin-right: 10px;
}


div.k_class label.tabs:hover {
  color: #888;
  cursor: pointer;
}

div.k_class input.tabs:checked + label {
  color: #555;
  border: 1px solid #ddd;
  border-top: 2px solid #0F0;
  border-bottom: 1px solid #fff;
}

div.k_class #tab1:checked ~ #content1,
div.k_class #tab2:checked ~ #content2 {
  display: block;
}

@media screen and (max-width: 650px) {
  label {
    font-size: 0;
  }
  label:before {
    margin: 0;
    font-size: 18px;
  }
}

@media screen and (max-width: 400px) {
  label {
    padding: 15px;
  }
}
<!---->
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro);

div.k_class form {
    margin-left:20px;
    margin-right:20px;
    width: 630px;
    height: 333px;
    padding:30px;
    border: 1px solid rgba(0,0,0,.2);
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-background-clip: padding;
    -webkit-background-clip: padding-box;
    background-clip: padding-box;
    background: rgba(230, 230, 230, 0.5); 
    -moz-box-shadow: 0 0 13px 3px rgba(200,200,200,.5);
    -webkit-box-shadow: 0 0 13px 3px rgba(200,200,200,.5);
    box-shadow: 0 0 13px 3px rgba(200,200,200,.5);
    overflow: hidden; 
}

div.k_class textarea{
	  background: rgba(255, 255, 255, 0.4) url(http://luismruiz.com/img/gemicon_message.png) no-repeat scroll 16px 16px; 
    width: 276px;
    height: 110px;
    border: 1px solid rgba(255,255,255,.6);
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
    -moz-background-clip: padding;
    -webkit-background-clip: padding-box;
    background-clip: padding-box; 
    display:block;
    font-family: 'Source Sans Pro', sans-serif;
    font-size:18px;
    color:#AAA;
    padding-left:45px;
    padding-right:20px;
    padding-top:12px;
    margin-bottom:20px;
    overflow:hidden;
}

div.k_class input {
    width: 276px;
    height: 48px;
    border: 1px solid rgba(255,255,255,.4);
    -moz-border-radius: 4px;
    -webkit-border-radius: 4px;
    border-radius: 4px;
    -moz-background-clip: padding;
    -webkit-background-clip: padding-box;
    background-clip: padding-box; 
    display:inline;
    font-family: 'Source Sans Pro', sans-serif;
    font-size:18px;
    color:#AAA;
    padding-left:20px;
    padding-right:20px;
    margin-bottom:20px;
}

div.k_class input[type=submit] {
    cursor:pointer;
}

div.k_class input.name {
	  background: rgba(255, 255, 255, 0.4) url(http://luismruiz.com/img/gemicon_name.png) no-repeat scroll 16px 16px; 
	  padding-left:45px;
}

div.k_class input.email {
	  background: rgba(255, 255, 255, 0.4) url(http://luismruiz.com/img/gemicon_email.png) no-repeat scroll 16px 20px;
	  padding-left:45px;
}

div.k_class input.message {
	  background: rgba(255, 255, 255, 0.4) url(http://luismruiz.com/img/gemicon_message.png) no-repeat scroll 16px 16px;
	  padding-left:45px;
}

::-webkit-input-placeholder {
	  color: #AAA;
}

:-moz-placeholder{ 
    color: #AAA; 
}

::-moz-placeholder {
    color: #AAA;
}

:-ms-input-placeholder {  
	  color: #AAA; 
}

div.k_class input:focus, textarea:focus { 
	  background-color: rgba(200, 200, 200, 0.2);
    -moz-box-shadow: 0 0 5px 1px rgba(255,255,255,.5);
    -webkit-box-shadow: 0 0 5px 1px rgba(255,255,255,.5);
    box-shadow: 0 0 5px 1px rgba(0,0,0,.5);
	  overflow: hidden; 
}

div.k_class .btn {
	  width: 138px;
	  height: 44px;
	  -moz-border-radius: 4px;
	  -webkit-border-radius: 4px;
	  border-radius: 4px;
	  float:right;
    border: 1px solid #253737;
    background: #416b68;
    background: -webkit-gradient(linear, left top, left bottom, from(#6da5a3), to(#416b68));
    background: -webkit-linear-gradient(top, #6da5a3, #416b68);
    background: -moz-linear-gradient(top, #6da5a3, #416b68);
    background: -ms-linear-gradient(top, #6da5a3, #416b68);
    background: -o-linear-gradient(top, #6da5a3, #416b68);
    background-image: -ms-linear-gradient(top, #6da5a3 0%, #416b68 100%);
    padding: 10.5px 21px;
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: rgba(255,255,255,0.1) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
    -moz-box-shadow: rgba(255,255,255,0.1) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
    box-shadow: rgba(255,255,255,0.1) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
    text-shadow: #333333 0 1px 0;
    color: #e1e1e1;
}

div.k_class .btn:hover {
    border: 1px solid #253737;
    text-shadow: #333333 0 1px 0;
    background: #416b68;
    background: -webkit-gradient(linear, left top, left bottom, from(#77b2b0), to(#416b68));
    background: -webkit-linear-gradient(top, #77b2b0, #416b68);
    background: -moz-linear-gradient(top, #77b2b0, #416b68);
    background: -ms-linear-gradient(top, #77b2b0, #416b68);
    background: -o-linear-gradient(top, #77b2b0, #416b68);
    background-image: -ms-linear-gradient(top, #77b2b0 0%, #416b68 100%);
    color: #fff;
 }

div.k_class .btn:active {
    margin-top:1px;
    text-shadow: #333333 0 -1px 0;
    border: 1px solid #253737;
    background: #6da5a3;
    background: -webkit-gradient(linear, left top, left bottom, from(#416b68), to(#416b68));
    background: -webkit-linear-gradient(top, #416b68, #609391);
    background: -moz-linear-gradient(top, #416b68, #6da5a3);
    background: -ms-linear-gradient(top, #416b68, #6da5a3);
    background: -o-linear-gradient(top, #416b68, #6da5a3);
    background-image: -ms-linear-gradient(top, #416b68 0%, #6da5a3 100%);
    color: #fff;
    -webkit-box-shadow: rgba(255,255,255,0) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
    -moz-box-shadow: rgba(255,255,255,0) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
    box-shadow: rgba(255,255,255,0) 0 1px 0, inset rgba(255,255,255,0.7) 0 1px 0;
   }




</style>  
  <script>
  $(function(){
	    $('.thedate').datepicker({
	   	 		dateFormat: 'yy-mm-dd'
	    });
	});
  
  
  function joutuanDateChange(){
	  var joutuan1 = document.getElementById("joutuan1").value;
	  var joutuan2 = document.getElementById("joutuan2").value;
	  var joutuan3 = document.getElementById("joutuan3");
	  if(joutuan1 != "" && joutuan2 != ""){  
		  joutuan3.value = joutuan1 + "~" + joutuan2;

	  }
	  if(joutuan1 != "" && joutuan2 == ""){
		  joutuan3.value = joutuan1 + "~" + "2030-12-30";
	  }
	  if(joutuan1 == "" && joutuan2 != ""){
		  joutuan3.value = "1990-1-1" + "~" + joutuan2;
	  }
  }
  function liouyanDateChange(){
	  var joutuan1 = document.getElementById("liouyan1").value;
	  var joutuan2 = document.getElementById("liouyan2").value;
	  var joutuan3 = document.getElementById("liouyan3");
	  if(joutuan1 != "" && joutuan2 != ""){  
		  joutuan3.value = joutuan1 + "~" + joutuan2;

	  }
	  if(joutuan1 != "" && joutuan2 == ""){
		  joutuan3.value = joutuan1 + "~" + "2030-12-30";
	  }
	  if(joutuan1 == "" && joutuan2 != ""){
		  joutuan3.value = "1990-1-1" + "~" + joutuan2;
	  }
  }
  
  </script>
</head>
<body>



<jsp:include page="/mustinclude/left_nav.jsp" />
<jsp:include page="/mustinclude/top_nav.jsp" />

<div class="k_class">
<div id="wrapper">
            <fieldset>
                <legend>查詢</legend>
                <div class="select_tabs">
				  <input id="tab1" type="radio" name="tabs" checked class="tabs">
				  <label for="tab1" class="tabs">揪團查詢</label>			
				  	
				  <input id="tab2" type="radio" name="tabs" class="tabs">
				  <label for="tab2" class="tabs">留言板查詢</label>		
			    
				  <section id="content1">
					<form id="joutuan" action="<%=request.getContextPath()%>/research.kdo" method="post" name="select_joutuan">
						<input name="joutuan_name" placeholder="野餐團名稱" class="name"  />
						<input name="joutuan_limit" placeholder="人數上限" class="name"  />
						<input placeholder="揪團日期(起)" class="thedate" id="joutuan1" onchange="joutuanDateChange()" />
						<input placeholder="揪團日期(訖)" class="thedate" id="joutuan2" onchange="joutuanDateChange()" />
						<input name="joutuan_date" class="name" type="hidden" id="joutuan3" />
						<input type="hidden" name="button" value="joutuan_button" />
						<input name="joutuan_button" class="btn" type="submit" value="Send" />
					</form>	
				  </section>
				    
				  <section id="content2">
				    <form id="liouyan" action="<%=request.getContextPath()%>/research.kdo" method="post" name="select_liouyan">
						<input name="liouyan_name" placeholder="名字" class="name"  />
						<input name="liouyan_mail" placeholder="信箱" class="name"  />
						<input name="liouyan_title" placeholder="標題" class="name"  />
						<input name="liouyan_text" placeholder="內文" class="name"  />
						<input placeholder="日期(起)" class="thedate" id="liouyan1" onchange="liouyanDateChange()" />
						<input placeholder="日期(訖)" class="thedate" id="liouyan2" onchange="liouyanDateChange()" />
						<input name="liouyan_time" class="name" type="hidden" id="liouyan3" />
						<input type=hidden name="button" value="liouyan_button" />
						<input name="liouyan_button" class="btn" type="submit" value="Send" />
					</form>	
				  </section>   
				  
				  
				</div>
            </fieldset>    
    </div>
  </div>
<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>