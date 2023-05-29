package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.CoreWorkspaceExecutor;
import com.daviddev.j4cide.ui.InputMapper;

public class ExecuteAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public ExecuteAction() { super("Construir e Rodar"); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		try {
			CoreWorkspaceExecutor.compileAndRun(contextManager);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_EXECUTE;
	}
}
