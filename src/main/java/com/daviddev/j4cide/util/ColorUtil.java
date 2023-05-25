package com.daviddev.j4cide.util;

import java.awt.Color;

public final class ColorUtil {

	public static Color darker(Color originalColor, float factor) {
		int red = (int)(originalColor.getRed() * (1 - factor));
		int green = (int)(originalColor.getGreen() * (1 - factor));
		int blue = (int)(originalColor.getBlue() * (1 - factor));
		return new Color(red, green, blue, originalColor.getAlpha());
	}
	
	public static Color brighter(Color originalColor, float factor) {
		int red = (int)(originalColor.getRed() * (1 + factor));
		int green = (int)(originalColor.getGreen() * (1 + factor));
		int blue = (int)(originalColor.getBlue() * (1 + factor));
		return new Color(Math.max(red, 255), Math.max(green, 255), 
				Math.max(blue, 255), originalColor.getAlpha());
	}
	
}
