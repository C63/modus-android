package c63.studio.fi.modus.core;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
