package com.daviddev.j4cide.ui.base;

import java.io.IOException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.daviddev.j4cide.annotation.ExeceptionHandlerMaker;
import com.daviddev.j4cide.api.CodeSceneChild;
import com.daviddev.j4cide.core.Environment;
import com.daviddev.j4cide.model.FileExtensionType;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.ChildCodeEditor;
import com.daviddev.j4cide.ui.UiApplication;
import com.daviddev.j4cide.ui.UiCodeScene;
import com.daviddev.j4cide.ui.callback.TabbedPaneCloseCallback;
import com.daviddev.j4cide.util.ColorUtil;

import javax.swing.JTabbedPane;

@ExeceptionHandlerMaker
public class EditorPane extends JPanel implements HyperlinkListener, CodeSceneChild {

	private static final long serialVersionUID = 3315165494291402183L;

	private final UiCodeScene codeScene;
	
	private TabbedPaneCloseCallback tabbedCloseCallback;
	private JTabbedPane tabbedPane;
	
	public EditorPane(UiCodeScene codeScene) {

		this.codeScene = codeScene;
		tabbedCloseCallback = new TabbedPaneCloseCallback();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
	
	/*TODO:*/
	public void loadNewTab(FileTreeNode node) {
		
		if (node.getExtensionType() != FileExtensionType.C && node.getExtensionType() != FileExtensionType.H)
			return;
			
		ChildCodeEditor codePane_1 = null;
		try {
			codePane_1 = new ChildCodeEditor(node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon ic = (ImageIcon) node.getIcon();
		node.open();
		tabbedPane.addTab(node.getName(), ic, codePane_1, null);
		codePane_1.applyStyle(UiApplication.DEFAULT_STYLE);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
	    System.out.println("Hyperlink event: " + e.getEventType());
		if (e.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
			URL url = e.getURL();
			if (url==null) {
				UIManager.getLookAndFeel().provideErrorFeedback(null);
			}
			else {
				JOptionPane.showMessageDialog(this,
									"URL clicked:\n" + url);
			}
		}
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
