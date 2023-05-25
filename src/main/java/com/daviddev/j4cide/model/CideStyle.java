package com.daviddev.j4cide.model;

import java.awt.Font;
import java.io.File;

public class CideStyle {

	private String identifier;
	private File codeStyleFile;
	private Font codeFont;
	private String flatlafTheme;
	
	public CideStyle(String identifier, File codeStyleFile, Font codeFont, String flatlafTheme) {
		this.identifier = identifier;
		this.codeStyleFile = codeStyleFile;
		this.codeFont = codeFont;
		this.flatlafTheme = flatlafTheme;
	}
	
	public Font getSizedFont(int size) {
		return codeFont.deriveFont((float)size);
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public File getCodeStyleFile() {
		return codeStyleFile;
	}

	public Font getCodeFont() {
		return codeFont;
	}

	public String getFlatlafTheme() {
		return flatlafTheme;
	}
	
}
