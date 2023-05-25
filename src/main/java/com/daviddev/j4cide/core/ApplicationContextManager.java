package com.daviddev.j4cide.core;

import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;

public final class ApplicationContextManager {

	private static ApplicationContextManager instance;

	private volatile UiApplication uiApplication;
	private volatile UiCodeScene currentCodeScene;

	public ApplicationContextManager() {
		instance = this;
	}

	public static void initializeContextManager() {
		if (instance == null)
			instance = new ApplicationContextManager();
	}

	public static ApplicationContextManager getContextManager() {
		return instance;
	}
	
	public void setCurrentCodeScene(UiCodeScene currentCodeScene) {
		this.currentCodeScene = currentCodeScene;
	}

	public void setUiApplication(UiApplication uiApplication) {
		if (this.uiApplication == null) {
			this.uiApplication = uiApplication;
		}
	}

	public UiCodeScene getCodeScene() {
		return currentCodeScene;
	}


	public UiApplication getUiApplication() {
		return uiApplication;
	}

}
