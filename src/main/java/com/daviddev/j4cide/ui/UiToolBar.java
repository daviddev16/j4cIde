package com.daviddev.j4cide.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JToolBar;

import com.daviddev.j4cide.core.Environment;
import com.daviddev.j4cide.external.FlatSVGIcon;
import com.daviddev.j4cide.ui.action.CreateAction;
import com.daviddev.j4cide.ui.action.ExportAction;
import com.daviddev.j4cide.ui.action.SaveAction;
import com.daviddev.j4cide.ui.component.UiButton;
import com.daviddev.j4cide.ui.handler.ActionsHandler;
import com.daviddev.j4cide.util.ColorUtil;

public class UiToolBar extends JToolBar {

	private static final long serialVersionUID = -4300540640935411914L;

	public UiToolBar() {

		addSeparator(new Dimension(2, 1));
		setOpaque(true);
		//setBackground(ColorUtil.darker(Environment.BACKGROUND, 0.01f));
		
		
		UiButton lblNewLabel = new UiButton();
		lblNewLabel.setText("Projeto em C / GCC (MinGW)");
		lblNewLabel.setContentAreaFilled(false);
		lblNewLabel.setIcon(new FlatSVGIcon(new File("./profile/icons/linked_16px.svg")));
		add(lblNewLabel);
		
		addSeparator(new Dimension(10, 1));
		
		UiButton btnNewButton_1_1 = new UiButton();
		btnNewButton_1_1.setAction(ActionsHandler.actionOf(SaveAction.class));
		btnNewButton_1_1.setIcon(new FlatSVGIcon(new File("./profile/icons/save_16px.svg")));
		add(btnNewButton_1_1);
		
		
		UiButton btnNewButton_1_3 = new UiButton();
		btnNewButton_1_3.setAction(ActionsHandler.actionOf(ExportAction.class));
		btnNewButton_1_3.setIcon(new FlatSVGIcon(new File("./profile/icons/export_16px.svg")));
		add(btnNewButton_1_3);
		
		UiButton btnNewButton_1_6 = new UiButton();
		btnNewButton_1_6.setAction(ActionsHandler.actionOf(CreateAction.class));
		btnNewButton_1_6.setIcon(new FlatSVGIcon(new File("./profile/icons/cpp2_16px.svg")));
		add(btnNewButton_1_6);
		
		addSeparator(new Dimension(10, 1));
		
		UiButton btnNewButton = new UiButton();
		btnNewButton.setText("Rodar");
		btnNewButton.setIcon(new FlatSVGIcon(new File("./profile/icons/execute_16px.svg")));
		add(btnNewButton);
		
		UiButton btnNewButton_1 = new UiButton();
		btnNewButton_1.setIcon(new FlatSVGIcon(new File("./profile/icons/stop_16px.svg")));
		btnNewButton_1.setText("Parar");
		add(btnNewButton_1);
		
		UiButton btnNewButton_2 = new UiButton();
		btnNewButton_2.setIcon(new FlatSVGIcon(new File("./profile/icons/kill_16px.svg")));
		btnNewButton_2.setText("Matar Contextos");
		add(btnNewButton_2);
		
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
	
}
