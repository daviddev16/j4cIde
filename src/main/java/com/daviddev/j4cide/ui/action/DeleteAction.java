package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.InputMapper;
import com.daviddev.j4cide.ui.base.FileExplorerTree;

/*TODO:REFATORAR DEPOIS*/
public class DeleteAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public DeleteAction() { super("Deletar"); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		
		FileExplorerTree explorerTree = contextManager.getFileExplorerUI().getExplorerTree();

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				explorerTree.getLastSelectedPathComponent();
		if (node == null) return;
		FileTreeNode nodeInfo = (FileTreeNode)node.getUserObject();
		
		if (JOptionPane.showConfirmDialog(null, "Tem certeza que você deseja excluir este arquivo?") == JOptionPane.YES_OPTION) {
			new File(nodeInfo.getFilePath()).delete();
			JTabbedPane tabbedPane = contextManager.getEditorUI().getTabbedPane();
			for (int i = 0; i < tabbedPane.getTabCount(); i++) {
				if(tabbedPane.getTitleAt(i).equalsIgnoreCase(nodeInfo.getName())) {
					tabbedPane.removeTabAt(i);
				}
			}
			contextManager.getFileExplorerUI().getExplorerTree().load();
		}
		
		
		
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_DELETE;
	}
	
}
