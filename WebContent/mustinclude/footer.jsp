<%@ page contentType="text/css; charset=UTF-8" pageEncoding="Big5" %>
<footer class="footer">
    <div class="container">
        <nav class="footer-nav">
            <div class="column">
                <div class="title">MAIN</div>
                <ul>
                    <li><a href="https://github.com/BA101G5">Github專案</a></li>
                 
                </ul>
            </div>
            <div class="column">
                <div class="title">AWS<a></a></div>
                <ul>
                    <li><a href="https://54.64.254.206/my-work-rep-picnic-/">AWS版本</a></li>
                
                </ul>
            </div>
            <div class="column">
                <div class="title">會員</div>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/signin.jsp">會員登入</a></li>
                 
                </ul>
            </div>
            <div class="column">
                <div class="title">管理員</div>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/login.jsp">管理員登入</a></li>
                </ul>
            </div>
        </nav>
    </div>
</footer>