package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.api.ExpandableInterfaceFactory.InteractStateType;
import com.daviddev.j4cide.api.ExpandableInterfaceFactory.InterfaceType;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

public class OpenConsoleAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	public OpenConsoleAction() { super("Logs"); }

	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) 
	{
		contextManager.changeState(InteractStateType.OPEN, InterfaceType.CONSOLE);
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_OPEN_CONSOLE;
	}
}
