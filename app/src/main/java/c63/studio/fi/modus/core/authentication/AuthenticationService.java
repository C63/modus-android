package c63.studio.fi.modus.core.authentication;

import io.reactivex.Observable;

interface AuthenticationService {

    Observable<String> signIn(String email, String password);

    Observable<String> signUp(String email, String password, String name);
}
