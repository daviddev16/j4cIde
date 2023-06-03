package com.daviddev.j4cide.ui.component;

import java.awt.BorderLayout;

import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.api.Interactable;
import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.base.HeaderPanel;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConsolePane extends HeaderPanel implements CodeSceneChild, Interactable {

	private static final long serialVersionUID = -3272703785406586777L;

	private final UiCodeScene codeScene;
	
	private final ConsoleTextArea  consoleArea;
	
	public ConsolePane(UiCodeScene codeScene) {
		super(100, 20);
		
		this.consoleArea = new ConsoleTextArea(UiApplication.DEFAULT_STYLE);
		this.codeScene = codeScene;
		
		setTitle("Console");
		setIcon(IconMapper.icon(IconMapper.LINKED_ICON));
		getContentPanel().setOpaque(false);
		getContentPanel().setLayout(new BorderLayout(0, 0));

		
		consoleArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(consoleArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPane.setOpaque(false);
		getContentPanel().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
	}

	public ConsoleTextArea getConsoleArea() {
		return consoleArea;
	}
	
	@Override
	public UiCodeScene getCodeScene() {
		return codeScene;
	}

	@Override
	public void close() {
		setVisible(false);
	}

	@Override
	public void open() {
		setVisible(true);
		getCodeScene().getConsoleDividerPane().setDividerLocation(0.2d);
		getCodeScene().getConsoleDividerPane().revalidate();
	}

}
