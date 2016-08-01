package com.hand.Exam2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		Socket socket = null;
		while(true){
			socket = server.accept();
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
		}
	}

}
