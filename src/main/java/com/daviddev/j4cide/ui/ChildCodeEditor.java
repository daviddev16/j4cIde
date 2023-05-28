package com.daviddev.j4cide.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ToolTipManager;

import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.daviddev.j4cide.api.Styleable;
import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.model.FileTreeNode;
import com.daviddev.j4cide.ui.base.EditorPane;
import com.daviddev.j4cide.util.ColorUtil;
import com.daviddev.j4cide.util.IOUtil;

public class ChildCodeEditor extends Container implements Styleable {

	private static final long serialVersionUID = 1L;

	public static final LanguageSupportFactory LSF = LanguageSupportFactory.get();

	private RSyntaxTextArea textArea;
	private RTextScrollPane scrollPane;
	private CideStyle cideStyle;

	private final FileTreeNode treeNodeOwner;

	public ChildCodeEditor(FileTreeNode treeNodeOwner) throws IOException {
		this.treeNodeOwner = treeNodeOwner;

		setLayout(new BorderLayout());
		textArea = new RSyntaxTextArea(20, 60);
		textArea.addKeyListener(new KeyAdapter() {

		});
		scrollPane = new RTextScrollPane(textArea, true);
		scrollPane.getGutter().setBorder(null);
		scrollPane.setBorder(null);

		initializeTextArea(textArea);
		loadFromTreeNode();
		add(scrollPane);
	}

	private void initializeTextArea(RSyntaxTextArea textArea) {
		textArea.setCaretPosition(0);
		textArea.requestFocusInWindow();
		textArea.setMarkOccurrences(true);
		textArea.setCodeFoldingEnabled(true);
		textArea.setTabsEmulated(true);
		textArea.setTabSize(4);
		textArea.setCodeFoldingEnabled(true);
		ToolTipManager.sharedInstance().registerComponent(textArea);
	}

	private void loadFromTreeNode() throws IOException {
		String syntaxLanguage = getTreeNodeOwner().getExtensionType().getLanguageStyle();
		String filePath = getTreeNodeOwner().getFilePath();
		String contentOfFile = IOUtil.read(filePath);

		final LanguageSupport support = LSF.getSupportFor(syntaxLanguage);
		support.install(textArea);
		LSF.register(textArea);

		textArea.setSyntaxEditingStyle(syntaxLanguage);
		textArea.setText(contentOfFile);
	}

	@Deprecated(forRemoval = false)
	private void changeCodeThemeByCideStyle(CideStyle cideStyle) {
		try {
			Theme theme = Theme.load(new FileInputStream(cideStyle.getCodeStyleFile()));
			theme.bgColor = ColorUtil.darker(UiApplication.bg(), 0.2f);
			theme.apply(textArea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Deprecated(forRemoval = false)
	private void changeFontByCideStyle(CideStyle cideStyle) {
		textArea.setFont(cideStyle.getSizedFont(11));
		textArea.revalidate();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		if (visible && getParent() instanceof EditorPane)
			((EditorPane)getParent()).focusOnTextArea(textArea);

	}

	public void saveToFile() {
		try {
			IOUtil.write(treeNodeOwner.getFilePath(), textArea.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void applyStyle(CideStyle cideStyle) {
		this.cideStyle = cideStyle;
		changeCodeThemeByCideStyle(cideStyle);
		changeFontByCideStyle(cideStyle);
	}

	public FileTreeNode getTreeNodeOwner() {
		return treeNodeOwner;
	}

	@Override
	public CideStyle getCideStyle() {
		return cideStyle;
	}

}
