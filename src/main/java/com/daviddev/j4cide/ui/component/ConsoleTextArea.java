package com.daviddev.j4cide.ui.component;

import java.awt.Color;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.daviddev.j4cide.model.CideStyle;
import com.daviddev.j4cide.ui.UiApplication;


public class ConsoleTextArea extends JTextPane {
	
	private static final long serialVersionUID = -8903922692316227057L;

	public ConsoleTextArea(CideStyle cideStyle) {
		setFont(cideStyle.getSizedFont(12));
		setForeground(Color.red);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(UiApplication.bg());
		
		blankLine();
		appendDateText("⚠️ Carregando...", Color.yellow);
		appendDateText("⚠️ ok!", Color.GREEN);
	}
	
	public void appendDateText(String text, Color color) {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withLocale(new Locale("pt", "BR"));

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        String formattedDateTime = formatter.format(zonedDateTime);
        
        appendText(formattedDateTime + " -> " + text, color);
    }
	
	public void blankLine() {
		appendText("", Color.white);
	}
	
	public void appendText(String text, Color color) {
        StyledDocument document = getStyledDocument();
        Style style = document.addStyle("CustomStyle", null);
        StyleConstants.setForeground(style, color);

        try {
            document.insertString(document.getLength(), text+"\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
