package com.daviddev.j4cide.api;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.daviddev.j4cide.core.ApplicationContextManager;

public abstract class GenericAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public GenericAction(String name) { 
		super(name);
		putValue(ACCELERATOR_KEY, keys());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		perform(e, ApplicationContextManager.getContextManager());
	}

	public abstract KeyStroke keys();
	
	public abstract void perform(ActionEvent event, final ApplicationContextManager contextManager);

}
