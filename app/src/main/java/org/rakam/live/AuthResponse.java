package org.rakam.live;

/**
 * Created by buremba on 31/03/15.
 */
public class AuthResponse {
    public final String access_token;
    public final String error;

    public AuthResponse(String access_token, String error) {
        this.access_token = access_token;
        this.error = error;
    }
}
