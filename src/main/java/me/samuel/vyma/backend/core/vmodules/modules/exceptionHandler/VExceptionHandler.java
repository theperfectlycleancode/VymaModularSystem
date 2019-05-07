package me.samuel.vyma.backend.core.vmodules.modules.exceptionHandler;

import me.samuel.vyma.backend.core.vmodules.VModule;

public abstract class VExceptionHandler extends VModule {


    /**
     * VModule base constructor
     *
     * @param name
     * @param version
     */
    public VExceptionHandler(String name, double version) {
        super(name, version);
    }

    public abstract void onException(Exception e);

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
