package c63.studio.fi.modus.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import c63.studio.fi.modus.BuildConfig;
import c63.studio.fi.modus.core.repositories.TokenRepository;
import c63.studio.fi.modus.utils.GsonFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModusModule {

    private Context context;

    public ModusModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences("MODUS_ANDROID", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public TokenRepository providesTokenRepositorys(@NonNull final SharedPreferences preferences) {
        return new TokenRepository(preferences);
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(@NonNull final TokenRepository repository) {
        return new ModusHttp(repository).build();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(@NonNull final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public AuthController providesAuthController(@NonNull final TokenRepository tokenRepository) {
        AuthService authService = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
                .client(new OkHttpClient())
                .build()
                .create(AuthService.class);
        return new AuthController(authService, tokenRepository);
    }

    @Provides
    @Singleton
    public ModusApiService providesModusApiService(@NonNull final Retrofit retrofit) {
        return retrofit.create(ModusApiService.class);
    }
}
