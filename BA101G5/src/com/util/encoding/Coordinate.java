package com.util.encoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONObject;

public class Coordinate {
	public static JSONObject getCoordinate(String addr) throws Exception, IOException {
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String url = String.format(
				"https://maps.googleapis.com/maps/api/geocode/json?address=%s+tw&key=AIzaSyBjNJIxojHyNuTsveU2JJZyq4VdTIB4TZk",
				address);
		URL adrurl = null;
		URLConnection httpcon = null;
		try {
			adrurl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			httpcon = (URLConnection) adrurl.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject adr =null;
		if (httpcon != null) {
			InputStreamReader insr = new InputStreamReader(httpcon.getInputStream());
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(insr);
			String data = null;
			
			while((data = br.readLine()) != null) {
				sb.append(data);
			}
		
		
			 adr =JSONObject.fromObject(sb.toString());
			 
			insr.close();
		}
		return adr;
	}

	public static void main(String[] args) {
		Coordinate pl = new Coordinate();
		try {
			pl.getCoordinate("新北市政府");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
