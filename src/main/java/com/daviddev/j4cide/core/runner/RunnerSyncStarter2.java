package com.daviddev.j4cide.core.runner;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.ExecutionWorker;

public final class RunnerSyncStarter2 extends ExecutionWorker {

	public static final AtomicInteger ID = new AtomicInteger();

	public static final String PROCESS_NAME = "J4cIde.Runner.exe";

	private RunnerTcpClient tcpClient;
	private String runnerFilePath;
	private final File buildFile;
	
	private int applicationPid;

	public void handleRunner(File j4cRunner) {
		try {
			Desktop.getDesktop().open(j4cRunner);
			applicationPid = getRunningSystemPid();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RunnerSyncStarter2(String runnerFilePath, File buildFile) {
		super("RunnerSyncStarter-" + ID.getAndIncrement(), false, false);
		this.runnerFilePath = runnerFilePath;
		this.buildFile = buildFile;
	}

	public static synchronized int getRunningSystemPid() {
		try {
			Process process = new ProcessBuilder("tasklist").start();
			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line = reader.readLine()) != null) {
				if (line.contains(PROCESS_NAME)) {
					String pid = line.split("\\s+")[1];
					process.destroyForcibly();
					return Integer.parseInt(pid);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@Override
	protected void onBegin() {
		try {
			Desktop.getDesktop().open(new File(runnerFilePath));
			Thread.sleep(1000L);
			tcpClient = new RunnerTcpClient(InetAddress.getLocalHost());
			tcpClient.sendBuildFile(buildFile);
			tcpClient.collectServerData();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			ApplicationContextManager.getContextManager().getLogger()
			.error(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}

	@Override
	protected void onStop() {
		if (tcpClient != null) {
			tcpClient.closeQuietly();
		}
	}

	public RunnerTcpClient getTCPClient() {
		return tcpClient;
	}

	public File getBuildFile() {
		return buildFile;
	}

}
