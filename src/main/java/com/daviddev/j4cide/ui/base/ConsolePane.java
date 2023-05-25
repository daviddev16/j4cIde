package com.daviddev.j4cide.ui.base;

import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.component.ConsoleTextArea;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ConsolePane extends HeaderPanel implements CodeSceneChild {

	private static final long serialVersionUID = -3272703785406586777L;

	private final UiCodeScene codeScene;
	
	public ConsolePane(UiCodeScene codeScene) {
		super(100, 20);
		
		this.codeScene = codeScene;
		
		setTitle("Console");
		getContentPanel().setOpaque(false);
		getContentPanel().setLayout(new BorderLayout(0, 0));

		ConsoleTextArea area = new ConsoleTextArea(UiApplication.DEFAULT_STYLE);
		area.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPane.setOpaque(false);
		getContentPanel().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
		
	}

	@Override
	public UiCodeScene getCodeScene() {
		return codeScene;
	}

}
