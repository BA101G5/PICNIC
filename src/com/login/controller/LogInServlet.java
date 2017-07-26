package com.login.controller;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.login.model.LogInService;
import com.login.model.LogInVO;

import javax.servlet.annotation.WebServlet;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> erroMsgs = new LinkedList<String>();
	LogInVO loginVO = null;
   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
  protected LogInVO allowUser(String adm_acc, String adm_pw) {
	  if(!erroMsgs.isEmpty()){
  		erroMsgs.removeAll(erroMsgs);
	  }
	  LogInService loginSvc = new LogInService();
	  loginVO = loginSvc.getOnelogin(adm_acc);
	  System.out.println(loginVO);
	  
	  if(loginVO == null){
		  erroMsgs.add("請輸入帳號");
		  return null;
	  }else{
		  if(loginVO.getAdm_acc().equals(adm_acc) && loginVO.getAdm_pw().equals(adm_pw)){
			  System.out.println("帳密正確");
			  return loginVO;
		  }else{
			  erroMsgs.add("密碼錯誤");
		  }
		  return null;
	  }   
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // 【取得使用者 帳號(account) 密碼(password)】
    String adm_acc = req.getParameter("adm_acc");
    String adm_pw = req.getParameter("adm_pw");

    // 【檢查該帳號 , 密碼是否有效】
    if (allowUser(adm_acc, adm_pw)==null) {          //【帳號 , 密碼無效時】
//      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
//      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
//      //out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/login.html>重新登入</A>");
//      out.println("</BODY></HTML>");
    	req.setAttribute("errorMsgs"," * 帳密錯誤"); 
    	RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
		failureView.forward(req, res);
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
      session.setAttribute("loginVO", loginVO);   //*工作1: 才在session內做已經登入過的標識
      System.out.println(loginVO.getAdm_iden());
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/checklist/TEST_HOME.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}




















//	public class LogInServlet extends HttpServlet {
////		protected boolean allowUser(String account, String password) {
////		    if ("tomcat".equals(account) && "tomcat".equals(password))
////		      return true;
////		    else
////		      return false;
////		  }
//	
//		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			doPost(req, res);
//		}
////		protected boolean allowUser(String adm_acc, String adm_pw) {
////		    if ("123".equals(adm_acc) && "123".equals(adm_pw))
////		      return true;
////		    else
////		      return false;
////		  }
//		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			req.setCharacterEncoding("UTF-8");
//			
//			
//			String adm_acc = req.getParameter("adm_acc");
//			String adm_pw = req.getParameter("adm_pw");
//			System.out.println("inlog");
//			System.out.println(adm_acc);
//			System.out.println(adm_pw);
//			
//			LogInService loginSvc = new LogInService();
//			LogInVO loginVO = loginSvc.getOnelogin(adm_acc);
//			System.out.println(loginVO);
//			System.out.println("帳號: "+loginVO.getAdm_acc());
//			System.out.println("密碼: "+loginVO.getAdm_pw());
//			if(adm_acc.equals(loginVO.getAdm_acc())){
//				System.out.println("帳號正確");
//				if(adm_pw.equals(loginVO.getAdm_pw())){
//					System.out.println("密碼正確");
//					System.out.println(loginVO.getAdm_iden());
//					String iden = "123";
//					HttpSession session = req.getSession();
//				    session.setAttribute("iden", iden);
//					
//					
//					String url = "/checklist/TEST_HOME.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);
//					successView.forward(req, res);
//					
//				}
//			}
////			System.out.println(allowUser(adm_acc, adm_pw));
//			
//		}
//}
