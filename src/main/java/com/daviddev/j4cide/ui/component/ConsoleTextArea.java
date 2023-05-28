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
	
	public void print(String text, Color color) { 
		StyledDocument document = getStyledDocument();
        Style style = document.addStyle("CustomStyle", null);
        Icon icon = IconMapper.icon(IconMapper.LOG_WARN_ICON);
        StyleConstants.setComponent(style, createStyledLabel(text, color, icon));
       
        
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
		label.setFont(getFont());
		return label;
	}
	
	public void println(String text, Color color) {
		print(text.concat("\n"), color);
	}
	
	public void printBlankLine() {
		println("", Color.WHITE);
	}
	
	public void clear() {
		setText("");
	}
}
