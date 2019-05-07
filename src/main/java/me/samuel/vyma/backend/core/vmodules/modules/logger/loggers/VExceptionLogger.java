package me.samuel.vyma.backend.core.vmodules.modules.logger.loggers;

import me.samuel.vyma.backend.core.vmodules.modules.logger.VLogger;
import me.samuel.vyma.backend.core.vmodules.modules.logger.VLoggerMessage;

public class VExceptionLogger extends VLogger {


    /**
     * VModule base constructor
     */
    public VExceptionLogger() {
        super("VExceptionLogger", 1.0);
        this.updatePrefix("EXCEPTION");
    }

    public void onException(Thread thread, Throwable throwable) {
        String[] message = new String[] {
                "Thread: "+thread.getName()+" : "+thread.getId(),
                "Exception: "+throwable.toString(),
                "Message: "+throwable.getMessage()
        };
        VLoggerMessage loggerMessage = new VLoggerMessage(message);
        this.log(loggerMessage);
        throwable.printStackTrace(this.getStream());
    }
}
