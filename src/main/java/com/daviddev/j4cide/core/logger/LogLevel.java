package com.daviddev.j4cide.core.logger;

import java.awt.Color;

import com.daviddev.j4cide.ui.IconMapper;

public enum LogLevel {
	
	INFO (IconMapper.LOG_INFO_ICON, Color.decode("#f9f9f9")), 
	ERROR (IconMapper.LOG_ERROR_ICON, Color.decode("#e55765")), 
	WARNING (IconMapper.LOG_WARN_ICON, Color.decode("#ffaf0f")),
	SYNC (IconMapper.LOG_SYNC_ICON, Color.decode("#d3c1f9"));
	
	private String iconId;
	private Color color;
	
	private LogLevel(String iconId, Color color) {
		this.iconId = iconId;
		this.color = color;
	}
	
	public String getAssociatedIcon() {
		return this.iconId;
	}
	
	public Color getColor() {
		return color;
	}
	
}
