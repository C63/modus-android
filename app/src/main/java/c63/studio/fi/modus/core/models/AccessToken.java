package c63.studio.fi.modus.core.models;

import java.io.Serializable;

public class AccessToken implements Serializable {

    private final String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
