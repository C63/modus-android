package c63.studio.fi.modus.core;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ModusModule.class})
@Singleton
public interface ModusAppComponent {
    public Context getContext();

    public AuthController getAuthController();

    public ModusApiService getApiService();
}
