package com.daviddev.j4cide.api;

public interface ExpandableInterfaceFactory {

	public enum InteractStateType { CLOSE, OPEN; }
	public enum InterfaceType { FILE_EXPLORER, CONSOLE; }
	
	void changeState(InteractStateType stateType, InterfaceType interfaceType);
	
}
