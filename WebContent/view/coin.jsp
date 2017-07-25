<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

</script>


</head>
<style type="text/css">
    #wrapper {
        width:450px;
        margin:100px auto;
        font-family:Verdana, sans-serif;
    }
    legend {
        color:#0481b1;
        font-size:16px;
        padding:0 10px;
        background:#fff;
        -moz-border-radius:4px;
        box-shadow: 0 1px 5px rgba(4, 129, 177, 0.5);
        padding:5px 10px;
        text-transform:uppercase;
        font-family:Helvetica, sans-serif;
        font-weight:bold;
    }
    fieldset {
        border-radius:4px;
        background: #fff; 
        background: -moz-linear-gradient(#fff, #f9fdff);
        background: -o-linear-gradient(#fff, #f9fdff);
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#f9fdff)); /
        background: -webkit-linear-gradient(#fff, #f9fdff);
        padding:20px;
        border-color:rgba(4, 129, 177, 0.4);
    }
    input,
    textarea {
        color: #373737;
        background: #fff;
        border: 1px solid #CCCCCC;
        color: #aaa;
        font-size: 14px;
        line-height: 1.2em;
        margin-bottom:15px;

        -moz-border-radius:4px;
        -webkit-border-radius:4px;
        border-radius:4px;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1) inset, 0 1px 0 rgba(255, 255, 255, 0.2);
    }
    input[type="text"],
    input[type="password"]{
        padding: 8px 6px;
        height: 22px;
        width:280px;
    }
    input[type="text"].safe{
    	padding: 8px 6px;
        height: 15px;
        width:100px;
    }
    input[type="number"]{
    	padding: 8px 6px;
        height: 15px;
        width:100px;
    }
    input[type="password"]:focus {
        background:#f5fcfe;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        -webkit-transition-duration: 400ms;
        -webkit-transition-property: width, background;
        -webkit-transition-timing-function: ease;
        -moz-transition-duration: 400ms;
        -moz-transition-property: width, background;
        -moz-transition-timing-function: ease;
        -o-transition-duration: 400ms;
        -o-transition-property: width, background;
        -o-transition-timing-function: ease;
        width: 380px;
        
        border-color:#ccc;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        opacity:0.6;
    }
    button{
        background: #222;
        border: none;
        text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
        text-transform:uppercase;
        color: #eee;
        cursor: pointer;
        font-size: 15px;
        margin: 5px 0;
        padding: 5px 22px;
        -moz-border-radius: 4px;
        border-radius: 4px;
        -webkit-border-radius:4px;
        -webkit-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        -moz-box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
        box-shadow: 0px 1px 2px rgba(0,0,0,0.3);
    }
    textarea {
        padding:3px;
        width:96%;
        height:100px;
    }
    textarea:focus {
        background:#ebf8fd;
        text-indent: 0;
        z-index: 1;
        color: #373737;
        opacity:0.6;
        box-shadow:0 0 5px rgba(4, 129, 177, 0.5);
        border-color:#ccc;
    }
    .small {
        line-height:14px;
        font-size:12px;
        color:#999898;
        margin-bottom:3px;
    }
    
    .select-style {
	  -webkit-appearance: button;
	  -webkit-border-radius: 2px;
	  -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
	  -webkit-padding-end: 20px;
	  -webkit-padding-start: 2px;
	  -webkit-user-select: none;
	  background-image: url(images/select-arrow.png),
	  -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
	  background-position: center right;
	  background-repeat: no-repeat;
	  border: 0px solid #FFF;
	  color: #555;
	  font-size: inherit;
	  margin: 0;
	  overflow: hidden;
	  padding-top: 5px;
	  padding-bottom: 5px;
	  text-overflow: ellipsis;
  	  white-space: nowrap;
  }
    
</style>
<body>
    <div id="wrapper">
        <form action="${pageContext.request.contextPath}/coin.do" method="post" name="coinTable">
            <fieldset>
                <legend>點數儲值</legend>
                <div>
                	信用卡卡號:<br>
                    <input type="text" name="cardNumber"/>
                </div>
                <div>
                	信用卡到期日和安全碼:<br>
                    <select name="month" class="select-style">
						  <option value="---" selected="selected">---</option>
							<c:forEach var = "i" begin = "1" end = "12">
							　<option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<select name="year" class="select-style">
						  <option value="---" selected="selected">---</option>
							<c:forEach var = "i" begin = "2017" end = "2099">
							　<option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<input type="text" name="safeNumber" class="safe" />
                </div>
                <div>
                	購買點數:<br>
					<input type="number" name="points" min="100" max="10000" step="100" value="100" />
                </div>    
                <button type="submit" name="button" value="addCoin"  formaction="${pageContext.request.contextPath}/coin.do">送出</button>
        		<button type="submit" name="button" value="back"  formaction="${pageContext.request.contextPath}/coin.do">返回</button>
            </fieldset>    
        </form>
        
    </div>
</body>
</html>
