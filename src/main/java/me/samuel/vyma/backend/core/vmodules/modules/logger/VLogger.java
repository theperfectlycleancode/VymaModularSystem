package me.samuel.vyma.backend.core.vmodules.modules.logger;

import me.samuel.vyma.backend.core.vmodules.VModule;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * VLogger Logger base class
 */
public class VLogger extends VModule {

    /**
     * Loggers print stream
     */
    private PrintStream outStream;

    /**
     * Should print message prefix
     */
    private boolean printPrefix;

    /**
     * Should print new line on the end if each message
     */
    private boolean printNewLine;

    /**
     * Message Prefix
     */
    private String prefix;

    /**
     * Messages to send
     */
    private List<VLoggerMessage> messageQueue;

    /**
     * Messages that got send
     */
    private List<VLoggerMessage> sendMessages;

    /**
     * VModule base constructor
     *
     * @param name Logger Name
     * @param version Logger version
     */
    public VLogger(String name, double version) {
        super(name, version);
        this.messageQueue = new ArrayList<>();
        this.sendMessages = new ArrayList<>();
        printNewLine = true;
        printPrefix = false;
    }

    /**
     * @param message Log message
     */
    public void log(VLoggerMessage message) {
        messageQueue.add(message);
    }

    /**
     * Works queue
     */
    public void update() {
        List<VLoggerMessage> toDelete = new ArrayList<>();

        for(VLoggerMessage message : messageQueue) {
            for(String string : message.getMessage()) {
                print(string);
            }
            message.send();
            sendMessages.add(message);
            toDelete.add(message);
        }

        for(VLoggerMessage message : toDelete) {
            messageQueue.remove(message);
        }
    }

    /**
     * @return send messages
     */
    public List<VLoggerMessage> getSendMessages() {
        return this.sendMessages;
    }

    /**
     * @return message queue
     */
    public List<VLoggerMessage> getMessageQueue() {
        return this.messageQueue;
    }

    /**
     * enables logger to print a new line on each message
     */
    public void enableNewLine() {
        this.printNewLine = true;
    }

    /**
     * disables logger from printing a new line on each message
     */
    public void disableNewLine() {
        this.printNewLine = false;
    }

    /**
     * @param stream Printers output stream
     */
    public void setStream(PrintStream stream) {
        this.outStream = stream;
    }

    /**
     * @return Printers output stream
     */
    public PrintStream getStream() {
        return this.outStream;
    }

    /**
     * @param msg Print message
     */
    public void print(String msg) {
        if(outStream == null)
            return;
        if(printNewLine)
            msg+=System.lineSeparator();
        if(printPrefix)
            printPrefix(prefix, msg);
        else
            outStream.print(msg);

    }

    /**
     * @param syntax Message syntax
     * @param msg Message
     */
    private void printPrefix(String syntax, String msg) {
        outStream.print(syntax+" "+msg);
    }

    /**
     * Updates and enables prefix
     *
     * @param prefix Prefix
     */
    public void updatePrefix(String prefix) {
        if(this.printPrefix)
            this.printPrefix = true;
        this.prefix = prefix;
    }

    /**
     * Disables prefix
     */
    public void disablePrefix() {
        this.printPrefix = false;
    }

    /**
     * @return Prefix
     */
    public String getPrefix() {
        return this.prefix;
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
