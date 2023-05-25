package com.daviddev.j4cide.exception;

public final class FilteredException extends RuntimeException {

	private static final long serialVersionUID = 1696843949988816862L;

	public FilteredException(String nestedMessage, Exception cause) {
		super(exceptionMessage(nestedMessage, cause));
	}
	
	private static String exceptionMessage(String nestedMessage, Exception cause) {
		if (cause != null) {
			return String.format("%s [ CAUSE { %s: %s } ]", nestedMessage, cause.getClass()
					.getSimpleName(), cause.getMessage());
		}
		return String.format("%s [ CAUSE { UNKNWON: NO MESSAGE } ]", nestedMessage);
	}
	
	public static FilteredException iOException(Exception cause) {
		return new FilteredException("Não foi possível acessar esse arquivo, verifique a causa.", cause);
	}
	
}
