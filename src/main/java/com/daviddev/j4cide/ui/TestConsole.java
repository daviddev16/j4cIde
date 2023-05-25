package com.daviddev.j4cide.ui;

import java.io.IOException;

public class TestConsole {


	public static void main(String[] args) throws IOException {

		/*ProcessBuilder processBuilder = new ProcessBuilder("cmd","start","C:\\Users\\David\\Desktop\\mingw64\\a.exe");
		processBuilder.redirectErrorStream(true);
		Process processo = processBuilder.start();
		 */
		//try {
			String pre = "@echo on\n";
			String executable = "C:\\Users\\David\\Desktop\\mingw64\\a.exe";
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", executable);
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			
/*			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			process.destroy(); // Clean up the process
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}

}
