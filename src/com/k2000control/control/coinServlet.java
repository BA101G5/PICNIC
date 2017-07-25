package com.k2000control.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.*;

@WebServlet("/coin.do")
public class coinServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
		
		if(true){
		String para = null;
		Enumeration<String> e = req.getParameterNames();

			while(e.hasMoreElements()) {
				para = e.nextElement();
				if(para != null){
					System.out.println(para);
					System.out.println(req.getParameter(para));
				}
			}
		}
	
		if("addCoin".equals(button)){
			
			HttpSession session = req.getSession();
			String mem = (String) session.getAttribute("MEM_NO");
			
			int coinNumber = Integer.parseInt(req.getParameter("points"));
			GeneralMemberService gmS = new GeneralMemberService();
			GeneralMemberVO gmVO = gmS.k_getOneGeneralMember(mem);
			gmVO.setMEM_COIN(gmVO.getMEM_COIN()+coinNumber);
			gmS.k_updateGeneralMember(gmVO);
			
		}
	
	
	
	
	
	
	
	}
}
	
