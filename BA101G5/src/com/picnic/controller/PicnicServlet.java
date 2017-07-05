package com.picnic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession	session=req.getSession();
		String action = req.getParameter("action");
		
		if(action.equals("insert")){
			Map<String,String> errorMsgs =new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try{String picnic_name=(String) session.getAttribute("name");
				String nameReg ="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
			if(picnic_name==null||picnic_name.trim().length()==0){
				errorMsgs.put("name","嚙踝蕭嚙踝蕭峖W嚙請迎蕭嚙褐伐蕭");
			}else if(!picnic_name.trim().matches(nameReg)){
				errorMsgs.put("name", " 嚙諄名嚙線嚙踝蕭O嚙踝蕭嚙畿嚙稷嚙踝蕭r嚙踝蕭嚙畿嚙複字嚙瞎_ , 嚙畿嚙踝蕭嚙論伐蕭嚙豎在2嚙踝蕭10嚙踝蕭嚙踝蕭");
			}
		
			String address =(String)session.getAttribute("tladdress");
			String addressReg ="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
			if(address==null||address.trim().length()==0){
				errorMsgs.put("address","嚙踝蕭嚙踝蕭峖W嚙請迎蕭嚙褐伐蕭");
			}else if(!address.trim().matches(nameReg)){
				errorMsgs.put("address", " 嚙窮嚙罷嚙線嚙踝蕭O嚙踝蕭嚙畿嚙稷嚙踝蕭r嚙踝蕭嚙畿嚙複字嚙瞎_ , 嚙畿嚙踝蕭嚙論伐蕭嚙豎在2嚙踝蕭15嚙踝蕭嚙踝蕭");
			}
	
			java.sql.Timestamp picnic_date =null;
				try{
				picnic_date= java.sql.Timestamp.valueOf((String) session.getAttribute("date"));
				System.out.println(picnic_date+"aoeu");
				} catch (IllegalArgumentException e) {
					errorMsgs.put("date", "嚙請選蕭J嚙踝蕭嚙�");
				}
				Integer picnic_pl= new Integer(((String)session.getAttribute("people")).trim());	
			if(action.equals("insert")){
			PicnicService picnicSvc =new PicnicService();
			picnicSvc.addPicnic(picnic_name, picnic_date, picnic_pl);
			}
			
			} catch(Exception e) {errorMsgs.put("Exception", e.getMessage());
			
			
			}
			
		}
	}
}
