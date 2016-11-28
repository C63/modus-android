package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import c63.studio.fi.modus.core.repositories.TokenRepository;
import okhttp3.OkHttpClient;

public class ModusHttp {

    @NonNull
    private TokenRepository tokenRepository;

    public ModusHttp(@NonNull TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @NonNull
    public OkHttpClient build() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new AuthInterceptor(this.tokenRepository));

        builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }
}
