package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;

public class StopAction extends GenericAction {

	private static final long serialVersionUID = 468292077593913369L;
	
	public StopAction() { super("Parar"); }
	
	@Override
	public KeyStroke keys() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
	}

	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
	}
}
