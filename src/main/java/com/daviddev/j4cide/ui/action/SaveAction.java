package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

public class SaveAction extends GenericAction {

	private static final long serialVersionUID = 468292077593913369L;
	
	public SaveAction() { super("Salvar"); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		contextManager.saveContext();
	}

	@Override
	public KeyStroke keys() {
		return InputMapper.VK_SAVE;
	}
}
