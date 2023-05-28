package com.daviddev.j4cide.ui;

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
	public static final String LINKED_ICON = "lkd1";
	public static final String WARNING_ICON = "warn1";
	public static final String SAVE_ICON = "sv";
	public static final String EXPORT_ICON = "exprt";
	public static final String STOP_ICON = "stp";
	public static final String EXECUTE_ICON = "exc";
	public static final String KILL_ICON = "kll";
	public static final String CONFIG_ICON = "cfg";
	public static final String EXPLORER_ICON = "expr";
	public static final String LOG_ICON = "lg";
	public static final String CPP2_ICON = "cpp2";
	
	public static final String LOG_WARN_ICON = "lwicn";
	public static final String LOG_INFO_ICON = "liicn";
	public static final String LOG_ERROR_ICON = "lerricn";
	
	public static void registerAllIcons() {

		if (!MAPPER.isEmpty())
			return;
		
		registerIcon(UNKNOWN_ICON, iconOf("unknown_16px.svg"));
		registerIcon(PROJECT_ICON, iconOf("project_16px.svg"));
		registerIcon(FOLDER_ICON, iconOf("folder_16px.svg"));
		registerIcon(MINGW_ICON, iconOf("mingw_16px.svg"));
		registerIcon(CFILE_ICON, iconOf("c2_16px.svg"));
		registerIcon(HFILE_ICON, iconOf("h_16px.svg"));
		registerIcon(ENV_ICON, iconOf("env_16px.svg"));
		registerIcon(WARNING_ICON, iconOf("warn_16px.svg"));
		registerIcon(LINKED_ICON, iconOf("linked_16px.svg"));
		registerIcon(SAVE_ICON, iconOf("save_16px.svg"));
		registerIcon(EXPORT_ICON, iconOf("export_16px.svg"));
		registerIcon(STOP_ICON, iconOf("stop_16px.svg"));
		registerIcon(EXECUTE_ICON, iconOf("execute_16px.svg"));
		registerIcon(KILL_ICON, iconOf("kill_16px.svg"));
		registerIcon(CONFIG_ICON, iconOf("config_16px.svg"));
		registerIcon(EXPLORER_ICON, iconOf("explorer_16px.svg"));
		registerIcon(LOG_ICON, iconOf("log_16px.svg"));
		registerIcon(CPP2_ICON, iconOf("cpp2_16px.svg"));
		registerIcon(LOG_WARN_ICON, iconOf("warn_16px.svg", 10, 10));

	}

	public static ImageIcon iconOf(@NotNull String iconName) {
		return new FlatSVGIcon(new File(Directories.ICONS, iconName));
	}
	
	public static ImageIcon iconOf(@NotNull String iconName, int width, int height) {
		return new FlatSVGIcon(new File(Directories.ICONS, iconName)).derive(width, height);
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
