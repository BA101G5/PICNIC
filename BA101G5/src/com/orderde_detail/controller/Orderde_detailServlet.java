package com.orderde_detail.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
			req.setCharacterEncoding("UTF-8");
			
	
	}
}
