package c63.studio.fi.modus.core.authentication;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class CredentialsController {
    private static final String TOKEN_KEY = "TOKEN_KEY";
    private SharedPreferences preferences;

    CredentialsController(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    void signOut() {
        preferences.edit()
                .remove(TOKEN_KEY)
                .apply();
    }

    @Nullable
    private String getToken() {
        return preferences.getString(TOKEN_KEY, null);
    }

    void setToken(@Nullable String token) {
        if (token == null) {
            preferences.edit().remove(TOKEN_KEY).apply();
        } else {
            preferences.edit()
                    .putString(TOKEN_KEY, token)
                    .apply();
        }
    }

    boolean hasSavedCredentials() {
        return getToken() != null;
    }

    String getCredentials() {
        return getToken();
    }

}
