package com.daviddev.j4cide;

import java.io.IOException;

import com.daviddev.j4cide.ui.UiApplication;

public class Main {

	public static void main(String[] args) {
		try {
			UiApplication uiApplication = new UiApplication();
			main(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
