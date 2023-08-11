package com.daviddev.j4cide.ui.handler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.daviddev.j4cide.api.GenericAction;
import com.daviddev.j4cide.ui.action.CreateAction;
import com.daviddev.j4cide.ui.action.DeleteAction;
import com.daviddev.j4cide.ui.action.EmptyAction;
import com.daviddev.j4cide.ui.action.ExecuteAction;
import com.daviddev.j4cide.ui.action.ExportAction;
import com.daviddev.j4cide.ui.action.KillAction;
import com.daviddev.j4cide.ui.action.OpenConfigAction;
import com.daviddev.j4cide.ui.action.OpenConsoleAction;
import com.daviddev.j4cide.ui.action.OpenExplorerAction;
import com.daviddev.j4cide.ui.action.OpenInspectorAction;
import com.daviddev.j4cide.ui.action.SaveAction;
import com.daviddev.j4cide.ui.action.StopAction;
import com.daviddev.j4cide.util.Validator;

public final class ActionsHandler {
	
	private static final Map<Class<?>, GenericAction> ACTIONS = Collections.synchronizedMap(new HashMap<>());
	
	public static final EmptyAction EMPTY_ACTION = new EmptyAction();
	
	public static void registerAllActions() {		
		register(EMPTY_ACTION);
		register(new SaveAction());
		register(new ExportAction());
		register(new CreateAction());
		register(new ExecuteAction());
		register(new KillAction());
		register(new StopAction());
		register(new OpenConfigAction());
		register(new ExportAction());
		register(new CreateAction());
		register(new DeleteAction());
		register(new OpenConsoleAction());
		register(new OpenExplorerAction());
		register(new OpenInspectorAction());
	}
	
	public static void register(GenericAction action) {
		Validator.checkIsNotNull(action, "action");
		if (!ACTIONS.containsKey(action.getClass()))
			ACTIONS.put(action.getClass(), action);
	}
	
	public static boolean unregister(Class<?> clazz) {
		Validator.checkIsNotNull(clazz, "clazz");
		return ACTIONS.remove(clazz) != null;
	}
	
	public static GenericAction actionOf(Class<?> clazz) {
		Validator.checkIsNotNull(clazz, "clazz");
		return ACTIONS.getOrDefault(clazz, EMPTY_ACTION);
	}
	
}
