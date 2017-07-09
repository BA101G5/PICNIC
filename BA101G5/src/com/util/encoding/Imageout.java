package com.util.encoding;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

public class Imageout {
	public static String imageoutput(String contextpath, String table, String imgname, byte[] img) throws IOException {
		String url = String.format("%s\\%s\\%s.jpg", contextpath, table, imgname);
		String urlloc = String.format("/images/%s/%s.jpg", table, imgname);
		try {
			File file = new File(url);
			if (!file.getParentFile().exists()) {
				file.mkdirs();
			}
			FileOutputStream fileout = new FileOutputStream(file);
		//	BufferedOutputStream bos = new BufferedOutputStream(fileout);
				
			fileout.write(img);
			try {
			//	bos.close();
				fileout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return urlloc;
	}

	// public static void main(String[] args){
	// Imageout img=new Imageout();
	// String hello=Imageout.imageoutput("C:","table", "Hello",new byte[]{(byte)
	// 504708405});
	// System.out.println(hello);
	// }

}
