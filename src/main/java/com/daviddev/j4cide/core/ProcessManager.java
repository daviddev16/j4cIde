package com.daviddev.j4cide.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.daviddev.j4cide.util.IOUtil;

public final class ProcessManager {

	public static Process createCompilerProcess(File sourceFolder, String compilerPath, String extraArgument, 
			File buildFile, String filesArgument) throws IOException {

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.directory(sourceFolder);
		processBuilder.redirectErrorStream(true);
		List<String> commands = new ArrayList<>();
		commands.add(IOUtil.quotePath(compilerPath));
		if (buildFile != null) {
			commands.add("-o");
			commands.add(IOUtil.quotePath(buildFile.getCanonicalPath()));
		}
		if (extraArgument != null && !extraArgument.isEmpty()) {
			commands.add(extraArgument);
		}
		commands.add(filesArgument);		
		processBuilder.command(commands);

		Process process = processBuilder.start();
		return process;
	}

}
