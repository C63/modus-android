package c63.studio.fi.modus.core;

import android.content.Context;

import javax.inject.Singleton;

import c63.studio.fi.modus.core.authentication.AuthenticationController;
import c63.studio.fi.modus.core.authentication.AuthenticationModule;
import dagger.Component;
import okhttp3.OkHttpClient;

@Component(
        dependencies = {ModusModule.class},
        modules = {AuthenticationModule.class, AppModule.class}
)
@Singleton
interface AppComponent {

    AuthenticationController getAuthenticationController();

    OkHttpClient getOkHttpClient();

    Context getContext();

}
