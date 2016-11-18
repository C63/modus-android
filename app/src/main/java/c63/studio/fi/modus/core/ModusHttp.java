package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import c63.studio.fi.modus.core.authentication.AuthenticationInterceptor;
import c63.studio.fi.modus.core.authentication.CredentialsController;
import okhttp3.OkHttpClient;

public class ModusHttp {

    @NonNull
    private CredentialsController credentialsController;

    public ModusHttp(@NonNull final CredentialsController credentialsController) {
        this.credentialsController = credentialsController;
    }

    @NonNull
    public OkHttpClient build() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AuthenticationInterceptor(credentialsController));

        builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }
}
