package com.daviddev.j4cide.ui.custom;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.daviddev.j4cide.ui.component.UiButton;

public class TipButtonMouseEvent extends MouseAdapter {

	public static final TipButtonMouseEvent CURRENT = new TipButtonMouseEvent();
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getComponent() instanceof UiButton) {
			UiButton button = (UiButton)e.getComponent();
			//button.setText(button.getOriginalText());
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getComponent() instanceof UiButton) {
			UiButton button = (UiButton)e.getComponent();
			button.setText("");
		}
	}

}
