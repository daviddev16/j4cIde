package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.UiCodeScene;

public class CreateAction extends GenericAction {

	private static final long serialVersionUID = 468292077593913369L;
	
	public CreateAction() { super("Novo..."); }
	
	@Override
	public KeyStroke keys() {
		return KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
	}

	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {
		UiCodeScene codeScene = contextManager.getCodeScene();
		System.out.println(codeScene.getEditorPane().getTabbedPane().getSelectedIndex());
		
	}
	
}
