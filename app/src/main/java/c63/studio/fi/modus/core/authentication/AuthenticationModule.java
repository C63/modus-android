package c63.studio.fi.modus.core.authentication;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import c63.studio.fi.modus.BuildConfig;
import c63.studio.fi.modus.utils.GsonFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AuthenticationModule {

    @NonNull
    @Provides
    @Singleton
    public CredentialsController providesCredentialsController(@NonNull final SharedPreferences preferences) {
        return new CredentialsController(preferences);
    }

    @NonNull
    @Provides
    @Singleton
    public AuthenticationController providesAccountController(@NonNull final CredentialsController credentialsController) {
        AuthenticationService authenticationService = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
                .client(new OkHttpClient())
                .build()
                .create(AuthenticationService.class);
        return new AuthenticationController(authenticationService, credentialsController);
    }

}
