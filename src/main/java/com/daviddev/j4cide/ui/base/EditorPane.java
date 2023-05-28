package com.daviddev.j4cide.ui.base;


import java.util.Objects;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.daviddev.j4cide.annotation.ExeceptionHandlerMaker;
import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.model.FileExtensionType;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.ChildCodeEditor;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.callback.TabbedPaneCloseCallback;
import com.daviddev.j4cide.util.ColorUtil;

@ExeceptionHandlerMaker
public class EditorPane extends JPanel implements CodeSceneChild {

	public static final FileExtensionType[] AVAILABLE_EXTENSIONS =
		{
				FileExtensionType.C,
				FileExtensionType.H,
				FileExtensionType.OTHER
		};

	private static final long serialVersionUID = 3315165494291402183L;

	private final UiCodeScene codeScene;

	private TabbedPaneCloseCallback tabbedCloseCallback;
	private JTabbedPane tabbedPane;

	public EditorPane(UiCodeScene codeScene) {

		this.codeScene = codeScene;
		tabbedCloseCallback = new TabbedPaneCloseCallback();

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		initializeTabbedPane(tabbedPane);

		GroupLayout gl_contentPane = new GroupLayout(this);

		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
				);

		setLayout(gl_contentPane);
		setBorder(null);
	}

	private void initializeTabbedPane(JTabbedPane tabbedPane) {
		tabbedPane.setBackground(ColorUtil.darker(UiApplication.bg(), 0.01f));
		tabbedPane.putClientProperty("JTabbedPane.tabClosable", true);
		tabbedPane.putClientProperty("JTabbedPane.tabCloseCallback", tabbedCloseCallback);
		tabbedPane.setOpaque(true);
		tabbedPane.setBorder(null);
	}

	public void newTabOf(FileTreeNode treeNode) {
		Objects.requireNonNull(treeNode, "treeNode");

		if (!isLoadable(treeNode.getExtensionType()))
			return;

		try {
			int tabIndex = getTabbedPane().getTabCount();
			ChildCodeEditor codeEditor = new ChildCodeEditor(treeNode);
			ImageIcon imageIcon = (ImageIcon) treeNode.getIcon();
			getTabbedPane().insertTab(treeNode.getName(), imageIcon, codeEditor, null, tabIndex);
			codeEditor.applyStyle(UiApplication.DEFAULT_STYLE);
			treeNode.open();
			getTabbedPane().setSelectedIndex(tabIndex);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isLoadable(FileExtensionType extensionType) {
		for (FileExtensionType availableType : AVAILABLE_EXTENSIONS) {
			if (availableType.equals(extensionType)) {
				return true;
			}
		}
		return false;
	}

	public boolean focusOnTextArea(RSyntaxTextArea textArea) {
		return textArea.requestFocusInWindow();
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	@Override
	public UiCodeScene getCodeScene() {
		return codeScene;
	}
}
