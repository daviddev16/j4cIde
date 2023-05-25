package com.daviddev.j4cide.factory;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.daviddev.j4cide.core.IconMapper;
import com.daviddev.j4cide.model.FileExtensionType;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.util.Extensions;
import com.daviddev.j4cide.util.IOUtil;
import com.daviddev.j4cide.util.Validator;

public final class FileTreeNodeFactory {

	private static FileTreeNodeFactory instance;

	private FileTreeNodeFactory() {
		instance = this;
	}

	public DefaultMutableTreeNode createDefaultTreeNode(File file, boolean isRootProject) {
		return new DefaultMutableTreeNode(create(file, isRootProject));
	}

	public FileTreeNode createFileNode(File file, Icon icon, FileExtensionType extensionType) {
		return new FileTreeNode(file.getAbsolutePath(), icon, file.getName(), extensionType);
	}
	
	public FileTreeNode create(File file, boolean isRootProject) {
		Validator.checkIsReadable(file, "file");
		FileExtensionType extensionType = getFileExtensionType(file, isRootProject);
		ImageIcon icon = IconMapper.icon(extensionType.getAssociatedIcon());
		return createFileNode(file, icon, extensionType);
	}
	
	private FileExtensionType getFileExtensionType(File file, boolean isRootProject) {
		String extension = IOUtil.getExtension(file);
		if (isRootProject)
			return FileExtensionType.PROJECT;
		else if (extension.equalsIgnoreCase(Extensions.C))
			return FileExtensionType.C;
		else if (extension.equalsIgnoreCase(Extensions.H))
			return FileExtensionType.H;
		else if (extension.equalsIgnoreCase(Extensions.DIR))
			return FileExtensionType.DIRECTORY;
		else 
			return FileExtensionType.OTHER;
	}

	public static FileTreeNodeFactory getInstance() {
		return instance;
	}

	public static void initialize() {
		if (instance == null)
			new FileTreeNodeFactory();
	}

}
