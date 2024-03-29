package com.daviddev.j4cide.ui;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;

public final class CideThemeLoader {

	public static void setupConfiguredTheme() {
		if (SystemInfo.isMacOS) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("apple.awt.application.name", "J4cIde");
			System.setProperty("apple.awt.application.appearance", "system");
		}
		if (SystemInfo.isLinux) {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		}
	
		FlatMacDarkLaf.setup();
		UIManager.put( "Button.arc", 2);
		UIManager.put(UiApplication.GLOBAL_BG_KEY, UIManager.get("Panel.background"));
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
	}
}
