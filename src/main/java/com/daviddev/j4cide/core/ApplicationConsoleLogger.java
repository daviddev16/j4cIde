package com.daviddev.j4cide.core;

import java.awt.Color;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/* TODO: */
public final class ApplicationConsoleLogger {

	ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

	public void log(ApplicationContextManager contextManager, String logLevel, String message, Color color) {
		Instant instant = Instant.now();
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
		String formattedDateTime = formatter.format(zonedDateTime);
		String threadName = Thread.currentThread().getName();
		String consoleText = String.format("[%s] [%s/%s]: %s", formattedDateTime, threadName, logLevel, message);
		ApplicationContextManager.getContextManager().getConsoleUI()
		.area.println(consoleText, color);
	}

	public void info(String message) {
		log(ApplicationContextManager.getContextManager(), 
				"INFO", message, Color.WHITE);
	}

	public void error(String message) {
		log(ApplicationContextManager.getContextManager(), 
				"ERROR", message, Color.RED);
	}

	public void warn(String message) {
		log(ApplicationContextManager.getContextManager(), 
				"WARN", message, Color.YELLOW);
	}

}
