package c63.studio.fi.modus.core;


import android.support.annotation.NonNull;

import java.io.IOException;

import c63.studio.fi.modus.core.repositories.TokenRepository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @NonNull
    private TokenRepository tokenRepository;

    public AuthInterceptor(@NonNull final TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();

        addTokenAuthorizationHeader(builder);
        return chain.proceed(builder.build());
    }

    private void addTokenAuthorizationHeader(@NonNull final Request.Builder builder) {
        String token = tokenRepository.getToken();
        if (token != null) {
            builder.header(AUTHORIZATION_HEADER, String.format("Token %s", token));
        }
    }
}
