package com.testcontrol.control;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.general_member.model.GeneralMemberDAO;
import com.general_member.model.GeneralMemberVO;
import com.manufacturers.model.ManufacturersDAO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;
import com.pboard_article.model.*;

@WebServlet("/test.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Test_Test extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		if(true){
		String para = null;
		Enumeration<String> e = req.getParameterNames();

		while(e.hasMoreElements()) {
			para = e.nextElement();
			if(para != null){
				System.out.println(para);
				System.out.println(req.getParameter(para));
				System.out.println("-----------------");
			}
		}
		}


        Pboard_ArticleService ppp = new Pboard_ArticleService();
        Pboard_ArticleVO dd= ppp.getOnePboard_Article("AB00000021");
        System.out.println(dd.getArticle_post());
        
        
        Timestamp times = new Timestamp(System.currentTimeMillis());
        System.out.println(times);
        
        //dd.setArticle_post(times);
        //ppp.addPboard_Article(dd);
        
		/*String saveDirectory = "/images_uploaded"; // 上傳檔案的目地目錄;
		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println("realpath : " +realPath);
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists())
			 fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄*/
		
		/*ManufacturersService mDAO = new ManufacturersService();
		ManufacturersVO mVO = mDAO.getOneManufacturers("MM00000001");*/
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy",Locale.ENGLISH);
		SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd");
		
		String stdate = "1993-01-06";
		
		GeneralMemberDAO dd = new GeneralMemberDAO();
		GeneralMemberVO dc = dd.findByPrimaryKey("MG00000009");
        
		Date k;
		try {
			k = str.parse(stdate);
			java.sql.Date sqlDate = new java.sql.Date(k.getTime());
			System.out.println(k);
			dc.setMEM_BIRTH(sqlDate); 
			System.out.println(sqlDate);
			dd.update(dc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		String savePath = "/images_uploaded";
		String realPath2 = getServletContext().getRealPath(savePath);
		File picDataResource = new File(realPath2);
		File f = null;
		Collection<Part> parts = req.getParts();;
		for (Part part : parts) {
				String filename = mVO.getMF_NO() + ".jpg";
				f = new File(picDataResource, filename);
				try {
					part.write(f.toString());
					System.out.println(part.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
				
		
		/*
		File f = null;
		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		for (Part part : parts) {
			if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
				String filename = getFileNameFromPart(part);
				f = new File(fsaveDirectory, filename);
				part.write(f.toString());
				System.out.println("**************************"+f.toString());
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

		 if(mVO != null){
			 mVO.setMF_LOGO(buffer.toByteArray());
			 mDAO.updateManufacturers(mVO);
			 System.out.println("end");
		 }else{
			 System.out.println("null");
		 }
		/*
		File file = new File(realPath, mVO.getMF_NO() + ".jpg");
		ByteArrayInputStream bis = new ByteArrayInputStream(mVO.getMF_LOGO());
		FileOutputStream fos = new FileOutputStream(file);

        byte[] buffer = new byte[4 * 1024];
        int len = 0;
        while ((len = bis.read(buffer)) != -1) {
        	fos.write(buffer, 0, len);
        }
		fos.close();
		System.out.println("end");
		 	*/
	}	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
