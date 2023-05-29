package com.daviddev.j4cide.ui.component;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.daviddev.j4cide.core.logger.LogLevel;
import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.ui.IconMapper;
import com.daviddev.j4cide.ui.UiApplication;

public class ConsoleTextArea extends JTextPane {
	
	private static final long serialVersionUID = -8903922692316227057L;

	public ConsoleTextArea(CideStyle cideStyle) {
		setFont(cideStyle.getSizedFont(12));
		setForeground(Color.red);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(UiApplication.bg());	
		setStyledDocument(new DefaultStyledDocument());
	}

	public void print(String text, LogLevel level) { 
		StyledDocument document = getStyledDocument();
        Style style = document.addStyle("CustomStyle", null);
        StyleConstants.setComponent(style, createStyledLabel(text, level.getColor(),
        		getIconByLogLevel(level)));
        try {
            document.insertString(document.getLength(), text, style);
            setCaretPosition(getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
	
	private JLabel createStyledLabel(String text, Color color, Icon icon) {
		JLabel label = new JLabel();
		label.setText(text);
		if (!text.isBlank()) {
			label.setIcon(icon);
		}
		label.setForeground(color);
		label.setFont(getFont());
		return label;
	}
	
	private Icon getIconByLogLevel(LogLevel level) {
		return IconMapper.icon(level.getAssociatedIcon());
	}
	
	public void println(String text, LogLevel level) {
		print(text.concat("\n"), level);
	}
	
	public void printBlankLine() {
		println("", LogLevel.INFO);
	}
	
	public void clear() {
		setText("");
	}
}
