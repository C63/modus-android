package c63.studio.fi.modus.core;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class ModusApp extends MultiDexApplication {

    private static AppComponent appComponent;
    private static ModusApp instance;

    public ModusApp() {

    }

    public static Context appContext() {
        return appComponent.getContext();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        ModusApp.appComponent = appComponent;
    }

    public static ModusApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ModusApp.instance = this;
        appComponent = DaggerAppComponent.builder()
                .modusModule(new ModusModule(this))
                .build();
    }
}
