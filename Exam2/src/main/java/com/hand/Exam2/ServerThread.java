package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	private Socket socket;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			inputStream = new FileInputStream("../Exam1/target.pdf");
			bufferedInputStream = new BufferedInputStream(inputStream);
			outputStream = socket.getOutputStream();
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
