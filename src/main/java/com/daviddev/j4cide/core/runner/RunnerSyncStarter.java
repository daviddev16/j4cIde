package com.daviddev.j4cide.core.runner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.BuildEngine;
import com.daviddev.j4cide.core.ExecutionWorker;
import com.daviddev.j4cide.core.logger.LogLevel;

/* classe responsável por controlar o AppRunner */
public final class RunnerSyncStarter extends ExecutionWorker {

	private final ApplicationContextManager contextManager = ApplicationContextManager.getContextManager();

	public static final String CLOSE_SIGNAL_MESSAGE = "CLOSE_SIGNAL";

	public static final int MAX_BUFFER_SIZE = 1024*4;
	public static final int RUNNER_SERVER_PORT = 10799;

	private final InetAddress inetAddress;
	private final File applicationRunnerFile;
	private final File buildFile;

	private Socket clientSocket;

	public RunnerSyncStarter(File applicationRunnerFile, File buildFile, InetAddress inetAddress) {
		super("RunnerSyncStarter-" + ID.getAndIncrement(), false, false);
		this.applicationRunnerFile = applicationRunnerFile;
		this.buildFile = buildFile;
		this.inetAddress = inetAddress;
	}


	@Override
	protected void onBegin() {
		try {

			Desktop.getDesktop().open(applicationRunnerFile);

			clientSocket = new Socket(inetAddress, RUNNER_SERVER_PORT);

			sendApplicationFile(buildFile);

			while(!clientSocket.isClosed()) {
				String received = read();
				if (received.contains(CLOSE_SIGNAL_MESSAGE) || received.isEmpty()) {
					closeQuietly();
					break;
				}
				synchronized (RunnerSyncStarter.class) {
					ApplicationContextManager.getContextManager()
					.getLogger().log(LogLevel.SYNC, received);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ApplicationContextManager.getContextManager().getLogger()
			.error(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}

	@Override
	protected void onStop() {
	}

	public void sendApplicationFile(File buildFile) throws Exception {
		try {
			send( buildFile.getAbsolutePath() );
		} catch (SocketException e) {
			closeQuietly();
		}
	}

	public void sendCloseSignal() throws Exception {
		try {
			send( CLOSE_SIGNAL_MESSAGE );
		} catch (SocketException e) {
			closeQuietly();
		}
	}

	private String read() throws Exception {
		try {
			byte[] buffer = new byte[MAX_BUFFER_SIZE];
			int bytesRead = getInputStream().read(buffer);
			String response = new String(buffer, 0, bytesRead);
			return response;
		} catch(SocketException ignore) {}
		return "";
	}

	private void send(String message) throws Exception {
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		getOutputStream().write(data);
		getOutputStream().flush();
	}

	private void closeQuietly() {
		try {
			getInputStream().close();
			getOutputStream().flush();
			getOutputStream().close();
		} catch (Exception e) {/* close quietly */}
	}

	public InputStream getInputStream() 
			throws IOException {
		return getClientSocket().getInputStream();
	}

	public OutputStream getOutputStream() 
			throws IOException {
		return getClientSocket().getOutputStream();
	}

	public InetAddress getInetAddress() {
		return inetAddress;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public File getBuildFile() {
		return buildFile;
	}

}
