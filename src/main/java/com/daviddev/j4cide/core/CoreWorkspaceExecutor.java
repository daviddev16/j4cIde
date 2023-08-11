package com.daviddev.j4cide.core;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.daviddev.j4cide.core.runner.RunnerSyncStarter;

/* TODO: IMPLEMENTAR DEPOIS: SEPARAR CONSTRUÇÃO E RUNNER */
public final class CoreWorkspaceExecutor {

	private final ApplicationContextManager contextManager = ApplicationContextManager.getContextManager();
	
	public static BuildEngine build(ApplicationContextManager contextManager) {
		String sourceFoldePath = contextManager.getSourceFolder().getAbsolutePath();
		BuildEngine buildEngine = new BuildEngine(sourceFoldePath);
		buildEngine.start();
		return buildEngine;
	}

	public static void syncAndRun(ApplicationContextManager contextManager, BuildEngine buildEngine) throws UnknownHostException {
		String j4cRunner = contextManager.getApplicationRunnerPath();
		RunnerSyncStarter syncStarter = new RunnerSyncStarter(new File(j4cRunner), 
				buildEngine.getBuildFile(), InetAddress.getLocalHost());
		syncStarter.start();
	}
	
	
	public static void compileAndRun(ApplicationContextManager contextManager) throws IOException {
		BuildEngine buildEngine = build(contextManager);
		synchronized (BuildEngine.LOCK) {
			try {
				BuildEngine.LOCK.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			syncAndRun(contextManager, buildEngine);
		}
	}

}
