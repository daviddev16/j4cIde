package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.api.ExpandableInterfaceFactory.InteractStateType;
import com.daviddev.j4cide.api.ExpandableInterfaceFactory.InterfaceType;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;

public class OpenInspectorAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	public OpenInspectorAction() { super("Inspector"); }

	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) 
	{
		contextManager.changeState(InteractStateType.OPEN, InterfaceType.INSPECTOR);
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_INSPECTOR;
	}
}
