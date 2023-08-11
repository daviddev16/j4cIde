package com.daviddev.j4cide.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.daviddev.j4cide.util.IOUtil;

public final class ProcessManager {

	public static Process createCompilerProcess(File sourceFolder, String compilerPath, String extraArgument, 
			File buildFile, String filesArgument) throws IOException {

		File compilerFile = new File(compilerPath);
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.directory(compilerFile.getParentFile()
				.getCanonicalFile());
		processBuilder.redirectErrorStream(true);
		List<String> commands = new ArrayList<>();
		commands.add(compilerFile.getCanonicalPath());
		if (buildFile != null) {
			commands.add("-o");
			commands.add(IOUtil.quotePath(buildFile.getCanonicalPath()));
		}
		if (extraArgument != null && !extraArgument.isEmpty()) {
			commands.add(extraArgument);
		}
		//commands.add("-c");
		commands.add(filesArgument);		
		processBuilder.command(commands);
		commands.forEach(s -> System.out.print(s + " "));
		
		Process process = processBuilder.start();
		return process;
	}

}
