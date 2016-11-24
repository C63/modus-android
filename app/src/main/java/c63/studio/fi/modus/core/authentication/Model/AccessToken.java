package c63.studio.fi.modus.core.authentication.Model;

public class AccessToken {

    private final String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
