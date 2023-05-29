package com.daviddev.j4cide.ui;


import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JToolBar;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.ui.action.OpenConfigAction;
import com.daviddev.j4cide.ui.action.CreateAction;
import com.daviddev.j4cide.ui.action.ExecuteAction;
import com.daviddev.j4cide.ui.action.ExportAction;
import com.daviddev.j4cide.ui.action.KillAction;
import com.daviddev.j4cide.ui.action.OpenConsoleAction;
import com.daviddev.j4cide.ui.action.OpenExplorerAction;
import com.daviddev.j4cide.ui.action.SaveAction;
import com.daviddev.j4cide.ui.action.StopAction;
import com.daviddev.j4cide.ui.component.UiButton;
import com.daviddev.j4cide.ui.handler.ActionsHandler;

public class UiToolBar extends JToolBar {

	private static final long serialVersionUID = -4300540640935411914L;

	public UiToolBar() {

		addSeparator(new Dimension(2, 1));
		setOpaque(true);
		
		createButton("Projeto em C / GCC (mingw64)", IconMapper.LINKED_ICON, 
				(compilerBtn) -> compilerBtn.setContentAreaFilled(false));
		
		addSeparator(new Dimension(10, 1));
		
		createButton(IconMapper.SAVE_ICON, SaveAction.class);
		createButton(IconMapper.EXPORT_ICON, ExportAction.class);
		createButton(IconMapper.CPP2_ICON, CreateAction.class);

		addSeparator(new Dimension(10, 1));
		
		createButton(IconMapper.EXECUTE_ICON, ExecuteAction.class);
		createButton(IconMapper.STOP_ICON, StopAction.class);
		createButton(IconMapper.KILL_ICON, KillAction.class);
		
		addSeparator(new Dimension(10, 1));
		
		createButton(IconMapper.CONFIG_ICON, OpenConfigAction.class);
		createButton(IconMapper.LOG_ICON, OpenConsoleAction.class);
		createButton(IconMapper.EXPLORER_ICON, OpenExplorerAction.class);

	}
	
	private void createButton(String name, String iconId, Consumer<UiButton> configConsumer) {
		UiButton uiButton = new UiButton();
		if (configConsumer != null)
			configConsumer.accept(uiButton);
		uiButton.setText(name);
		uiButton.setIcon(IconMapper.icon(iconId));
		add(uiButton);
	}
	
	private void createButton(String iconId, Class<? extends GenericAction> clazz) {
		UiButton uiButton = new UiButton();
		uiButton.setAction(ActionsHandler.actionOf(clazz));
		uiButton.setIcon(IconMapper.icon(iconId));
		add(uiButton);
	}
	
}
