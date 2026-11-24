package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class AuthPageTest extends BaseTest {

    private final By usernameFieldBy = By.id(LOCATORS.get("usernameFieldLocator"));
    private final By passwordFieldBy = By.id(LOCATORS.get("passwordFieldLocator"));
    private final By loginButtonBy = By.cssSelector(LOCATORS.get("loginButtonLocator"));
    private final By authButtonBy = By.className(LOCATORS.get("authButtonLocator"));
    private final By resetButtonBy = By.partialLinkText("Reset");

    @Test
    @DisplayName("Текст ошибки при неправильных данных")
    void authErrorTest() {
        WebElement username = driverInstance.findElement(usernameFieldBy);
        username.sendKeys(CONFIG.get("incorrectLogin"));

        WebElement password = driverInstance.findElement(passwordFieldBy);
        password.sendKeys(CONFIG.get("incorrectPassword"));

        WebElement login = driverInstance.findElement(loginButtonBy);
        login.click();

        WebElement error = driverInstance.findElement(By.xpath(LOCATORS.get("authErrorLocator")));
        assertEquals("Incorrect username or password.", error.getText());
    }

    @Test
    @DisplayName("Проверка страницы восстановления пароля")
    void resetPasswordTest() {
        WebElement reset = driverInstance.findElement(resetButtonBy);
        reset.click();

        WebElement login = driverInstance.findElement(authButtonBy);
        assertFalse(login.isEnabled());

        WebElement emailField = driverInstance.findElement(By.xpath(LOCATORS.get("emailFieldLocator")));
        emailField.sendKeys("some@maill.com");
        assertTrue(login.isEnabled());
    }

}
