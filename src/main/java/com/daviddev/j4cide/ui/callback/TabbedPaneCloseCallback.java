package com.daviddev.j4cide.ui.callback;

import java.util.function.BiConsumer;

import javax.swing.JTabbedPane;

import com.daviddev.j4cide.core.ApplicationContextManager;
import com.daviddev.j4cide.ui.dialog.UiSaveOrExitDialog;
import com.daviddev.j4cide.ui.model.CloseActionType;

public final class TabbedPaneCloseCallback implements BiConsumer<JTabbedPane, Integer> {

	@Override
	public void accept(JTabbedPane tabbedPane, Integer tabIndex) {
		CloseActionType actionType = UiSaveOrExitDialog.decide();
		ApplicationContextManager.getContextManager()
			.closeTabOfChildCodeEditor(actionType, tabIndex);
	}

}
