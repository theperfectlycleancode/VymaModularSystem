package me.samuel.vyma.backend.core.vmodules.modules.logger;

/**
 * VLoggerMessage
 */
public class VLoggerMessage {

    /**
     * Message string array
     */
    private String[] message;

    /**
     * Is message send
     */
    private boolean send;

    /**
     * @param message VLoggerMessage constructor
     */
    public VLoggerMessage(String[] message) {
        this.message = message;
    }

    /**
     * Set message send status
     */
    public void send() {
        this.send = true;
    }

    /**
     * @return Is message send
     */
    public boolean isSend() {
        return this.send;
    }

    /**
     * @param message New message data
     */
    public void updateMessage(String[] message) {
        this.message = message;
    }

    /**
     * @return Message string array
     */
    public String[] getMessage() {
        return this.message;
    }
}
