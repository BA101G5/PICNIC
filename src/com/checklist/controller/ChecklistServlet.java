package com.checklist.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.checklist.model.ChecklistService;
import com.checklist.model.ChecklistVO;

public class ChecklistServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: "+action);
		System.out.println("inServlet");
		
		if("getOne_Checklist_isFinish".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("chli_no");
				if(str == null || (str.trim()).length() == 0){
					errorMsgs.add("請輸入會員帳號");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String chli_no = null;
				try{
					chli_no = new String(str);
				}catch(Exception e){
					errorMsgs.add("編號格式錯誤");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始查詢資料*****************************************/
				ChecklistService checklistSvc = new ChecklistService();
				ChecklistVO checklistVO = checklistSvc.getOneChecklist(chli_no);
				if(checklistVO == null){
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("checklistVO", checklistVO);
				String url = "/checklist/listOneChecklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if("delete".equals(action)){
			System.out.print("delete");
			
			String requestURL = req.getParameter("requestURL");
			
			String chli_no = new String(req.getParameter("chli_no"));
			String chli_cate = req.getParameter("chli_cate");
			System.out.println(chli_no);
			ChecklistService checklistSvc = new ChecklistService();
			checklistSvc.deleteChecklist(chli_no);
			
			
			
			List<ChecklistVO> checklistVOList = null;
			if(chli_cate.equals("0")){
				checklistVOList = checklistSvc.getAllUndone_0();
			}else if(chli_cate.equals("1")){
				checklistVOList = checklistSvc.getAllUndone_1();
			}else if(chli_cate.equals("2")){
				checklistVOList = checklistSvc.getAllUndone_2();
			}else if(chli_cate.equals("3")){
				checklistVOList = checklistSvc.getAllUndone_3();
			}else{
				checklistVOList = checklistSvc.getAllUndone_4();
				
			}
			
		
			req.setAttribute("checklistVO", checklistVOList);
			
			String undone = req.getParameter("undone");
			System.out.print("undone"+undone);
			req.setAttribute("testundone", undone);
			
			req.setAttribute("testundone", chli_cate);
			String result = "undone";
			req.setAttribute("result", result);
			
			
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("insertChecklist".equals(action)){
			System.out.print("insertChecklist");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			Integer chli_cate = Integer.valueOf(req.getParameter("chli_cate"));
			String chli_be_num = req.getParameter("chli_be_num");
			String chli_memno = req.getParameter("chli_memno");
			String chli_reason = req.getParameter("chli_reason");
			Timestamp chli_date = Timestamp.valueOf(req.getParameter("chli_date"));
			System.out.print(chli_date);
			
			try{
				if (chli_reason == null || (chli_reason.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉事由");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/checklist/addChecklist.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			}catch(Exception e){
				errorMsgs.add("不可空白" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/checklist/addChecklist.jsp");
				failureView.forward(req, res);
			}
			
			ChecklistVO checklistVO = new ChecklistVO();
			checklistVO.setChli_cate(chli_cate);
			checklistVO.setChli_be_num(chli_be_num);
			checklistVO.setChli_memno(chli_memno);
			checklistVO.setChli_reason(chli_reason);
			checklistVO.setChli_date(chli_date);
			
			ChecklistService checklistSvc = new ChecklistService();
			checklistVO = checklistSvc.addChecklist(chli_cate,chli_be_num,chli_memno,chli_reason,chli_date);
			
			
			String url = "/checklist/closewindow.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("update".equals(action)){
			System.out.println("update");
			String aaa = req.getParameter("chli_start");
					System.out.print(aaa);
			Timestamp chli_start = Timestamp.valueOf(req.getParameter("chli_start"));
			Integer chli_day = Integer.valueOf(req.getParameter("chli_day"));						
			
			Long longEnd=chli_start.getTime()+chli_day*24*60*60*1000;
			java.sql.Date dateEnd = new java.sql.Date(longEnd);
			SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endString = end.format(dateEnd);
			Timestamp chli_end = Timestamp.valueOf(endString);
			
			
			Integer chli_pun = Integer.valueOf(req.getParameter("chli_pun"));
			String chli_sta = req.getParameter("chli_dta");
			if(chli_sta==null){
				chli_sta="F";
			}
			String chli_no = req.getParameter("chli_no");
			String chli_cate = req.getParameter("chli_cate");
			System.out.println("chli_cate="+chli_cate);
			
			ChecklistVO checklistVO = new ChecklistVO();
			checklistVO.setChli_start(chli_start);
			checklistVO.setChli_day(chli_day);
			checklistVO.setChli_end(chli_end);
			checklistVO.setChli_pun(chli_pun);
			checklistVO.setChli_sta(chli_sta);
			checklistVO.setChli_no(chli_no);
			
			ChecklistService checklistSvc = new ChecklistService();
			checklistSvc.updateChecklist(chli_start, chli_day, chli_end, chli_pun, chli_sta, chli_no);
			
			
			
			List<ChecklistVO> checklistVOList = null;
			if(chli_cate.equals("0")){
				checklistVOList = checklistSvc.getAllUndone_0();
			}else if(chli_cate.equals("1")){
				checklistVOList = checklistSvc.getAllUndone_1();
			}else if(chli_cate.equals("2")){
				checklistVOList = checklistSvc.getAllUndone_2();
			}else if(chli_cate.equals("3")){
				checklistVOList = checklistSvc.getAllUndone_3();
			}else{
				checklistVOList = checklistSvc.getAllUndone_4();
				
			}
			
		
			String chli_be_num = req.getParameter("chli_be_num");
			System.out.print(chli_be_num);
			String key = (String) chli_be_num.subSequence(0, 2);
			if(key.equals("AB")){
				checklistSvc.update_mem(checklistSvc.findABMem(chli_be_num));
				checklistSvc.deletePBArticle(chli_be_num);
			}else if(key.equals("AF")){
				checklistSvc.update_mem(checklistSvc.findAFMem(chli_be_num));
				checklistSvc.deleteArticle(chli_be_num);
			}else if(key.equals("PG")){
				checklistSvc.update_mem(checklistSvc.findPicnicA(chli_be_num));
				checklistSvc.blockadePicnic(chli_be_num);
			}else if(key.equals("MG")){
				checklistSvc.update_mem(chli_be_num);
			}else{//key.equals("GS")
				checklistSvc.update_goods_one(chli_be_num);
			}
			
			
			String undone = req.getParameter("undone");
			System.out.print("undone"+undone);
			req.setAttribute("testundone", undone);
			
			req.setAttribute("checklistVO", checklistVOList);
			
			req.setAttribute("testundone", chli_cate);
			String result = "undone";
			req.setAttribute("result", result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
		
		if("test_done".equals(action)){
			String str = req.getParameter("chli_cate");
			System.out.println(str);
			ChecklistService checklistSvc = new ChecklistService();
			List<ChecklistVO> checklistVO = null;
			
			String done = null;
			if(str.equals("0")){
				checklistVO = checklistSvc.getAllDone_0();
				done="0";
			}else if(str.equals("1")){
				checklistVO = checklistSvc.getAllDone_1();
				done="1";
			}else if(str.equals("2")){
				checklistVO = checklistSvc.getAllDone_2();
				done="2";
			}else if(str.equals("3")){
				checklistVO = checklistSvc.getAllDone_3();
				done="3";
			}else{
				checklistVO = checklistSvc.getAllDone_4();
				done="4";
			}
			
			String result = "done";
			
			req.setAttribute("testdone", done);
			req.setAttribute("result", result);
			req.setAttribute("checklistVO", checklistVO);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("test_undone".equals(action)){
			String str = req.getParameter("chli_cate");
			System.out.println(str);
			ChecklistService checklistSvc = new ChecklistService();
			List<ChecklistVO> checklistVO = null;
			
			String undone = null;
			if(str.equals("0")){
				checklistVO = checklistSvc.getAllUndone_0();
				undone = "0";
			}else if(str.equals("1")){
				checklistVO = checklistSvc.getAllUndone_1();
				undone = "1";
			}else if(str.equals("2")){
				checklistVO = checklistSvc.getAllUndone_2();
				undone = "2";
			}else if(str.equals("3")){
				checklistVO = checklistSvc.getAllUndone_3();
				undone = "3";
			}else{
				checklistVO = checklistSvc.getAllUndone_4();
				undone = "4";
			}
			
			String result = "undone";

			req.setAttribute("testundone", undone);
			req.setAttribute("result", result);
			req.setAttribute("checklistVO", checklistVO);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
