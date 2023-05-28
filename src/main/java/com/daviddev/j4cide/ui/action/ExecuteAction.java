package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.core.Compiler;
import com.daviddev.j4cide.ui.InputMapper;

public class ExecuteAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public ExecuteAction() { super("Rodar"); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		Compiler compiler = new Compiler(contextManager.getCodeScene().getProjectFolder());
		compiler.build(ApplicationContextManager.getContextManager(), "build.exe");
		
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_EXECUTE;
	}
}
