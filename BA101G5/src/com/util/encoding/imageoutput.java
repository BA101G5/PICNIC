package com.util.encoding;

import java.io.*;
import java.sql.Array;

public class imageoutput {

	public String imgoutput(String tablename, byte[] img, String name) {
		String loca = String.format("C:\\images\\%s\\%s.png", tablename, name);
		try {
			FileOutputStream fos = new FileOutputStream(new File(loca));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			fos.write(img);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tablename;

	}
	
	public static void main(String[] args){
		imageoutput ima =new imageoutput();
		ima.imgoutput("heLLO", new byte[]{(byte) 4056904}, "aa");
	}
}
