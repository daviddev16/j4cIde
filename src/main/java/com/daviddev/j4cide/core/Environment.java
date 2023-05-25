package com.daviddev.j4cide.core;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;

import com.daviddev.j4cide.external.FlatSVGIcon;

@Deprecated
public class Environment {

	//public static final Color BACKGROUND = Color.decode("#303234");
	
	public static Environment instance;

	/*private final List<Styleable> styleableComponents = Collections.synchronizedList(new ArrayList<>());
	private final Map<Integer, FlatSVGIcon> svgIcons = Collections.synchronizedMap(new HashMap<>()); 
	
	public Environment(File profileFile) throws InstanceAlreadyExistsException {
		
		if (instance != null)
			throw new InstanceAlreadyExistsException(Environment.class.getSimpleName());
		
		instance = this;

		registerSVGIcon(Icons.CLOSE_16PX, createFlatSVGIcon(profileFile, "icons/close_16px.svg"));
	}
	
	public FlatSVGIcon createFlatSVGIcon(File profileFile, String iconName) {
		return new FlatSVGIcon(new File(profileFile, iconName));
	}
	
	public static void unregisterStyleableComponent(Styleable styleable) {
		getInstance().getStyleableComponents().remove(styleable);
	}
	
	public static void registerStyleableComponent(Styleable styleable) {
		if (!getInstance().getStyleableComponents().contains(styleable))
			getInstance().getStyleableComponents().add(styleable);
	}
	
	public static FlatSVGIcon getSVGIcon(Integer iconId) {
		return getInstance().getSVGIcons().getOrDefault(iconId, null);
	}
	
	public static void registerSVGIcon(Integer iconId, FlatSVGIcon svgIcon) {
		getInstance().getSVGIcons().putIfAbsent(iconId, svgIcon);
	}
	
	private Map<Integer, FlatSVGIcon> getSVGIcons() {
		return svgIcons;
	}
	
	private List<Styleable> getStyleableComponents() {
		return styleableComponents;
	}
	
	public static Environment getInstance() {
		return instance;
	}*/
	
}
