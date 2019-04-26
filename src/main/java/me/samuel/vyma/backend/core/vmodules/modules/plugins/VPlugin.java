package me.samuel.vyma.backend.core.vmodules.modules.plugins;

import me.samuel.vyma.backend.core.vmodules.VModule;
import me.samuel.vyma.pluginInterface.VPluginInterface;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

public class VPlugin extends VModule {

    private File pluginFile;

    private URLClassLoader urlClassLoader;

    private ServiceLoader<VPluginInterface> serviceLoader;

    /**
     * VModule base constructor
     *
     * @param name
     * @param version
     */
    public VPlugin(String name, double version, File file) {
        super(name, version);
        pluginFile = file;
    }

    @Override
    public void onLoad() {
        try {
            urlClassLoader = new URLClassLoader(new URL[]{pluginFile.toURI().toURL()});
            System.out.println(urlClassLoader.getURLs()[0]);
            serviceLoader = ServiceLoader.load(VPluginInterface.class, urlClassLoader);
            Iterator<VPluginInterface> apit = serviceLoader.iterator();
            System.out.println(serviceLoader.iterator().hasNext());
            while (apit.hasNext())
                System.out.println("plugin >> "+apit.next().getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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

    public void reload() {
        serviceLoader.reload();
    }

    public void onReload() {}
}
