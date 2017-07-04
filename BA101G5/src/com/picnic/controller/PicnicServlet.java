package com.picnic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.picnic.model.PicnicService;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.LinkedHashMap;

public class PicnicServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		if("insert".equals(action)){
			Map<String,String> errorMsgs =new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{String picnic_name=req.getParameter("name");
				String nameReg ="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(picnic_name==null||picnic_name.trim().length()==0){
				errorMsgs.put("name","���\�ΦW�Ъ��ť�");
			}else if(!picnic_name.trim().matches(nameReg)){
				errorMsgs.put("name", " �ΦW�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			
			String address = req.getParameter("tladdress");
			String addressReg ="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
			if(address==null||address.trim().length()==0){
				errorMsgs.put("address","���\�ΦW�Ъ��ť�");
			}else if(!address.trim().matches(nameReg)){
				errorMsgs.put("address", " �a�}�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
			}
			
			java.sql.Date picnic_date =null;
				try{
				picnic_date= java.sql.Date.valueOf(req.getParameter("date"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("date", "�п�J���");
				}
				
			Integer picnic_pl=new Integer(req.getParameter("people"));	
			
			/*************************** 2.�}�l�s�W��� ***************************************/
			if(action.equals("insert")){
			PicnicService picnicSvc =new PicnicService();
			picnicSvc.addPicnic(picnic_name, picnic_date, picnic_pl);
			}
			
			} catch(Exception e) {errorMsgs.put("Exception", e.getMessage());
			
			
			}
			
		}
	}
}
