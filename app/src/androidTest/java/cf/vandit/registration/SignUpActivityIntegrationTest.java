package cf.vandit.registration;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

public class SignUpActivityIntegrationTest {

    @Rule
    public IntentsTestRule<SignUpActivity> activityRule =
            new IntentsTestRule<>(SignUpActivity.class);

    @Test
    public void testSignUpButtonClicked() {
        // Нажатие кнопки SignUp
        Espresso.onView(withId(R.id.signup_button))
                .perform(ViewActions.click());

        // Проверка, что активность LoginActivity была запущена после успешной регистрации
        intended(hasComponent(LoginActivity.class.getName()));
    }
}

