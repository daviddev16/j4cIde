package com.daviddev.j4cide.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.daviddev.j4cide.util.Directories;

/**
 * Essa classe é responsável por criar o arquivo executável dos arquivos C e
 * dizer para o RunnerSyncStarter começar a receber informação.
 **/
public final class BuildEngine extends ExecutionWorker {

	public static final AtomicInteger ID = new AtomicInteger();
	public static final Object LOCK = new Object();

	public static final int SUCCESS = 0;
	
	private final ApplicationContextManager contextManager = ApplicationContextManager.getContextManager();

	private volatile File buildFile;
	
	private boolean success = false;
	private final StringBuilder compilerLogs;
	private final File sourceFolder;
	
	public BuildEngine(String sourceFolder) {
		super("BuildEngineWorker-" + ID.getAndIncrement(), false, false);
		this.compilerLogs = new StringBuilder();
		this.sourceFolder = new File(sourceFolder);
	}

	@Override
	protected void onBegin() {
		try {
			contextManager.getLogger().clear();
			build(createUniqueName());
		} catch (Exception e) {
			contextManager.getLogger().error("Houve um problema na compilação do projeto.");
			contextManager.getLogger().error(e.getClass().getSimpleName() + ": " + e.getMessage());
			success = false;
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStop() {
		synchronized (LOCK) {
            LOCK.notify();
        }
	}
	
	private void build(String buildName) throws IOException, InterruptedException {

		String compilerPath = contextManager.getCompilerPath();

		contextManager.getLogger().space(1);
		contextManager.getLogger().info("=|===================================|=");
		contextManager.getLogger().info("=| INICIANDO PROCESSO DE COMPILAÇÃO  |=");
		contextManager.getLogger().info("=|===================================|=");
		
		clearGarbage(contextManager);
		Process process = createProcess(compilerPath, buildName);
		handleInputStream(process.getInputStream());
		int exitCodeValue = process.waitFor();

		if (wasSuccessfullyCompiled(exitCodeValue)) {
			contextManager.getLogger().info("O projeto \"" + sourceFolder.getName() + 
					"\" foi compilado em \"" + buildName + "\"");
			success = true;
		}
		else {
			contextManager.getLogger().warn("Houve um problema na hora de"
					+ " compilar o projeto, verifique:");
			showAllProblems();
			success = false;
		}
		contextManager.getLogger().info("Processo de compilação finalizado.");
		stop();
	
	}
	
	private void handleInputStream(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			getCompilerLogs().append(line).append('\n');
		}
	}
	
	private Process createProcess(String compilerPath, 
								  String buildName) throws IOException {
		String allCFiles = createStringOfAllCFiles();
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.directory(sourceFolder);
		processBuilder.redirectErrorStream(true);
		List<String> commands = new ArrayList<>();
		commands.add(quotePath(compilerPath));
		commands.add("-o");
		buildFile = new File(Directories.BUILDS, buildName);
		commands.add(quotePath(buildFile.getAbsolutePath()));
		commands.add(allCFiles.trim());		
		processBuilder.command(commands);
		Process process = processBuilder.start();
		return process;
	}
	
	private void showAllProblems() {
		for (String line : getCompilerLogs().toString().split("\n")) {
			contextManager.getLogger().error(line);
		}
	}
	
	private void clearGarbage(ApplicationContextManager contextManager) {
		contextManager.getLogger().info("Limpando arquivos de build antigos");
		for (File buildFiles : new File(Directories.BUILDS).listFiles()) {
			buildFiles.delete();
		}
	}
	
	private boolean wasSuccessfullyCompiled(int exitCode) {
		return (exitCode == SUCCESS);
	}

	/* TODO:MELHORAR: implementar recursividade para sub-pastas depois */
	private void walk(StringBuilder files, File root) {
		for (File file : root.listFiles()) {
			if (file.isFile() && (file.getName().endsWith(".c") || 
					file.getName().endsWith(".h"))) {
				files.append(quotePath(file.getName())).append(' ');
			}
		}
	}

	private String createUniqueName() {
		Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String fixedFolderName = fixedFileName();
        return fixedFolderName + "_" + formato.format(dataAtual) + ".exe";
	}
	
	private String createStringOfAllCFiles() {
		StringBuilder builder = new StringBuilder();
		walk(builder, sourceFolder);
		builder.trimToSize();
		return builder.toString();
	}

	private String quotePath(String path) {
		return "\"".concat(path).concat("\"");
	}
	
	public String fixedFileName() {
		return sourceFolder.getName()
				.replaceAll("[^a-zA-Z0-9-_\\. ]", "_")
				.toLowerCase();
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
