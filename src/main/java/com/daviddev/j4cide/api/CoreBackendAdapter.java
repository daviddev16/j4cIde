package com.daviddev.j4cide.api;

import java.io.File;

public interface CoreBackendAdapter {

	File getSourceFolder();
	String getCompilerPath();
	String getApplicationRunnerPath();
}
