package com.daviddev.j4cide.ui.base;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import com.daviddev.j4cide.factory.FileTreeNodeFactory;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.component.FileExplorerPane;
import com.daviddev.j4cide.ui.handler.FileExplorerTreeEventHandler;

public class FileExplorerTree extends JTree implements TreeCellRenderer {

	private static final long serialVersionUID = -4564019534259200105L;

	private final FileTreeNodeFactory factory = FileTreeNodeFactory.getInstance();
	
	private final FileExplorerTreeEventHandler treeActionHandler;
	private final FileExplorerPane explorerPane;
	private final File projectFile;

	private DefaultTreeModel model;
	
	public FileExplorerTree(FileExplorerPane explorerPane, File projectFile) {
		this.explorerPane = explorerPane;
		this.projectFile = projectFile;
		this.treeActionHandler = new FileExplorerTreeEventHandler(this);
		load();
		addMouseListener(treeActionHandler);
		setCellRenderer(this);
		
	}
	
	public void load() {
		clearSelection();
		model = new DefaultTreeModel(null);
		getModel().setRoot(factory.createDefaultTreeNode(projectFile, true));
		walkThrough(projectFile, getRootNode());
		setModel(model);	
		
	}

	public void walkThrough(File file, DefaultMutableTreeNode root) {
		for (File child : file.listFiles()) {
			DefaultMutableTreeNode node = factory.createDefaultTreeNode(child, false);
			root.add(node);
			if (child.isDirectory()) {
				walkThrough(child, node);
			}
		}
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		
		if (value instanceof DefaultMutableTreeNode) {
		
			FileTreeNode fileTreeNode = (FileTreeNode)
					((DefaultMutableTreeNode) value).getUserObject();
			
			JLabel label = new JLabel();
			label.setText(fileTreeNode.getName());
			label.setIcon(fileTreeNode.getIcon());
			
			return label;
		}
		return new JLabel(value.toString());
	}

	public FileExplorerPane getExplorerPane() {
		return explorerPane;
	}

	public DefaultMutableTreeNode getRootNode() {
		return ((DefaultMutableTreeNode)getModel().getRoot());
	}

	public DefaultTreeModel getModel() {
		return model;
	}


	public File getProjectFile() {
		return projectFile;
	}

}
