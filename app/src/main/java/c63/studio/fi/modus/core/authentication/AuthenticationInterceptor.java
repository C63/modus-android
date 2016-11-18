package c63.studio.fi.modus.core.authentication;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @NonNull
    private CredentialsController credentialsController;

    public AuthenticationInterceptor(@NonNull final CredentialsController credentialsController) {
        this.credentialsController = credentialsController;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();

        addTokenAuthorizationHeader(builder);
        return chain.proceed(builder.build());
    }

    private void addTokenAuthorizationHeader(@NonNull final Request.Builder builder) {
        String token = credentialsController.getCredentials();
        if (token != null) {
            builder.header(AUTHORIZATION_HEADER, String.format("Token %s", token));
        }
    }
}
