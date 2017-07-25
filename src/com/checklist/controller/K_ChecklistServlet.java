package com.checklist.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.checklist.model.*;
import com.general_member.model.*;
import com.manufacturers.model.*;

@WebServlet("/checklist.do")
public class K_ChecklistServlet extends HttpServlet{
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String button = req.getParameter("button");
		String mem = (String) session.getAttribute("MEM_NO");
		
//		if(true){
//		String para = null;
//		Enumeration<String> e = req.getParameterNames();
//
//		while(e.hasMoreElements()) {
//			para = e.nextElement();
//				if(para != null){
//					System.out.println(para);
//					System.out.println(req.getParameter(para));
//					System.out.println("-----------------");
//				}
//			}
//		}
		
		if(button.contains("checklist_mem")){
			String number_no = button.replaceAll("checklist_mem.", "");
			if("MG".equals(number_no.substring(0,2))){			
				GeneralMemberService gmS = new GeneralMemberService();
				GeneralMemberVO gmVO = gmS.k_getOneGeneralMember(number_no);
				req.setAttribute("gmVO", gmVO);
				String url = "/view/checklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			if("MM".equals(number_no.substring(0,2))){			
				
				ManufacturersService mfS =new ManufacturersService();
				ManufacturersVO mfVO = mfS.k_getOneManufacturers(number_no);
				req.setAttribute("mfVO", mfVO);
				String url = "/view/checklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if("checkCertain".equals(button)){
			
			ChecklistService chlS = new ChecklistService();
			ChecklistVO chlVO =new ChecklistVO();
			
			chlVO.setChli_cate(1);
			chlVO.setChli_be_num(req.getParameter("number_no"));
			chlVO.setChli_memno(mem);
			chlVO.setChli_day(0);
			chlVO.setChli_pun(0);
			chlVO.setChli_reason(req.getParameter("content"));
			chlVO.setChli_date(new Timestamp(System.currentTimeMillis()));
			chlVO.setChli_sta("U");
			
			chlS.k_addChecklist(chlVO);
			
			String url = "/commend.do";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("checkCannel".equals(button)){
			
			String url = "/commend.do";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	

		}
	}
}
