package cf.vandit.registration;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordStrategy implements AuthenticationStrategy {
    private LoginActivity activity;

    // Конструктор для стратегии восстановления пароля
    public ForgotPasswordStrategy(LoginActivity activity) {
        this.activity = activity;
    }

    @Override
    public void authenticate() {
        // Создание диалогового окна для восстановления пароля
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_forgot, null);
        EditText emailBox = dialogView.findViewById(R.id.emailBox);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Обработка нажатия кнопки сброса пароля
        dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailBox.getText().toString();

                // Проверка введенной электронной почты на корректность
                if (TextUtils.isEmpty(userEmail) || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    // Отображение сообщения об ошибке при некорректной электронной почте
                    Toast.makeText(activity, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Отправка письма для сброса пароля на указанную электронную почту
                FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Отображение сообщения об успешной отправке письма
                            Toast.makeText(activity, "Check your email", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            // Отображение сообщения об ошибке при неудачной отправке письма
                            Toast.makeText(activity, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Обработка нажатия кнопки отмены
        dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // Установка прозрачного фона для диалогового окна
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        // Показ диалогового окна для восстановления пароля
        dialog.show();
    }
}
