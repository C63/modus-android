package c63.studio.fi.modus.core;

import android.support.multidex.MultiDexApplication;

public class ModusApp extends MultiDexApplication {

    private static ModusApp instance;

    private ModusAppComponent modusAppComponent;

    public ModusApp() {
    }

    public static ModusApp getInstance() {
        return instance;
    }

    public ModusAppComponent getModusAppComponent() {
        return modusAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ModusApp.instance = this;
        modusAppComponent = DaggerModusAppComponent.builder()
                .modusModule(new ModusModule(this)).build();
    }
}
