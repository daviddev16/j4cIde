package com.daviddev.j4cide.core;

import java.io.IOException;

import com.daviddev.j4cide.core.runner.RunnerSyncStarter;

/* TODO: IMPLEMENTAR DEPOIS: SEPARAR CONSTRUÇÃO E RUNNER */
public final class CoreWorkspaceExecutor {

	public static BuildEngine build(ApplicationContextManager contextManager) {
		String sourceFoldePath = contextManager.getSourceFolder().getAbsolutePath();
		BuildEngine buildEngine = new BuildEngine(sourceFoldePath);
		buildEngine.start();
		return buildEngine;
	}

	public static void syncAndRun(ApplicationContextManager contextManager, BuildEngine buildEngine) {
		String j4cRunner = contextManager.getApplicationRunnerPath();
		RunnerSyncStarter syncStarter = new RunnerSyncStarter(j4cRunner, buildEngine);
		syncStarter.start();
	}
	
	public static void compileAndRun(ApplicationContextManager contextManager) throws IOException {
		BuildEngine buildEngine = build(contextManager);
		syncAndRun(contextManager, buildEngine);
		/*String runnerPpath = contextManager.getApplicationRunnerPath();
		File sourceFolder = contextManager.getSourceFolder();
		BuildEngine buildEngine = new BuildEngine(sourceFolder.getCanonicalPath());
		buildEngine.start();
		RunnerSyncStarter syncStarter = new RunnerSyncStarter("C:\\Users\\David\\eclipse-workspace\\j4cIde\\CSharp.Runner"
				+ "\\J4cIde.Runner\\J4cIde.Runner\\bin\\Release\\J4cIde.Runner.exe", buildEngine);
		syncStarter.start();*/
	}
	
	/*public static void runApplication(ApplicationContextManager contextManager) throws IOException {
		File sourceFolder = contextManager.getSourceFolder();
		BuildEngine buildEngine = new BuildEngine(sourceFolder.getCanonicalPath());
		buildEngine.start();
		RunnerSyncStarter syncStarter = new RunnerSyncStarter("C:\\Users\\David\\eclipse-workspace\\j4cIde\\CSharp.Runner"
				+ "\\J4cIde.Runner\\J4cIde.Runner\\bin\\Release\\J4cIde.Runner.exe", buildEngine);
		syncStarter.start();
	}*/
	
}
