package com.daviddev.j4cide.core;

import java.awt.Color;

/* TODO: */
public final class ApplicationConsoleLogger {

	
	public void info(String message) {
		ApplicationContextManager.getContextManager().getConsoleUI()
			.area.appendText(message, Color.WHITE);
	}
	
	public void error(String message) {
		ApplicationContextManager.getContextManager().getConsoleUI()
			.area.appendText(message, Color.RED);
	}
	
	public void warn(String message) {
		ApplicationContextManager.getContextManager().getConsoleUI()
			.area.appendText(message, Color.YELLOW);
	}
	
}
