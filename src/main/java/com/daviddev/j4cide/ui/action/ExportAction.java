package com.daviddev.j4cide.ui.action;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.InputMapper;
import com.daviddev.j4cide.ui.UiCodeScene;

public class ExportAction extends GenericAction {

	private static final long serialVersionUID = 468292077593913369L;
	
	public ExportAction() { super("Exportar..."); }
	
	@Override
	public void perform(ActionEvent event, ApplicationContextManager contextManager) {

		UiCodeScene codeScene = contextManager.getCodeScene();
		System.out.println(codeScene.getEditorPane().getTabbedPane().getSelectedIndex());
		
	}
	
	@Override
	public KeyStroke keys() {
		return InputMapper.VK_EXPORT;
	}
	
}
