package com.daviddev.j4cide.core.runner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.BuildEngine;
import com.daviddev.j4cide.core.ExecutionWorker;

public final class RunnerSyncStarter extends ExecutionWorker {

	public static final AtomicInteger ID = new AtomicInteger();

	public static AtomicReference<RunnerSyncStarter> syncStarterReference = new AtomicReference<>();

	private String runnerFilePath;
	private BuildEngine buildEngine;
	private RunnerTcpClient tcpClient;

	public RunnerSyncStarter(String runnerFilePath, BuildEngine buildEngine) {
		super("RunnerSyncStarter-" + ID.getAndIncrement(), false, false);
		this.runnerFilePath = runnerFilePath;
		this.buildEngine = buildEngine;
		syncStarterReference.set(this);
	}

	@Override
	protected void onBegin() {
		try {
			synchronized (BuildEngine.LOCK) {
				BuildEngine.LOCK.wait();
				if (buildEngine.wasSuccessfullyBuilt()) {
					Desktop.getDesktop().open(new File(runnerFilePath));
					Thread.sleep(1000L);
					tcpClient = new RunnerTcpClient(InetAddress.getLocalHost());
					tcpClient.sendBuildFile(buildEngine.getBuildFile());
					tcpClient.collectServerData();
				} else {
					ApplicationContextManager.getContextManager().getLogger()
							.warn("Houve um erro de compilação. O Application Runner não será iniciado.");
				}
			}
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

}
