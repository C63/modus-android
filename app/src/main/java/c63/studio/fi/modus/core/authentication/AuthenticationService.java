package c63.studio.fi.modus.core.authentication;

import android.support.annotation.NonNull;

import c63.studio.fi.modus.core.authentication.Model.AccessToken;
import io.reactivex.Observable;
import retrofit2.http.POST;

interface AuthenticationService {

    @NonNull
    @POST("accounts/get-token")
    Observable<AccessToken> signIn(String email, String password);

    @NonNull
    @POST("accounts/register")
    Observable<AccessToken> signUp(String email, String password, String name);
}
