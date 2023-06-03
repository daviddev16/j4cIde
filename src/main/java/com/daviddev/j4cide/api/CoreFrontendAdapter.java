package com.daviddev.j4cide.api;

import java.awt.Component;

import com.daviddev.j4cide.ui.component.ConsolePane;
import com.daviddev.j4cide.ui.component.ConsoleTextArea;
import com.daviddev.j4cide.ui.component.EditorPane;
import com.daviddev.j4cide.ui.component.FileExplorerPane;
import com.daviddev.j4cide.ui.component.InspectionPane;
import com.daviddev.j4cide.ui.model.CloseActionType;

public interface CoreFrontendAdapter extends ExpandableInterfaceFactory {

	EditorPane getEditorUI();
	Component getSelectedTabComponent();
	void closeTabOfChildCodeEditor(CloseActionType actionType, int tabIndex);
	void closeTabContext(int tabIndex);
	void saveContext();

	InspectionPane getInspectionUI();
	
	FileExplorerPane getFileExplorerUI();

	ConsolePane getConsoleUI();
	ConsoleTextArea getConsoleArea();
}
