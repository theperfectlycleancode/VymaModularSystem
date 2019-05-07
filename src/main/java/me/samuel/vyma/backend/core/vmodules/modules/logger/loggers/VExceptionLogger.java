package me.samuel.vyma.backend.core.vmodules.modules.logger.loggers;

import me.samuel.vyma.backend.core.vmodules.modules.logger.VLogger;

public class VExceptionLogger extends VLogger {


    /**
     * VModule base constructor
     */
    public VExceptionLogger() {
        super("VExceptionLogger", 1.0);
        this.updatePrefix("EXCEPTION");
    }
}
