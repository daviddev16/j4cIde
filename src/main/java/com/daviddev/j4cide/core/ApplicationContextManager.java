package com.daviddev.j4cide.core;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import com.daviddev.j4cide.api.CoreBackendAdapter;
import com.daviddev.j4cide.api.Interactable;
import com.daviddev.j4cide.api.CoreFrontendAdapter;
import com.daviddev.j4cide.core.logger.ApplicationConsoleLogger;
import com.daviddev.j4cide.ui.ChildCodeEditor;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.base.ConsolePane;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.ui.base.FileExplorerPane;
import com.daviddev.j4cide.ui.component.ConsoleTextArea;
import com.daviddev.j4cide.ui.model.CloseActionType;
import com.daviddev.j4cide.util.Directories;

public final class ApplicationContextManager implements CoreFrontendAdapter, CoreBackendAdapter {

	private static ApplicationContextManager instance;

	/* front-end */
	private volatile UiApplication uiApplication;
	private volatile UiCodeScene currentCodeScene;

	/* back-end */
	private final ApplicationConsoleLogger consoleLogger;

	public ApplicationContextManager() {
		consoleLogger = new ApplicationConsoleLogger();
		instance = this;
	}

	public static void initializeContextManager() {
		if (instance == null)
			instance = new ApplicationContextManager();
	}

	public static ApplicationContextManager getContextManager() {
		return instance;
	}

	@Override
	public void changeState(InteractStateType stateType, InterfaceType interfaceType) {
		Interactable interactable = null;
		switch (interfaceType) {
		case FILE_EXPLORER:
			interactable = getFileExplorerUI();
			break;
		case CONSOLE:
			interactable = getConsoleUI();
			break;
		}
		if (interactable == null) {
			getLogger().warn("Fonte de interação inválida.");
			return;
		}
		if (stateType == InteractStateType.CLOSE)
			interactable.close();
		else
			interactable.open();
	}

	@Override
	public void closeTabOfChildCodeEditor(CloseActionType actionType, int tabIndex) {
		switch(actionType) {
		case CANCELLED: /* ignore */
			return;
		case SAVE_AND_EXIT:
			saveContext();
		case ONLY_EXIT:
			closeTabContext(tabIndex);
		}
	}
	
	@Override
	public void closeTabContext(int tabIndex) {
		Component codeEditor = getSelectedTabComponent();
		if (!(codeEditor instanceof ChildCodeEditor)) {
			return;
		}
		((ChildCodeEditor)codeEditor).getTreeNodeOwner().close();
		getEditorUI().getTabbedPane().removeTabAt(tabIndex);
	}
	
	@Override
	public void saveContext() {
		Component codeEditor = getSelectedTabComponent();
		if (!(codeEditor instanceof ChildCodeEditor)) {
			return;
		}
		((ChildCodeEditor)codeEditor).saveToFile();
	}

	public ApplicationConsoleLogger getLogger() {
		return consoleLogger;
	}

	@Override
	public EditorPane getEditorUI() {
		return getContextManager().getCodeScene()
				.getEditorPane();
	}

	@Override
	public FileExplorerPane getFileExplorerUI() {
		return getContextManager().getCodeScene()
				.getFileExplorerPane();
	}

	@Override
	public ConsolePane getConsoleUI() {
		return getContextManager().getCodeScene()
				.getConsolePane();
	}

	@Override
	public Component getSelectedTabComponent() {
		return getContextManager().getEditorUI()
				.getTabbedPane().getSelectedComponent();
	}
	
	public void setCurrentCodeScene(UiCodeScene currentCodeScene) {
		this.currentCodeScene = currentCodeScene;
	}

	public void setUiApplication(UiApplication uiApplication) {
		if (this.uiApplication == null) {
			this.uiApplication = uiApplication;
		}
	}

	public UiApplication getUiApplication() {
		return uiApplication;
	}

	public UiCodeScene getCodeScene() {
		return currentCodeScene;
	}

	@Override
	public ConsoleTextArea getConsoleArea() {
		return getConsoleUI().getConsoleArea();
	}

	@Override
	public File getSourceFolder() {
		return new File(getCodeScene()
				.getProjectFolder());
	}

	@Override
	public String getCompilerPath() {
		try {
			return new File(Directories.pathOf(Directories.BINARIES, 
					"/mingw64/bin/gcc.exe"))
					.getCanonicalPath();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String getApplicationRunnerPath() {
		try {
			return new File(Directories.pathOf(Directories.J4CIDE_RUNNER, 
					"/bin/Release/J4cIde.Runner.exe"))
					.getCanonicalPath();
		} catch (IOException e) {
			return null;
		}
	}

}
