package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import c63.studio.fi.modus.core.models.AccessToken;
import c63.studio.fi.modus.core.repositories.TokenRepository;

public class AuthController {
    @NonNull
    private final AuthService authService;

    @NonNull
    private final TokenRepository tokenRepository;

    public AuthController(@NonNull AuthService authService, @NonNull TokenRepository tokenRepository) {
        this.authService = authService;
        this.tokenRepository = tokenRepository;
    }

    public void signIn(String email, String password) {
        authService.signIn(email, password)
                .singleOrError()
                .map(AccessToken::getAccessToken)
                .subscribe(tokenRepository::setToken, Throwable::printStackTrace);
    }

    public void signUp(String email, String password, String name) {
        authService.signUp(email, password, name)
                .singleOrError()
                .map(AccessToken::getAccessToken)
                .subscribe(tokenRepository::setToken, Throwable::printStackTrace);
    }

    public void signOut() {
        tokenRepository.signOut();
    }

    public boolean isLoggedIn() {
        return tokenRepository.getToken() != null;
    }
}
