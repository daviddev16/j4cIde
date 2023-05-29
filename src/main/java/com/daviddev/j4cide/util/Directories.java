package com.daviddev.j4cide.util;

import java.io.File;

public final class Directories {
	
	public static final String J4CIDE_RUNNER = "./CSharp.Runner/J4cIde.Runner/J4cIde.Runner/";

	public static final String PROFILE = "./profile/";
	public static final String ICONS = PROFILE.concat("icons/");
	
	public static final String BINARIES = PROFILE.concat("binaries/");
	public static final String BUILDS = BINARIES.concat("builds/");
	
	public static String pathOf(String dirPath, String filePath) {
		return new File(dirPath, filePath).getPath();
	}
	
}
