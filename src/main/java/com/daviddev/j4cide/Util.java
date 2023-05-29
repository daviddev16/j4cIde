package com.daviddev.j4cide;

import java.awt.Font;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;

public class Util {
	
	public static void setFont(RSyntaxTextArea textArea, Font font) {
		if (font != null) {
			SyntaxScheme ss = textArea.getSyntaxScheme();
			ss = (SyntaxScheme) ss.clone();
			for (int i = 0; i < ss.getStyleCount(); i++) {
				if (ss.getStyle(i) != null) {
					ss.getStyle(i).font = font;
				}
			}
			textArea.setSyntaxScheme(ss);
			textArea.setFont(font);
		}
	}
}
