package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException{
		Socket socket = new Socket("127.0.0.1", 9999);
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			inputStream = socket.getInputStream();
			bufferedInputStream = new BufferedInputStream(inputStream);
			File file = new File("target.pdf");
			if( !file.exists() )
				file.createNewFile();
			outputStream = new FileOutputStream(file);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			byte[] buff = new byte[1024];
			int len = 0;
			while( (len = bufferedInputStream.read(buff)) > 0){
				bufferedOutputStream.write(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if( bufferedOutputStream != null )
					bufferedOutputStream.close();
				if( outputStream != null )
					outputStream.close();
				if( bufferedInputStream != null )
					bufferedInputStream.close();
				if( inputStream != null )
					inputStream.close();
				if( socket != null )
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
