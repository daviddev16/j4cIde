package com.daviddev.j4cide.core;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*TODO:REFACTOR*/
public final class Compiler {

	public static final File GCC = new File(".\\profile\\binaries\\mingw64\\bin\\gcc.exe");

	private final String projectFolder;


	public Compiler(String projectFolder) {
		this.projectFolder = projectFolder;
	}

	public void build(ApplicationContextManager contextManager, String name) {

		StringBuilder clangFiles = new StringBuilder();
		walkAndAppend(clangFiles);

		contextManager.getLogger().info("O projeto \"" + projectFolder + "\" será complidado em: \"" + name + "\".");
		contextManager.getLogger().info("Iniciando processo do GCC...");

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.directory(new File(projectFolder));
		processBuilder.redirectErrorStream(true);
		List<String> commands = new ArrayList<>();
		commands.add(GCC.getAbsolutePath());
		commands.add("-o");
		File build = new File(new File(".//profile//binaries//builds"), name);
		
		if(build.exists())
			build.delete();
		
		commands.add(build.getAbsolutePath());
		commands.add(clangFiles.toString().trim());
		processBuilder.command(commands);
		for (String string : commands) {
			System.out.print(string + " ");
		}
		try {
			Process process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				ApplicationContextManager.getContextManager().getLogger().error(line);
			}
			contextManager.getLogger().info("Compilação finalizada.");
			contextManager.getLogger().warn("O processo retornou o código " + process.exitValue() + ".");
			starter(contextManager, build);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void starter(ApplicationContextManager contextManager, File build) {
		contextManager.getLogger().info("Iniciando programa...");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String executable = build.getAbsolutePath();
					ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", executable);
					contextManager.getLogger().info("O programa iniciou, os logs serão exibidos no cmd.");
					Process process = processBuilder.start();
				}catch (Exception e) {
					contextManager.getLogger().error(e.getMessage());
				}
			}
		}).start();
	}

	/* TODO: implementar recursividade para sub-pastas depois */
	public void walk(StringBuilder files, File root) {
		for (File file : root.listFiles()) {
			if (file.isFile() && (file.getName().endsWith(".c") || 
					file.getName().endsWith(".h"))) {
				files.append(file.getName()).append(' ');
			}
		}
	}

	public void walkAndAppend(StringBuilder files) {
		walk(files, new File(projectFolder));
	}

	public String getProjectFolder() {
		return projectFolder;
	}

}
