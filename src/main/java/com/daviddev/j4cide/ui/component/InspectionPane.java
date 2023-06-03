package com.daviddev.j4cide.ui.component;

import java.awt.BorderLayout;

import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.api.Interactable;
import com.daviddev.j4cide.core.logger.LogLevel;
import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.base.HeaderPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class InspectionPane extends HeaderPanel implements CodeSceneChild, Interactable {

	private static final long serialVersionUID = -3272703785406586777L;

	private final UiCodeScene codeScene;

	private final JTextArea textArea;

	public InspectionPane(UiCodeScene codeScene) {
		super(100, 100);

		this.codeScene = codeScene;

		setTitle("Inspector");
		setIcon(IconMapper.icon(IconMapper.INSPECTION_ICON));
		getContentPanel().setOpaque(false);
		getContentPanel().setLayout(new BorderLayout(0, 0));

		this.textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		CideStyle cideStyle = UiApplication.DEFAULT_STYLE;
		textArea.setFont(cideStyle.getSizedFont(12));
		textArea.setBackground(UiApplication.bg());
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setOpaque(false);
		getContentPanel().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);

	}

	public void clearText() {
		textArea.setText("");
	}

	public void appendText(String text) {
		textArea.setForeground(LogLevel.ERROR.getColor());
		textArea.setText(textArea.getText() + "\n" + text);
	}
	
	public void ok() {
		textArea.setForeground(LogLevel.INFO.getColor());
		textArea.setText("OK!");
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
		getCodeScene().getEditorDividerPane().setDividerLocation(0.8d);
		getCodeScene().getEditorDividerPane().revalidate();
	}

}
