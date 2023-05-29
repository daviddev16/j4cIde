package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

/*TODO:REFATORAR DEPOIS*/
public class CreateAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public CreateAction() { super("Novo..."); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {

		String fileName = JOptionPane.showInputDialog("Nome do arquivo:");
		File newFile = new File(contextManager.getSourceFolder(), fileName);
		if (!newFile.exists()) {
			try {
				newFile.createNewFile();
				contextManager.getFileExplorerUI().getExplorerTree().load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_CREATE;
	}
	
}
