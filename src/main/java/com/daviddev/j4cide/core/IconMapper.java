package com.daviddev.j4cide.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.jetbrains.annotations.NotNull;

import com.daviddev.j4cide.external.FlatSVGIcon;
import com.daviddev.j4cide.util.Directories;
import com.daviddev.j4cide.util.Validator;

public final class IconMapper {

	public static final Map<String, ImageIcon> MAPPER = new HashMap<>();

	public static final String UNKNOWN_ICON = "<unknown>";
	
	public static final String PROJECT_ICON = "proj-icn";
	public static final String FOLDER_ICON = "fldr";
	public static final String MINGW_ICON = "mingw";
	public static final String CFILE_ICON = "c";
	public static final String HFILE_ICON = "h";
	public static final String ENV_ICON = "env";
	
	public static void registerAllIcons() {
		registerIcon(UNKNOWN_ICON, iconOf("unknown_16px.svg"));
		registerIcon(PROJECT_ICON, iconOf("project_16px.svg"));
		registerIcon(FOLDER_ICON, iconOf("folder_16px.svg"));
		registerIcon(MINGW_ICON, iconOf("mingw_16px.svg"));
		registerIcon(CFILE_ICON, iconOf("c2_16px.svg"));
		registerIcon(HFILE_ICON, iconOf("h_16px.svg"));
		registerIcon(ENV_ICON, iconOf("env_16px.svg"));
	}

	public static ImageIcon iconOf(@NotNull String iconName) {
		return new FlatSVGIcon(new File(Directories.ICONS, iconName));
	}

	public static void registerIcon(@NotNull String iconId, ImageIcon icon) {
		Validator.checkIsNotNull(iconId, "iconId");
		Validator.checkIsNotNull(icon, "icon");
		MAPPER.put(iconId, icon);
	}
	
	@NotNull
	public static ImageIcon icon(@NotNull String iconId) {
		Validator.checkIsNotNull(iconId, "iconId");
		ImageIcon icon = MAPPER.get(iconId);
		return (icon != null) ? icon : unknown();
	}

	@NotNull
	public static ImageIcon unknown() {
		return MAPPER.get(UNKNOWN_ICON);
	}

}
