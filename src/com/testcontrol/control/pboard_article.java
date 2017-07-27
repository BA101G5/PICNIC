package com.testcontrol.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/k_pboard_article.do")
public class pboard_article extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
	
		/*if(true){
		String para = null;
		Enumeration<String> e = req.getParameterNames();

		while(e.hasMoreElements()) {
			para = e.nextElement();
			if(para != null){
				System.out.println(para);
				System.out.println(req.getParameter(para));
				}
			}
		}*/
		
		
		
		String url = "/view/pboard_article.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		
		
	}
}
