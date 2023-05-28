package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;

public class EmptyAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public EmptyAction() { super(""); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {}

	@Override
	public KeyStroke keys() {
		return KeyStroke.getKeyStroke(KeyEvent.CHAR_UNDEFINED);
	}
}

