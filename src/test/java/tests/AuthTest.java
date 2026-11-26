package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AuthPage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class AuthTest extends BaseTest {

    @Test
    @DisplayName("Текст ошибки при неправильных данных")
    void authErrorTest() {
        var authPage = new AuthPage(driverInstance);
        authPage.enterUsername(CONFIG.get("incorrectPassword"));
        authPage.enterPassword(CONFIG.get("incorrectPassword"));
        authPage.clickLoginButton();

        assertEquals("Incorrect username or password.", authPage.getAuthError());
    }

    @Test
    @DisplayName("Проверка страницы восстановления пароля")
    void resetPasswordTest() {
        var authPage = new AuthPage(driverInstance);
        authPage.clickResetButton();
        assertFalse(authPage.isAuthButtonEnabled());

        authPage.enterEmail("some@maill.com");
        assertTrue(authPage.isAuthButtonEnabled());
    }
}
