package com.daviddev.j4cide.api;

import java.awt.Component;

import com.daviddev.j4cide.ui.base.ConsolePane;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.ui.base.FileExplorerPane;
import com.daviddev.j4cide.ui.component.ConsoleTextArea;
import com.daviddev.j4cide.ui.model.CloseActionType;

public interface CoreFrontendAdapter extends ExpandableInterfaceFactory {

	EditorPane getEditorUI();
	Component getSelectedTabComponent();
	void closeTabOfChildCodeEditor(CloseActionType actionType, int tabIndex);
	void closeTabContext(int tabIndex);
	void saveContext();
	
	FileExplorerPane getFileExplorerUI();

	ConsolePane getConsoleUI();
	ConsoleTextArea getConsoleArea();
}
