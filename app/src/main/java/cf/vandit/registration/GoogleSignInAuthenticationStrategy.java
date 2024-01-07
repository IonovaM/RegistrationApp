package cf.vandit.registration;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

// Конкретная стратегия для входа через Google
// Конкретная стратегия для входа через Google
public class GoogleSignInAuthenticationStrategy implements AuthenticationStrategy {
    private LoginActivity activity;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    public GoogleSignInAuthenticationStrategy(LoginActivity activity, GoogleSignInClient googleSignInClient) {
        this.activity = activity;
        this.googleSignInClient = googleSignInClient;
    }

    @Override
    public void authenticate() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
