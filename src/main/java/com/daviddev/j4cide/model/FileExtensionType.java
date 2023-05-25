package com.daviddev.j4cide.model;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import com.daviddev.j4cide.core.IconMapper;

public enum FileExtensionType {

	OTHER (IconMapper.UNKNOWN_ICON, SyntaxConstants.SYNTAX_STYLE_NONE),

	C (IconMapper.CFILE_ICON, SyntaxConstants.SYNTAX_STYLE_C),
	H (IconMapper.HFILE_ICON, SyntaxConstants.SYNTAX_STYLE_C),
	
	DIRECTORY (IconMapper.FOLDER_ICON, null),
	PROJECT (IconMapper.PROJECT_ICON, null);
	
	private String iconId;
	private String languageStyle;

	private FileExtensionType(String iconId, String languageStyle) {
		this.iconId = iconId;
		this.languageStyle = languageStyle;
	}
	
	public String getAssociatedIcon() {
		return this.iconId;
	}
	
	public String getLanguageStyle() {
		return languageStyle;
	}

}