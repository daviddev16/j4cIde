package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

public class CreateAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public CreateAction() { super("Novo..."); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		System.out.println("CREATE");
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_CREATE;
	}
	
}
