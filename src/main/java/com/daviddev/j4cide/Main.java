package com.daviddev.j4cide;

import java.awt.EventQueue;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.factory.FileTreeNodeFactory;
import com.daviddev.j4cide.ui.CideThemeLoader;
import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.handler.ActionsHandler;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ApplicationContextManager.initializeContextManager();
					CideThemeLoader.setupConfiguredTheme();
					IconMapper.registerAllIcons();
					
					ActionsHandler.registerAllActions();
					FileTreeNodeFactory.initialize();
					
					UiApplication.createApplicationWindow();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
