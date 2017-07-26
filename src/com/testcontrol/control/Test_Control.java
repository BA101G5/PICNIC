package com.testcontrol.control;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.administrator.model.*;
import com.manufacturers.model.*;
//import com.purview.model.PurviewDAO;
//import com.purview.model.PurviewVO;
//import com.purview_detail.model.*;

public class Test_Control extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String no = req.getParameter("admno");
		String result = "";
		System.out.println("CONXXX");
		if ("get_adm_one_data".equals(action)){
			System.out.println("123123");
			AdministratorDAO admDAO = new AdministratorDAO();
			List<AdministratorVO> admVO = admDAO.getAll();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				result = "adm";
				req.setAttribute("result", result);
				req.setAttribute("admVO", admVO);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		
		
		if("man_men".equals(action)){
			
			ManufacturersService maS = new ManufacturersService();
			List<ManufacturersVO> maList = maS.getAll();
			
			req.setAttribute("maList", maList);
			String url = "/view/man_mem_data.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}		
	}
}