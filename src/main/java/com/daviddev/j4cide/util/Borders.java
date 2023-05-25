package com.daviddev.j4cide.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.daviddev.j4cide.core.Environment;
import com.daviddev.j4cide.ui.UiApplication;

public final class Borders {

	public static final Border HEADER_TITLE_BORDER = new EmptyBorder(1, 5, 1, 1);

	public static Border createBorderedPane(int top, int left, int right, int bottom) {
		Color darkerColor = ColorUtil.darker(UiApplication.bg(), 0.05f);
		return BorderFactory.createMatteBorder(top, left, bottom, right, darkerColor);
	}
	
	public static Border createBorderedPane(int thickness) {
		thickness = Math.max(thickness, 1);
		Color darkerColor = ColorUtil.darker(UiApplication.bg(), 0.05f);
		return BorderFactory.createMatteBorder(thickness, thickness, thickness, thickness, darkerColor);
	}
}
