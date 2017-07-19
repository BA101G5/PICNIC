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
   //�i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
   //�i��ڤW���ܸ�Ʈw�j�M���j
  protected LogInVO allowUser(String adm_acc, String adm_pw) {
	  if(!erroMsgs.isEmpty()){
  		erroMsgs.removeAll(erroMsgs);
	  }
	  LogInService loginSvc = new LogInService();
	  loginVO = loginSvc.getOnelogin(adm_acc);
	  System.out.println(loginVO);
	  
	  if(loginVO == null){
		  erroMsgs.add("�п�J�b��");
		  return null;
	  }else{
		  if(loginVO.getAdm_acc().equals(adm_acc) && loginVO.getAdm_pw().equals(adm_pw)){
			  System.out.println("�b�K���T");
			  return loginVO;
		  }else{
			  erroMsgs.add("�K�X���~");
		  }
		  return null;
	  }   
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // �i���o�ϥΪ� �b��(account) �K�X(password)�j
    String adm_acc = req.getParameter("adm_acc");
    String adm_pw = req.getParameter("adm_pw");

    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
    if (allowUser(adm_acc, adm_pw)==null) {          //�i�b�� , �K�X�L�Įɡj
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>�A���b�� , �K�X�L��!<BR>");
      //out.println("�Ы������s�n�J <A HREF="+req.getContextPath()+"/login.html>���s�n�J</A>");
      out.println("</BODY></HTML>");
    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
      HttpSession session = req.getSession();
      session.setAttribute("loginVO", loginVO);   //*�u�@1: �~�bsession�����w�g�n�J�L������
      System.out.println(loginVO.getAdm_iden());
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/checklist/TEST_HOME.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
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
//			System.out.println("�b��: "+loginVO.getAdm_acc());
//			System.out.println("�K�X: "+loginVO.getAdm_pw());
//			if(adm_acc.equals(loginVO.getAdm_acc())){
//				System.out.println("�b�����T");
//				if(adm_pw.equals(loginVO.getAdm_pw())){
//					System.out.println("�K�X���T");
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
