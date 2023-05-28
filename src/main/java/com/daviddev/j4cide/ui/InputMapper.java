package com.daviddev.j4cide.ui;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public final class InputMapper {

	public static final KeyStroke VK_OPEN_EXPLORER = KeyStroke.getKeyStroke(KeyEvent.VK_A,
			KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
	
	public static final KeyStroke VK_OPEN_CONSOLE = KeyStroke.getKeyStroke(KeyEvent.VK_C,
			KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
	
	public static final KeyStroke VK_CONFIG = KeyStroke.getKeyStroke(KeyEvent.VK_C, 
			KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK);
	
	public static final KeyStroke VK_SAVE = KeyStroke.getKeyStroke(KeyEvent.VK_S, 
			KeyEvent.CTRL_DOWN_MASK);
	
	public static final KeyStroke VK_EXPORT = KeyStroke.getKeyStroke(KeyEvent.VK_E, 
			KeyEvent.CTRL_DOWN_MASK);
	
	public static final KeyStroke VK_CREATE = KeyStroke.getKeyStroke(KeyEvent.VK_N, 
			KeyEvent.CTRL_DOWN_MASK);
	
	public static final KeyStroke VK_EXECUTE = KeyStroke.getKeyStroke(KeyEvent.VK_R, 
			KeyEvent.CTRL_DOWN_MASK);
	
	public static final KeyStroke VK_KILL = KeyStroke.getKeyStroke(KeyEvent.VK_K, 
			KeyEvent.CTRL_DOWN_MASK);
	
	public static final KeyStroke VK_STOP = KeyStroke.getKeyStroke(KeyEvent.VK_P, 
			KeyEvent.CTRL_DOWN_MASK);

}
