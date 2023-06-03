package com.daviddev.j4cide.core.runner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.logger.LogLevel;

public class RunnerTcpClient {

	public static final String CLOSE_SOCKET_MESSAGE = ":ClOSE_SOCKET:";

	public static final int MAX_BUFFER_SIZE = 1024*4;
	public static final int RUNNER_SERVER_PORT = 10799;

	private final Socket clientSocket;
	private final OutputStream outputStream; 
	private final InputStream inputStream;

	public RunnerTcpClient(InetAddress inetAddress) throws IOException {
		clientSocket = new Socket(inetAddress, RUNNER_SERVER_PORT);
		outputStream = clientSocket.getOutputStream();
		inputStream = clientSocket.getInputStream();
	}

	public void sendBuildFile(File buildFile) throws IOException {
		ApplicationContextManager.getContextManager()
		.getLogger().info("Sincronizando com Application Runner");
		write(buildFile.getCanonicalPath());
	}

	public void collectServerData() throws IOException {
		while(!clientSocket.isClosed()) {
			String received = read();
			if (received.contains(CLOSE_SOCKET_MESSAGE) || received.isEmpty()) {
				closeQuietly();
				break;
			}
			synchronized (RunnerTcpClient.class) {
				ApplicationContextManager.getContextManager()
				.getLogger().log(LogLevel.SYNC, received);
			}
		}
	}

	private void write(String message) throws IOException {
		try {
			byte[] data = message.getBytes(StandardCharsets.UTF_8);
			getOutputStream().write(data);
			getOutputStream().flush();
		} catch (SocketException e) {
			closeQuietly();
		}
	}

	private String read() throws IOException {
		try {
			byte[] buffer = new byte[MAX_BUFFER_SIZE];
			int bytesRead = getInputStream().read(buffer);
			String response = new String(buffer, 0, bytesRead);
			return response;
		} catch(SocketException e) {
			closeQuietly();
		}
		return "";
	}
	
	public void closeQuietly() {
		try {
			inputStream.close();
			outputStream.close();
			clientSocket.close();
		} catch (Exception e) {/* ignore */}
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

}
