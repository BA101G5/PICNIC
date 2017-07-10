package com.util.encoding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Imageout {
	public static String imageoutput(String contextpath, String table, String imgname, byte[] img) {
		String url = String.format("%s\\%s\\%s.jpg", contextpath, table, imgname);
		String urlloc = String.format("/images/%s/%s.jpg", table, imgname);
		File file = new File(url);
		FileOutputStream fileout = null;
		try {
			fileout = new FileOutputStream(file);
			if (!file.getParentFile().exists()) {
				file.mkdirs();
			}
			fileout.write(img);

		} catch (FileNotFoundException e2) {

			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileout.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
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
