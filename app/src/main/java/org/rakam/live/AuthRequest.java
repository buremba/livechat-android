package org.rakam.live;

/**
 * Created by buremba on 31/03/15.
 */
public class AuthRequest {
    public final String email;
    public final String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
