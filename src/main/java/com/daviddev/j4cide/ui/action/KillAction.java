package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

public class KillAction extends GenericAction {

	private static final long serialVersionUID = 468292077593913369L;
	
	public KillAction() { super("Matar Processos"); }
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_KILL;
	}

	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {}
}
