package com.daviddev.j4cide.core.runner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.daviddev.j4cide.core.ApplicationContextManager;

public class J4CTCPClient {

	private Socket clientSocket;
	
	public J4CTCPClient() {}
	
	public void start(String buildPath) {
		try {
			clientSocket = new Socket("127.0.0.1", 10799);
			OutputStream outputStream = clientSocket.getOutputStream();
			InputStream inputStream = clientSocket.getInputStream();
			write(outputStream, buildPath);
			
			while(clientSocket.isConnected()) {
				String received = receive(inputStream);
				
				if (received.equals(":ClOSE"))
					break;
				
				ApplicationContextManager.getContextManager()
					.getLogger().info("Runner Message: " + received);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String receive(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024*4];
		int bytesRead = inputStream.read(buffer);
		String response = new String(buffer, 0, bytesRead);
		return response;
	}
	
	private void write(OutputStream outputStream, String message) throws IOException {
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		outputStream.write(data);
		outputStream.flush();
	}
	
	
	
}
