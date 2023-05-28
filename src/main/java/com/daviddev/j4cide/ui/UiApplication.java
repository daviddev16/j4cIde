package com.daviddev.j4cide.ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorUIResource;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.external.FlatSVGUtils;
import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.util.ColorUtil;

public class UiApplication extends JFrame {

	/*TODO:*/
	public static CideStyle DEFAULT_STYLE;

	/*TODO:*/
	static {
		try {
			DEFAULT_STYLE = new CideStyle("defaultStyle", new File(".\\profile\\themes\\monokai.xml"),
					Font.createFont(Font.TRUETYPE_FONT, new File(".//profile//fonts//FiraMono-Regular.ttf")), "flatDarculaLaf");
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static UiApplication instance;

	private static final long serialVersionUID = 6077917334900022692L;
	public static final String GLOBAL_BG_KEY = "globalBackground";

	private final UiToolBar toolBar = new UiToolBar();
	private final UiMenuBar menuBar = new UiMenuBar();
	private final UiCodeScene codeScene = new UiCodeScene(".//profile//workspace//Soma_001");

	private UiApplication() throws IOException {

		setTitle("j4CIde");
		setBounds(0, 0, 1200, 1000);
		setMinimumSize(getMinimumSize());
		setIconImage(FlatSVGUtils.svg2image(new File("./profile/icons/cpp_16px.svg").toURI().toURL(), 1.5f));
		ApplicationContextManager.getContextManager().setUiApplication(this);
		ApplicationContextManager.getContextManager().setCurrentCodeScene(codeScene);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(codeScene, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(codeScene, GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
					.addGap(6))
		);

		getContentPane().setLayout(groupLayout);
		setJMenuBar(menuBar);

		instance = this;
	}

	public static Color bg() {
		ColorUIResource colorResource = ((ColorUIResource)UIManager.get(GLOBAL_BG_KEY));
		return ColorUtil.darker(colorResource, 0.1f);
	}

	public static void createApplicationWindow() throws IOException {
		if (instance == null) {
			UiApplication applicationWindow = new UiApplication();
			applicationWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			applicationWindow.setVisible(true);
		}
	}

	public static UiApplication getInstance() {
		return instance;
	}

}
