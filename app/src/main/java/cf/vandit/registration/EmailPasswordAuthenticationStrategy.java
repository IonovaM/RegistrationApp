package cf.vandit.registration;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// Конкретная стратегия для аутентификации через электронную почту и пароль
// Конкретная стратегия для аутентификации через электронную почту и пароль
// Конкретная стратегия для аутентификации через электронную почту и пароль
public class EmailPasswordAuthenticationStrategy implements AuthenticationStrategy {
    private LoginActivity activity;
    private FirebaseAuth auth;
    private String email;
    private String password;

    public EmailPasswordAuthenticationStrategy(LoginActivity activity, FirebaseAuth auth, String email, String password) {
        this.activity = activity;
        this.auth = auth;
        this.email = email;
        this.password = password;
    }

    @Override
    public void authenticate() {
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!password.isEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show();
                        });
            } else {
               // activity.showPasswordError("Empty fields are not allowed");
            }
        } else if (email.isEmpty()) {
            //activity.showEmailError("Empty fields are not allowed");
        } else {
           // activity.showEmailError("Please enter correct email");
        }
    }
}
