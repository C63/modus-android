package c63.studio.fi.modus.core.authentication;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;

public class AuthenticationController {
    @NonNull
    private final AuthenticationService authenticationService;

    @NonNull
    private final CredentialsController credentialsController;

    public AuthenticationController(@NonNull final AuthenticationService authenticationService,
                                    @NonNull final CredentialsController credentialsController) {
        this.authenticationService = authenticationService;
        this.credentialsController = credentialsController;
    }

    public void signIn(String email, String password) throws NoSuchElementException {
        authenticationService.signIn(email, password)
                .singleOrError()
                .subscribe(credentialsController::setToken, Throwable::printStackTrace);
    }

    public void signUp(String email, String password, String name) {
        authenticationService.signUp(email, password, name)
                .singleOrError()
                .subscribe(credentialsController::setToken, Throwable::printStackTrace);
    }

    public void signOut(){
        credentialsController.signOut();
    }

    public boolean isLoggedIn(){
        return credentialsController.hasSavedCredentials();
    }
}
