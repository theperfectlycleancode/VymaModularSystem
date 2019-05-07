package me.samuel.vyma.backend.core.vmodules.modules.exceptionHandler;

import me.samuel.vyma.backend.core.vmodules.VModule;
import me.samuel.vyma.backend.core.vmodules.modules.logger.loggers.VExceptionLogger;

public abstract class VExceptionHandler extends VModule implements Thread.UncaughtExceptionHandler {

    /**
     * Should the handler log exceptions
     */
    private boolean shouldLog;

    private VExceptionLogger exceptionLogger;

    /**
     * VModule base constructor
     *
     * @param name VExceptionHandler module name
     * @param version version
     */
    public VExceptionHandler(String name, double version) {
        super(name, version);
        this.shouldLog = true;

        this.exceptionLogger = new VExceptionLogger();
        this.exceptionLogger.setStream(System.out);
    }

    abstract void onException(Thread thread, Throwable throwable);

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        this.exceptionLogger.onException(thread, throwable);
        onException(thread, throwable);
    }

    /**
     * Enables exceptionLogger
     */
    public void enableLogger() {
        this.shouldLog = true;
    }

    /**
     * Disables exceptionLogger
     */
    public void disableLogger() {
        this.shouldLog = false;
    }

    public VExceptionLogger getExceptionLogger() {
        return this.exceptionLogger;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onPostLoad() {

    }

    @Override
    public void onUnload() {

    }

    @Override
    public void onPostUnload() {

    }
}
