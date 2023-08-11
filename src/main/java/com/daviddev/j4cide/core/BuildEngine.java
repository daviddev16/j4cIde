package com.daviddev.j4cide.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.daviddev.j4cide.util.Directories;

/**
 * Essa classe é responsável por criar o arquivo executável dos arquivos C e
 * dizer para o RunnerSyncStarter começar a receber informação.
 **/
public final class BuildEngine extends ExecutionWorker {

	private static volatile BuildEngine CURRENT_ENGINE;
	public static final Object LOCK = new Object();
	public static final int SUCCESS = 0;

	private final ApplicationContextManager contextManager = ApplicationContextManager.getContextManager();
	private final StringBuilder compilerLogs = new StringBuilder();
	
	private volatile File buildFile;
	private boolean success = false;
	private File sourceFolder;
	
	public BuildEngine(String sourceFolder) {
		super("BuildEngineWorker-" + ID.getAndIncrement(), false, false);
		if (CURRENT_ENGINE != null) {
			contextManager.getLogger().error("Não é possível fazer isso no momento, aguarde.");
			markAsDisabled();
			return;
		}
		this.sourceFolder = new File(sourceFolder);
	}

	@Override
	protected void onBegin() {
		try {
			contextManager.getLogger().clear();
			String buildName = newUniqueName();
			build(buildName);
		} catch (Exception e) {
			contextManager.getLogger().error("Houve um problema na compilação do projeto.");
			contextManager.getLogger().error(e.getClass().getSimpleName() + ": " + e.getMessage());
			success = false;
			e.printStackTrace();
		}
	}
	
	private void build(String buildName) throws IOException, InterruptedException {
		
		String compilerPath = contextManager.getCompilerPath();
		buildFile = new File(Directories.BUILDS, buildName);
		
		contextManager.getLogger().info("Iniciando construção do programa.");
		clearBuildGarbage();
		
		Process process = ProcessManager.createCompilerProcess(sourceFolder, 
				compilerPath, "", buildFile, createFilesArgument());
		
		handleInputStream(process.getInputStream());
		int exitCodeValue = process.waitFor();

		if (isDoneSuccessfully(exitCodeValue)) {
			contextManager.getLogger().info("O projeto \"" + sourceFolder.getName() + 
					"\" foi compilado em \"" + buildName + "\"");
			success = true;
		}
		else {
			contextManager.getLogger().warn("Houve um problema na hora de"
					+ " compilar o projeto, verifique:");
			for (String line : getCompilerLogs().toString().split("\n")) {
				contextManager.getLogger().error(line);
			}
			success = false;
		}
		stop();
	}
	
	@Override
	protected void onStop() {
		synchronized (LOCK) {
            LOCK.notify();
        }
	}
	
	private void handleInputStream(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			getCompilerLogs().append(line).append('\n');
		}
	}

	private String newUniqueName() {
		Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String fixedFolderName = fixedFileName();
        return fixedFolderName + "_" + formato.format(dataAtual) + ".exe";
	}
	
	private String createFilesArgument() {
		StringBuilder builder = new StringBuilder();
		FileWalker.appendFile(builder, sourceFolder, true, Constants.EXTENSIONS);
		return builder.toString().trim();
	}

	private void clearBuildGarbage() {
		for (File buildFiles : new File(Directories.BUILDS).listFiles())
			buildFiles.delete();
	}
	
	private boolean isDoneSuccessfully(int exitCode) {
		return (exitCode == SUCCESS);
	}
	
	public String fixedFileName() {
		return sourceFolder.getName()
				.replaceAll("[^a-zA-Z0-9-_\\. ]", "_")
				.replaceAll("\\s+", "_")
				.toLowerCase();
	}
	
	public static boolean isEngineBusy() {
		return CURRENT_ENGINE != null && CURRENT_ENGINE.isRunning();
	}
	
	public static BuildEngine getBusyBuildEngine() {
		return CURRENT_ENGINE;
	}
	
	public StringBuilder getCompilerLogs() {
		return compilerLogs;
	}

	public boolean wasSuccessfullyBuilt() {
		return success;
	}
	
	public File getSourceFolder() {
		return sourceFolder;
	}
	
	public File getBuildFile() {
		return buildFile;
	}
	
}
