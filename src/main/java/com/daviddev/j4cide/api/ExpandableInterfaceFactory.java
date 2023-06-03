package com.daviddev.j4cide.api;

public interface ExpandableInterfaceFactory {

	public enum InteractStateType { CLOSE, OPEN; }
	public enum InterfaceType { FILE_EXPLORER, CONSOLE, INSPECTOR; }
	
	void changeState(InteractStateType stateType, InterfaceType interfaceType);
	
}
