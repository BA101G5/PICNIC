<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<div class="container">
  <div class="row">
  	<div class="col-sm-12  ">
            <nav class="navbar navbar-default " role="navigation">
                 <div class="col-sm-10 ">
                            <div class="navbar-header">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" style="padding: 4px;"><img src="<%=request.getContextPath()%>/mustinclude/logo.png" style="height: 100%;"></a>
                        </div>
                
                        <!-- 左選單 -->
                        <ul class="nav navbar-nav">
                            <li><a href="#">參團</a></li>
                            <li><a href="<%=request.getContextPath()%>/picnic/maosecondui.jsp">開團</a></li>
                            <li><a href="<%=request.getContextPath()%>/buycart/moafirst.jsp">預購商品</a></li>
                               <li><a href="#">成團</a></li>
                            <li><a href="forum.html">討論區</a></li>
                         
                        </ul>

                        <!-- 右選單 -->
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">shyangs</a></li>
                             <li><a href="<%=request.getContextPath() %>/orderde_detail/orderde_detailui.jsp" ><span class="glyphicon glyphicon-shopping-cart"></span></a></li>
                        </ul>

                        <!-- 搜尋表單 -->
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="請輸入關鍵字">
                            </div>
                            <button type="submit" class="btn btn-default">搜尋</button>
                        </form>
                    
                </div>
              
            </nav>
          </div>
  
  </div>
</div>