package com.daviddev.j4cide.ui.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.DefaultMutableTreeNode;

import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.base.FileExplorerTree;

public class FileExplorerTreeEventHandler extends MouseAdapter {

	private final FileExplorerTree explorerTree;
	private final UiCodeScene codeScene;

	public FileExplorerTreeEventHandler(FileExplorerTree explorerTree) {
		this.explorerTree = explorerTree;
		this.codeScene = getExplorerTree().getExplorerPane().getCodeScene();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					explorerTree.getLastSelectedPathComponent();
			if (node == null) return;
			FileTreeNode nodeInfo = (FileTreeNode)node.getUserObject();
			if (!nodeInfo.isEditing()) {
				getCodeScene().getEditorPane().newTabOf(nodeInfo);
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			System.out.println("aooa");
		}
		
	}

	public FileExplorerTree getExplorerTree() {
		return explorerTree;
	}

	public UiCodeScene getCodeScene() {
		return codeScene;
	}

}
