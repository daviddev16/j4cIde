package com.daviddev.j4cide.ui;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Consumer;

import javax.swing.Action;
import javax.swing.JToolBar;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.external.FlatSVGIcon;
import com.daviddev.j4cide.ui.action.CreateAction;
import com.daviddev.j4cide.ui.action.EmptyAction;
import com.daviddev.j4cide.ui.action.ExportAction;
import com.daviddev.j4cide.ui.action.SaveAction;
import com.daviddev.j4cide.ui.component.UiButton;
import com.daviddev.j4cide.ui.handler.ActionsHandler;

public class UiToolBar extends JToolBar {

	private static final long serialVersionUID = -4300540640935411914L;

	public UiToolBar() {

		addSeparator(new Dimension(2, 1));
		setOpaque(true);
		//setBackground(ColorUtil.darker(Environment.BACKGROUND, 0.01f));
		
		createButton("Projeto em C / GCC (MinGW)", IconMapper.LINKED_ICON, 
				(compilerBtn) -> compilerBtn.setContentAreaFilled(false));
		
		addSeparator(new Dimension(10, 1));
		
		createButton(IconMapper.SAVE_ICON, SaveAction.class);
		createButton(IconMapper.EXPORT_ICON, ExportAction.class);
		createButton(IconMapper.CPP2_ICON, CreateAction.class);

		addSeparator(new Dimension(10, 1));
		
		createButton(IconMapper.EXECUTE_ICON, EmptyAction.class); /* Rodar */
		createButton(IconMapper.STOP_ICON, EmptyAction.class); /* Parar */
		createButton(IconMapper.KILL_ICON, EmptyAction.class); /* Matar Processos */
		
		
		addSeparator(new Dimension(10, 1));
		
		UiButton btnNewButton_6 = new UiButton();
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//consolePane.setVisible(true);
				//splitPane_1.setDividerLocation(0.3d);
				//splitPane_1.revalidate();
			}
		});
		btnNewButton_6.setIcon(new FlatSVGIcon(new File("./profile/icons/config_16px.svg")));
		btnNewButton_6.setText("Configurações");
		add(btnNewButton_6);
		
		UiButton btnNewButton_6_1 = new UiButton();
		btnNewButton_6.setIcon(new FlatSVGIcon(new File("./profile/icons/log_16px.svg")));
		btnNewButton_6.setText("Exibir Logs");
		add(btnNewButton_6_1);
		
		UiButton btnNewButton_6_1_1 = new UiButton();
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fileExplorerPane.setVisible(true);
				//splitPane.setDividerLocation(0.2d);
				//splitPane.revalidate();
			}
		});
		btnNewButton_6_1_1.setIcon(new FlatSVGIcon(new File("./profile/icons/explorer_16px.svg")));
		btnNewButton_6_1_1.setText("Exibir Explorador de Arquivos");
		add(btnNewButton_6_1_1);
	}
	
	private void createButton(String name, String iconId, Consumer<UiButton> configConsumer) {
		UiButton uiButton = new UiButton();
		if (configConsumer != null)
			configConsumer.accept(uiButton);
		uiButton.setText(name);
		uiButton.setIcon(IconMapper.icon(iconId));
		add(uiButton);
	}
	
	private void createButton(String iconId, Consumer<UiButton> configConsumer) {
		UiButton uiButton = new UiButton();
		if (configConsumer != null)
			configConsumer.accept(uiButton);
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
