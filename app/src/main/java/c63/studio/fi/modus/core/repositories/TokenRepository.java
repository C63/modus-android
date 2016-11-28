package c63.studio.fi.modus.core.repositories;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import javax.inject.Inject;

public class TokenRepository {

    private static final String TOKEN_KEY = "TOKEN_KEY";
    private SharedPreferences preferences;

//    @Inject
    public TokenRepository(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void signOut() {
        preferences.edit()
                .remove(TOKEN_KEY)
                .apply();
    }

    @Nullable
    public String getToken() {
        return preferences.getString(TOKEN_KEY, null);
    }

    public void setToken(@Nullable String token) {
        if (token == null) {
            preferences.edit().remove(TOKEN_KEY).apply();
        } else {
            preferences.edit()
                    .putString(TOKEN_KEY, token)
                    .apply();
        }
    }

}
