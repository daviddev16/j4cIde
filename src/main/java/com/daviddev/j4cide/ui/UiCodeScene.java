package com.daviddev.j4cide.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.daviddev.j4cide.ui.component.ConsolePane;
import com.daviddev.j4cide.ui.component.EditorPane;
import com.daviddev.j4cide.ui.component.FileExplorerPane;
import com.daviddev.j4cide.ui.component.InspectionPane;

import java.awt.BorderLayout;

public class UiCodeScene extends JPanel {

	private static final long serialVersionUID = -8572338923630052229L;
	
	private final FileExplorerPane fileExplorerPane;
	private final EditorPane editorRootPane;
	private final ConsolePane consolePane;
	private final InspectionPane inspectionPane;
	
	private final JSplitPane consoleDividerPane;
	private final JSplitPane editorDividerPane;
	private final JSplitPane inspectionDividerPane;
	
	private final String projectFolder;

	public UiCodeScene(String projectFolder) {
		this.projectFolder = projectFolder;
		
		setLayout(new BorderLayout(0, 0));
		
		editorRootPane = new EditorPane(this);
		consolePane = new ConsolePane(this);
		fileExplorerPane = new FileExplorerPane(this);
		inspectionPane = new InspectionPane(this);
		
		inspectionDividerPane = new JSplitPane();
		initializeInspectionPane();
		
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
		editorDividerPane.setRightComponent(inspectionDividerPane);
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
	
	private void initializeInspectionPane() {
		inspectionDividerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		inspectionDividerPane.setMinimumSize(new Dimension(209, 500));
		inspectionDividerPane.setLeftComponent(editorRootPane);
		inspectionDividerPane.setRightComponent(inspectionPane);
		inspectionDividerPane.setContinuousLayout(true);
		inspectionDividerPane.setDividerLocation(0.9);
	}
	
	public JSplitPane getConsoleDividerPane() {
		return consoleDividerPane;
	}

	public JSplitPane getEditorDividerPane() {
		return editorDividerPane;
	}

	public FileExplorerPane getFileExplorerPane() {
		return fileExplorerPane;
	}
	
	public InspectionPane getInspectionPane() {
		return inspectionPane;
	}

	public ConsolePane getConsolePane() {
		return consolePane;
	}
	
	public EditorPane getEditorPane() {
		return editorRootPane;
	}

	public String getProjectFolder() {
		return projectFolder;
	}
	
}
