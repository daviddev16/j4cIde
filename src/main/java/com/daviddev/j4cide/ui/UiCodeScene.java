package com.daviddev.j4cide.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.daviddev.j4cide.ui.base.ConsolePane;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.ui.base.FileExplorerPane;

import java.awt.BorderLayout;

public class UiCodeScene extends JPanel {

	private static final long serialVersionUID = -8572338923630052229L;
	
	private final FileExplorerPane fileExplorerPane;
	private final EditorPane editorRootPane;
	private final ConsolePane consolePane;
	
	private final JSplitPane consoleDividerPane;
	private final JSplitPane editorDividerPane;

	public UiCodeScene() {
		setLayout(new BorderLayout(0, 0));
		
		editorRootPane = new EditorPane(this);
		consolePane = new ConsolePane(this);
		fileExplorerPane = new FileExplorerPane(this);

		editorDividerPane = new JSplitPane();
		initializeEditorDividerPane();
		
		consoleDividerPane = new JSplitPane();
		initializeConsoleDividerPane();
		
		add(consoleDividerPane);
	}
	
	private void initializeEditorDividerPane() {
		editorDividerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		editorDividerPane.setMinimumSize(new Dimension(209, 500));
		editorDividerPane.setLeftComponent(fileExplorerPane);
		editorDividerPane.setRightComponent(editorRootPane);
		editorDividerPane.setContinuousLayout(true);
		editorDividerPane.setDividerLocation(0.5);
	}
	
	private void initializeConsoleDividerPane() {
		consoleDividerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		consoleDividerPane.setLeftComponent(editorDividerPane);
		consoleDividerPane.setRightComponent(consolePane);
		editorDividerPane.setContinuousLayout(true);
		consoleDividerPane.setDividerLocation(0.7);
	}

	public FileExplorerPane getFileExplorerPane() {
		return fileExplorerPane;
	}

	public EditorPane getEditorPane() {
		return editorRootPane;
	}

	public ConsolePane getConsolePane() {
		return consolePane;
	}
	
	

}
