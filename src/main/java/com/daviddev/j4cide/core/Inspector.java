package com.daviddev.j4cide.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Inspector {

	public static void sendInspection(ApplicationContextManager contextManager) {
		
		/*if (!contextManager.getInspectionUI().isVisible())
			return;
		new Thread(new Runnable() {
			@Override
			public void run() {
				File sourceFolder = contextManager.getSourceFolder();
				String filesArgument = createFilesArgument(sourceFolder);
				try {
					Process process = ProcessManager.createCompilerProcess(sourceFolder, contextManager.getCompilerPath(), 
							"-fsyntax-only", null, filesArgument);
					handleInputStream(contextManager, process.getInputStream());
					process.waitFor();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();*/
	}

	public static String createFilesArgument(File sourceFolder) {
		StringBuilder builder = new StringBuilder();
		FileWalker.appendFile(builder, sourceFolder, true, Constants.EXTENSIONS);
		return builder.toString().trim();
	}

	public static void handleInputStream(ApplicationContextManager contextManager, 
			InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		/* melhorar depois */
		StringBuilder content = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			content.append(line);
			contextManager.getCodeScene().getInspectionPane().appendText(line);
		}
		if(content.toString().trim().isEmpty()) {
			contextManager.getCodeScene().getInspectionPane().ok();
		}

		reader.close();
	}

}
