package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import c63.studio.fi.modus.core.authentication.CredentialsController;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AppModule {

    @NonNull
    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(@NonNull final CredentialsController credentialsController) {
        ModusHttp modusHttp = new ModusHttp(credentialsController);
        return modusHttp.build();
    }
}
