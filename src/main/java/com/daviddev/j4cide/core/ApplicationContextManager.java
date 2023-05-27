package com.daviddev.j4cide.core;

import com.daviddev.j4cide.api.UiAdapter;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.base.ConsolePane;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.ui.base.FileExplorerPane;

public final class ApplicationContextManager implements UiAdapter {

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

	public EditorPane getEditorUI() {
		return getContextManager().getCodeScene()
				.getEditorPane();
	}
	
	public FileExplorerPane getFileExplorerUI() {
		return getContextManager().getCodeScene()
				.getFileExplorerPane();
	}
	
	public ConsolePane getConsoleUI() {
		return getContextManager().getCodeScene()
				.getConsolePane();
	}
	
	public UiApplication getUiApplication() {
		return uiApplication;
	}
	
	public UiCodeScene getCodeScene() {
		return currentCodeScene;
	}

}
