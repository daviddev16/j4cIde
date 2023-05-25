package com.daviddev.j4cide.model;

import javax.swing.Icon;

public class FileTreeNode {
	
	private String filePath;
	private Icon icon;
	private String name;
	private FileExtensionType extensionType;
	private boolean editing;
	
	public FileTreeNode(String filePath, Icon icon, String name, FileExtensionType extensionType) {
		this.filePath = filePath;
		this.icon = icon;
		this.name = name;
		this.extensionType = extensionType;
		this.editing = false;
	}

	public FileExtensionType getExtensionType() {
		return extensionType;
	}

	public void open() {
		editing = true;
	}
	
	public void close() {
		editing = false;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public Icon getIcon() {
		return icon;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isEditing() {
		return editing;
	}
	
}
