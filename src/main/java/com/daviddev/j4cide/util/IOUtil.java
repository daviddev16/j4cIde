package com.daviddev.j4cide.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class IOUtil {

	public static String read(String pathname) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(pathname)))) {
			StringBuilder appender = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				appender.append(line).append("\n");
			}
			return appender.toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void write(String pathname, String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathname)));
		writer.write(text);
		writer.flush();
		
	}
	
	
	

	public static String getExtension(File file) {
		String filePath = file.getAbsolutePath();
		return (file.isDirectory()) ? Extensions.DIR : 
			filePath.substring(filePath.lastIndexOf('.') + 1, filePath.length());
	}
}
