package com.daviddev.j4cide.api;

import com.daviddev.j4cide.ui.base.ConsolePane;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.ui.base.FileExplorerPane;

public interface UiAdapter {

	EditorPane getEditorUI();
	
	FileExplorerPane getFileExplorerUI();
	
	ConsolePane getConsoleUI();
		
}
