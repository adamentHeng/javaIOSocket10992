package com.hand.Exam1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
		URLConnection con = url.openConnection();
		InputStream inputStream = con.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		File file = new File("target.pdf");
		if( !file.exists() )
			file.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		byte[] buff = new byte[1024];
		int len = 0;
		while( (len = bufferedInputStream.read(buff)) > 0){
			bufferedOutputStream.write(buff, 0, len);
		}
		bufferedInputStream.close();
		inputStream.close();
		bufferedOutputStream.close();
		fileOutputStream.close();
	}

}
