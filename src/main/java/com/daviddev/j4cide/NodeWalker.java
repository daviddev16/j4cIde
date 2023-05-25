package com.daviddev.j4cide;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.tree.DefaultMutableTreeNode;

import com.daviddev.j4cide.ui.component.FileExplorerTree;

/* fazer isso na base de arquvio ao inves de DefaultMutableTreeNode */
public class NodeWalker {

	private final FileExplorerTree explorerTree;
	
	public NodeWalker(FileExplorerTree explorerTree) {
		this.explorerTree = explorerTree;
	}
	
	public List<String> walk(Function<String, Boolean> filter) {
		List<String> paths = new ArrayList<>();
		walker(paths, filter, getExplorerTree().getRootNode());
		return paths;
	}
	
	private void walker(List<String> paths, Function<String, Boolean> filter, DefaultMutableTreeNode rootNode) {
		if (!rootNode.getAllowsChildren() || rootNode.getChildCount() == 0) {
			return;
		}
		for (int i = 0; i < rootNode.getChildCount(); i++) {
			DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) rootNode.getChildAt(i);
			walker(paths, filter, childNode);
		}
	}

	public FileExplorerTree getExplorerTree() {
		return explorerTree;
	}

	
	
}
