package com.testcontrol.control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.general_member.model.*;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;
//@WebServlet("/k_man_men_data.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Man_men_data extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
		String argu = req.getParameter("argu");
		System.out.println("---------------");
		try{
			argu = new String(argu.getBytes("ISO-8859-1"),"UTF-8");
		}catch(NullPointerException e){
			//System.out.println("argu is null");
		}
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
		if("cho_generalMember".equals(button)){  //會員權限維護頁
			GeneralMemberService meS = new GeneralMemberService();
			List<GeneralMemberVO> memList = meS.k_getAll();
			
			List<String> staList = new ArrayList<String>();
			List<String> pboList = new ArrayList<String>();
			for(GeneralMemberVO meVO : memList){
				staList.add(sta_convert(meVO.getMEM_STA()));
				pboList.add(pbo_convert(meVO.getMEM_PBOARD()));
			}
			
			req.setAttribute("pboList", pboList);
			req.setAttribute("staList", staList);
			req.setAttribute("memList", memList);
			String result = "";
			result = "cho_generalMember";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("detail".equals(button)){  //詳細資料
			if("MM".equals(argu.substring(0,2))){
				ManufacturersService maS = new ManufacturersService();
				ManufacturersVO ma_detail = maS.k_getOneManufacturers(argu);
	
				String sta = sta_convert(ma_detail.getMF_STA());
				String picPath = req.getContextPath()+getPicPath(ma_detail.getMF_LOGO(),ma_detail.getMF_NO());

				req.setAttribute("picPath", picPath);
				req.setAttribute("staList", sta);
				req.setAttribute("ma_detail", ma_detail);
				String result = "";
				result = "detail";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else if("MG".equals(argu.substring(0,2))){
				GeneralMemberService gmS =new GeneralMemberService();
				GeneralMemberVO gm_detail = gmS.k_getOneGeneralMember(argu);
				
				String sta = sta_convert(gm_detail.getMEM_STA());
				String pbo = pbo_convert(gm_detail.getMEM_PBOARD());
				String picPath = req.getContextPath()+getPicPath(gm_detail.getMEM_PIC(),gm_detail.getMEM_NO());
				req.setAttribute("picPath", picPath);

				req.setAttribute("pbo", pbo);
				req.setAttribute("staList", sta);
				req.setAttribute("gm_detail", gm_detail);
				String result = "";
				result = "detail";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		if("back".equals(button)){  //返回
			
			ManufacturersService maS = new ManufacturersService();
			List<ManufacturersVO> maList = maS.k_getAll();
			
			List<String> staList = new ArrayList<String>();
			for(ManufacturersVO maVO : maList){
				staList.add(sta_convert(maVO.getMF_STA()));
			}
			
			req.setAttribute("staList", staList);
			req.setAttribute("maList", maList);
			String result = "";
			result = "back";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("ma_verification".equals(button)){  //認證
			if("MM".equals(req.getParameter("MF_NO").substring(0,2))){
				ManufacturersService maS = new ManufacturersService();
				ManufacturersVO ma_detail = maS.k_getOneManufacturers(req.getParameter("MF_NO"));
				ma_detail.setMF_STA('A');
				maS.updateManufacturers(ma_detail);
				
				String sta = sta_convert(ma_detail.getMF_STA());
				String picPath = req.getContextPath()+getPicPath(ma_detail.getMF_LOGO(),ma_detail.getMF_NO());
	
				req.setAttribute("picPath", picPath);
				req.setAttribute("staList", sta);
				req.setAttribute("ma_detail", ma_detail);
				String result = "ma_verification";
				result = "MM";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else if("MG".equals(req.getParameter("MF_NO").substring(0,2))){
				GeneralMemberService gmS =new GeneralMemberService();
				GeneralMemberVO gm_detail = gmS.getOneGeneralMember(req.getParameter("MF_NO"));
				gm_detail.setMEM_STA('A');
				gmS.updateGeneralMember(gm_detail);
				
				String sta = sta_convert(gm_detail.getMEM_STA());
				String pbo = pbo_convert(gm_detail.getMEM_PBOARD());
				String picPath = req.getContextPath()+getPicPath(gm_detail.getMEM_PIC(),gm_detail.getMEM_NO());
				req.setAttribute("picPath", picPath);

				req.setAttribute("pbo", pbo);
				req.setAttribute("staList", sta);
				req.setAttribute("gm_detail", gm_detail);
				String result = "ma_verification";
				result = "MG";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		if("ma_suspension".equals(button)){  //停權
			if("MM".equals(req.getParameter("MF_NO").substring(0,2))){
				ManufacturersService maS = new ManufacturersService();
				ManufacturersVO ma_detail = maS.getOneManufacturers(req.getParameter("MF_NO"));
				ma_detail.setMF_STA('D');
				maS.updateManufacturers(ma_detail);
				
				
				String picPath = req.getContextPath()+getPicPath(ma_detail.getMF_LOGO(),ma_detail.getMF_NO());
				String sta = sta_convert(ma_detail.getMF_STA());
				
				req.setAttribute("picPath", picPath);
				req.setAttribute("staList", sta);
				req.setAttribute("ma_detail", ma_detail);
				String result = "";
				result = "ma_suspension";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);	
			}else if("MG".equals(req.getParameter("MF_NO").substring(0,2))){
				GeneralMemberService gmS =new GeneralMemberService();
				GeneralMemberVO gm_detail = gmS.getOneGeneralMember(req.getParameter("MF_NO"));
				gm_detail.setMEM_STA('D');
				gmS.updateGeneralMember(gm_detail);
				
				String sta = sta_convert(gm_detail.getMEM_STA());
				String pbo = pbo_convert(gm_detail.getMEM_PBOARD());
				String picPath = req.getContextPath()+getPicPath(gm_detail.getMEM_PIC(),gm_detail.getMEM_NO());
				req.setAttribute("picPath", picPath);

				req.setAttribute("pbo", pbo);
				req.setAttribute("staList", sta);
				req.setAttribute("gm_detail", gm_detail);
				String result = "";
				result = "ma_suspension";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if("ma_removed".equals(button)){  //移除
			if("MM".equals(req.getParameter("MF_NO").substring(0,2))){
				ManufacturersService maS = new ManufacturersService();
				maS.deleteManufacturers(req.getParameter("MF_NO"));
	
				List<ManufacturersVO> maList = maS.getAll();
				List<String> staList = new ArrayList<String>();
				for(ManufacturersVO maVO : maList){
					staList.add(sta_convert(maVO.getMF_STA()));
				}			
			
				req.setAttribute("staList", staList);
				req.setAttribute("maList", maList);
				String result = "";
				result = "ma_removed";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else if("MG".equals(req.getParameter("MF_NO").substring(0,2))){
				GeneralMemberService gmS =new GeneralMemberService();
				gmS.deleteGeneralMember(req.getParameter("MF_NO"));
				
				List<GeneralMemberVO> memList = gmS.getAll();
				
				List<String> staList = new ArrayList<String>();
				List<String> pboList = new ArrayList<String>();
				for(GeneralMemberVO meVO : memList){
					staList.add(sta_convert(meVO.getMEM_STA()));
					pboList.add(pbo_convert(meVO.getMEM_PBOARD()));
				}
				
				req.setAttribute("pboList", pboList);
				req.setAttribute("staList", staList);
				req.setAttribute("memList", memList);
				String result = "";
				result = "ma_removed";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}
		if("ma_mod".equals(button)){ //修改
			if("MM".equals(req.getParameter("MF_NO").substring(0,2))){
				ManufacturersService maS = new ManufacturersService();
				ManufacturersVO maVO = manSetAttribute(req);
				boolean flag = false;
				for(Part part : req.getParts()){
					if("logo".equals(part.getName()) && "image/jpeg".equals(part.getContentType())){
						flag = true;
					}
				}
				if(flag){
					maVO.setMF_LOGO((changePic(maVO.getMF_NO() ,req.getParts()).toByteArray()));
				}else{
					maVO.setMF_LOGO(noUpPic(maVO.getMF_NO()));
				}
				
				maS.updateManufacturers(maVO);
				String picPath = req.getContextPath()+getPicPath(maVO.getMF_LOGO(),maVO.getMF_NO());
				String sta = sta_convert(maVO.getMF_STA());
				
				req.setAttribute("picPath", picPath);
				req.setAttribute("staList", sta);
				req.setAttribute("ma_detail", maVO);
				String result = "";
				result = "ma_mod";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else if("MG".equals(req.getParameter("MF_NO").substring(0,2))){
				GeneralMemberService gmS =new GeneralMemberService();
				try {
					GeneralMemberVO gmVO = memSetAttribute(req);
					boolean flag = false;
					for(Part part : req.getParts()){
						if("logo".equals(part.getName()) && "image/jpeg".equals(part.getContentType())){
							flag = true;
						}
					}
					if(flag){
						gmVO.setMEM_PIC((changePic(gmVO.getMEM_NO() ,req.getParts()).toByteArray()));
					}else{
						gmVO.setMEM_PIC(noUpPic(gmVO.getMEM_NO()));
					}	
					
					gmS.updateGeneralMember(gmVO);
					String sta = sta_convert(gmVO.getMEM_STA());
					String pbo = pbo_convert(gmVO.getMEM_PBOARD());
					String picPath = req.getContextPath()+getPicPath(gmVO.getMEM_PIC(),gmVO.getMEM_NO());
					req.setAttribute("picPath", picPath);
					req.setAttribute("pbo", pbo);
					req.setAttribute("staList", sta);
					req.setAttribute("gm_detail", gmVO);
					String result = "";
					result = "ma_mod";
					req.setAttribute("result",result);
					String url = "/checklist/TEST_HOME.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	private String sta_convert(char tsta){	
		String sta = null;
		if(tsta == 'U'){
			sta = "未認證";
		}else if(tsta == 'A'){
			sta = "全認證";
		}else if(tsta == 'D'){
			sta = "停權";
		}else if(tsta == 'M'){
			sta = "手機認證";
		}else if(tsta == 'E'){
			sta = "信箱認證";
		}
		return sta;
	}
	
	private String pbo_convert(char tsta){	
		String sta = null;
		if(tsta == 'Y'){
			sta = "開啟";
		}else{
			sta = "關閉";
		}
		return sta;
	}
	
	private ManufacturersVO manSetAttribute(HttpServletRequest req){
		
		ManufacturersService maS = new ManufacturersService();
		ManufacturersVO maVO = maS.getOneManufacturers(req.getParameter("MF_NO"));
		
		String para = null;
		Enumeration<String> e = req.getParameterNames();

		while(e.hasMoreElements()) {
			para = e.nextElement();
			if(!"".equals(req.getParameter(para))){
				switch(para){
					case "MF_NAME":
						maVO.setMF_NAME(req.getParameter("MF_NAME"));
						break;
					case "MF_PHONE":
						maVO.setMF_PHONE(req.getParameter("MF_PHONE"));
						break;
					case "MF_MAIL":
						maVO.setMF_MAIL(req.getParameter("MF_MAIL"));
						break;
					case "MF_ACCO":
						maVO.setMF_ACCO(req.getParameter("MF_ACCO"));
						break;
					case "MF_PSW":
						maVO.setMF_PSW(req.getParameter("MF_PSW"));
						break;
					case "MF_SELF":
						maVO.setMF_SELF(req.getParameter("MF_SELF"));
						break;
					case "MF_BS":
						maVO.setMF_BS(req.getParameter("MF_BS"));
						break;
					case "MF_ADDR":
						maVO.setMF_ADDR(req.getParameter("MF_ADDR"));
						break;
					case "MF_FAX":
						maVO.setMF_FAX(req.getParameter("MF_FAX"));
						break;
					case "MF_REPORTNUM":
						maVO.setMF_REPORTNUM(Integer.parseInt(req.getParameter("MF_REPORTNUM")));
						break;
					default:
						break;
				}	
			}		
		}
		return maVO;
	}
	
	private GeneralMemberVO memSetAttribute(HttpServletRequest req) throws ParseException{

		GeneralMemberService gmS =new GeneralMemberService();
		GeneralMemberVO gmVO = gmS.getOneGeneralMember(req.getParameter("MF_NO"));

		String para = null;
		Enumeration<String> e = req.getParameterNames();
		
		while(e.hasMoreElements()) {
			para = e.nextElement();
			if(!"".equals(req.getParameter(para))){
				switch(para){
					case "MF_NAME":
						gmVO.setMEM_NAME(req.getParameter("MF_NAME"));
						break;
					case "MF_PHONE":
						gmVO.setMEM_PHONE(req.getParameter("MF_PHONE"));
						break;
					case "MF_MAIL":
						gmVO.setMEM_MAIL(req.getParameter("MF_MAIL"));
						break;
					case "MF_BIRTH":
						
						SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd");
						Date k;
						k = str.parse(req.getParameter("MF_BIRTH"));
						java.sql.Date sqlDate = new java.sql.Date(k.getTime());
						gmVO.setMEM_BIRTH(sqlDate);
						break;
					case "MF_PSW":
						gmVO.setMEM_PSW(req.getParameter("MF_PSW"));
						break;
					case "MF_COIN":
						gmVO.setMEM_COIN(Integer.parseInt(req.getParameter("MF_COIN")));
						break;
					case "MF_GEN":
						gmVO.setMEM_GEN(req.getParameter("MF_GEN").charAt(0));
						break;
					case "MF_ADDR":
						gmVO.setMEM_ADDR(req.getParameter("MF_ADDR"));
						break;
					case "MF_SELF":
						gmVO.setMEM_SELF(req.getParameter("MF_SELF"));
						break;
					case "pboardValue":
						gmVO.setMEM_PBOARD((req.getParameter("pboardValue").charAt(0)));
						break;
					default:
						break;
				}	
			}		
		}
		return gmVO;
	}
	private String getPicPath(byte[] pic , String no){
		
		String picPath = null;
		String savePath = "/images_uploaded";
		String realPath = getServletContext().getRealPath(savePath);
		
		File picDataResource = new File(realPath);
		if (!picDataResource.exists()){
			picDataResource.mkdirs();
		}
		
		if(pic != null){
			File picFile = new File(realPath, no + ".jpg");
			if(!picFile.exists()){
				ByteArrayInputStream bPic = new ByteArrayInputStream(pic);
				try {
					FileOutputStream fos = new FileOutputStream(picFile);
					
			        byte[] buffer = new byte[4 * 1024];
			        int len = 0;
			        while ((len = bPic.read(buffer)) != -1) {
			        	fos.write(buffer, 0, len);
			        }
					fos.close();
					bPic.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			picPath = savePath +"/"+ no + ".jpg";

		}else{
			picPath = savePath +"/NoResult.jpg";
		}	
		
		return picPath;
	}
	
	private ByteArrayOutputStream changePic(String no,Collection<Part> reqPart) throws IOException{
		
		String savePath = "/images_uploaded";
		String realPath = getServletContext().getRealPath(savePath);
		File picDataResource = new File(realPath);
		File f = null;
		Collection<Part> parts = reqPart;
		for (Part part : parts) {
			if (part.getContentType()!=null){
				String filename = no + ".jpg";
				f = new File(picDataResource, filename);
				try {
					part.write(f.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		InputStream fin = new FileInputStream(f);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = fin.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();
	    buffer.close();
		fin.close();
		return buffer;
	}
	
	private byte[] noUpPic(String no) throws IOException{
		
		String savePath = "/images_uploaded";
		String realPath = getServletContext().getRealPath(savePath);
		byte[] pic = null;
		File picFile = new File(realPath, no + ".jpg");
		if(picFile.exists()){
			InputStream fin = new FileInputStream(picFile);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = fin.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			buffer.flush();
			buffer.close();
			fin.close();
			pic = buffer.toByteArray();
		}
		
		return pic;
	}	
}
