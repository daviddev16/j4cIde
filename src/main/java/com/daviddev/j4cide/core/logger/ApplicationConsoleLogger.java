package com.daviddev.j4cide.core.logger;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.daviddev.j4cide.core.ApplicationContextManager;

/* TODO: */
public final class ApplicationConsoleLogger {

	private static final String CONSOLE_FORMAT = "[%s] [%s/%s]: %s";

	private ZoneId zoneId;
	private DateTimeFormatter formatter;

	public ApplicationConsoleLogger() {
		zoneId = ZoneId.of("America/Sao_Paulo");
		formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
	}

	public void log(ApplicationContextManager contextManager, LogLevel level, String message) {
		Instant instant = Instant.now();
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
		String formattedDateTime = formatter.format(zonedDateTime);
		String threadName = Thread.currentThread().getName();
		String consoleText = String.format(CONSOLE_FORMAT, formattedDateTime, 
				threadName, level.name(), message);
		ApplicationContextManager.getContextManager()
		.getConsoleArea().println(consoleText, level);
	}

	public void space(int times) {
		for (int i = 0; i < ((times < 1) ? 1 : times); i++) {
			ApplicationContextManager.getContextManager()
			.getConsoleArea().printBlankLine();
		}
	}

	public void clear() {
		ApplicationContextManager.getContextManager()
		.getConsoleArea().clear();
	}
	
	public void log(LogLevel level, String message) {
		log(ApplicationContextManager.getContextManager(), 
				level, message);
	}

	public void info(String message) {
		log(LogLevel.INFO, message);
	}

	public void error(String message) {
		log(LogLevel.ERROR, message);
	}

	public void warn(String message) {
		log(LogLevel.WARNING, message);
	}

}
