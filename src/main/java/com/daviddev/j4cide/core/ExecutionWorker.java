package com.daviddev.j4cide.core;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ExecutionWorker implements Runnable {

	public static final AtomicInteger ID = new AtomicInteger();

	private volatile boolean state = false;
	private volatile Thread worker;

	private final boolean updatable;
	private final boolean daemon;

	private final String name;

	private boolean disabled = false;

	public ExecutionWorker(String name, boolean updatable, boolean daemon) {
		this.name = name;
		this.daemon = daemon;
		this.updatable = updatable;
	}

	public synchronized void changeState(boolean newState) {
		if (newState != state && !disabled)
			state = newState;
	}

	public void start() {
		if (!state && !disabled) {
			state = true;
			worker = new Thread(this, name);
			worker.setDaemon(daemon);
			worker.start();
		}
	}

	public void stop() {
		if (state && !disabled) {
			state = false;
			interrupt(worker);
			worker = null;
		}
	}
	
	public void markAsDisabled() {
		if (!state || 
				!worker.isAlive()) disabled = true;
	}

	private void interrupt(Thread thread) {
		try {
			thread.interrupt();
		} catch (Exception e) {/* ignore */}
	}

	@Override
	public void run() {
		onBegin();
		while (state && updatable) {
			onUpdate();
		}
		onStop();
	}

	protected void onBegin() {}
	protected void onUpdate() {}
	protected void onStop() {}
	
	public boolean isRunning() {
		return state;
	}
	
	public boolean isDisabled() {
		return disabled;
	}

}
