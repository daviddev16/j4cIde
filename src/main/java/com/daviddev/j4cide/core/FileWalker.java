package com.daviddev.j4cide.core;

import java.io.File;
import java.util.function.Consumer;

import com.daviddev.j4cide.util.IOUtil;

import static com.daviddev.j4cide.util.Validator.*;

public final class FileWalker {

	public static void appendFile(StringBuilder stringBuilder, 
			File rootFolder, boolean quote, String[] extensions) {
		walk((walkedFile) -> {
			String fileName = walkedFile.getAbsolutePath();
			String quotedFileName = (quote) ? IOUtil.quotePath(fileName) : fileName;
			stringBuilder.append(' ').append(quotedFileName);
		}, rootFolder, quote, extensions);
	}
	
	public static void walk(Consumer<File> fileConsumer, 
			File rootFolder, boolean quote, String[] extensions) {
		checkIsNotNull(rootFolder, "rootFolder");
		checkIsNotNull(fileConsumer, "fileConsumer");
		assertThat("extensions.length != 0", extensions.length != 0);
		assertThat("rootFolder.isDirectory()", rootFolder.isDirectory());
		for (File subFile : rootFolder.listFiles()) {
			if (subFile.isDirectory()) {
				walk(fileConsumer, subFile, quote, extensions);
				continue;
			}
			if (hasExtension(subFile, extensions)) {
				fileConsumer.accept(subFile);
			}
		}
	}

	public static boolean hasExtension(File file, String[] extensions) {
		for (String extension : extensions) {
			if (file.getName().endsWith(extension))
				return true;
		}
		return false;
	}

}
