package me.samuel.vyma.backend.core.vmodules.modules.plugins;

import me.samuel.vyma.backend.core.vmodules.VModule;
import me.samuel.vyma.pluginInterface.VPluginInterface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipFile;

public class VPlugin extends VModule {

    private File pluginFile;

    private Class<?> pluginClass;

    private VPluginInterface pluginInterfaceInstance;

    private Thread pluginThread;
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
            String mainClass = null;
            ZipFile zipFile = new ZipFile(pluginFile);
            InputStream is = zipFile.getInputStream(zipFile.getEntry("app.yml"));
            Properties config = new Properties();
            config.load(is);
            mainClass = config.getProperty("main");
            ClassLoader l = URLClassLoader.newInstance(new URL[]{pluginFile.toURI().toURL()}, getClass().getClassLoader());
            Class<?> clazz = l.loadClass(mainClass);
            this.pluginClass = clazz;
            AtomicReference<VPluginInterface> plugin = new AtomicReference<>();
            this.pluginThread = new Thread(() -> {
                try {
                    plugin.set((VPluginInterface) clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                plugin.get().load();
            });
            this.pluginInterfaceInstance = plugin.get();
            this.pluginThread.start();
            this.pluginThread.join(2000);
            System.out.println(plugin.get().getName() + " "+ plugin.get().getVersion());

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPostLoad() {

    }

    @Override
    public void onUnload() {
        pluginInterfaceInstance.unload();
    }

    @Override
    public void onPostUnload() {

    }

    public void reload() {}

    public void onReload() {}
}
