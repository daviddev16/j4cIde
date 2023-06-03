package com.daviddev.j4cide.core;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class ExecutionWorker implements Runnable {

	public static final AtomicInteger ID = new AtomicInteger();
	
    private volatile boolean state = false;
    private volatile Thread worker;

    private final boolean updatable;
    private final boolean daemon;

    private final String name;

    public ExecutionWorker(String name, boolean updatable, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
        this.updatable = updatable;
    }

    public synchronized void changeState(boolean newState) {
        if (newState != state)
            state = newState;
    }

    public void start() {
        if (!state) {
            state = true;
            worker = new Thread(this, name);
            worker.setDaemon(daemon);
            worker.start();
        }
    }

    public void stop() {
        if (state) {
            state = false;
            interrupt(worker);
            worker = null;
        }
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

}
