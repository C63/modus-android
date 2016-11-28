package c63.studio.fi.modus.core;

import android.support.annotation.NonNull;

import c63.studio.fi.modus.core.models.AccessToken;
import io.reactivex.Observable;
import retrofit2.http.POST;

public interface AuthService {
    @NonNull
    @POST("accounts/get-token")
    Observable<AccessToken> signIn(String email, String password);

    @NonNull
    @POST("accounts/register")
    Observable<AccessToken> signUp(String email, String password, String name);

}
