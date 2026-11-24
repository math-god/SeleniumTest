package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class DashboardPageTest extends BaseTest {

    private final By usernameFieldBy = By.id(LOCATORS.get("usernameFieldLocator"));
    private final By passwordFieldBy = By.id(LOCATORS.get("passwordFieldLocator"));
    private final By loginButtonBy = By.cssSelector(LOCATORS.get("loginButtonLocator"));
    private final By newDashboardBy = By.xpath(LOCATORS.get("newDashboardLocator"));
    private final By dashboardNameBy = By.cssSelector(LOCATORS.get("dashboardNameLocator"));
    private final By issueDashboardButtonBy = By.cssSelector(LOCATORS.get("issueDashboardButtonLocator"));

    @Test
    @DisplayName("Создание дашборда, текстовое поле пустое -> кнопка не кликабельна")
    void newDashboardCreateEmptyTextTest() {
        auth();

        WebElement newDashboardButton = driverInstance.findElement(newDashboardBy);
        newDashboardButton.click();
        WebElement issueCreateButton = driverInstance.findElement(issueDashboardButtonBy);

        assertFalse(issueCreateButton.isEnabled());
    }

    @Test
    @DisplayName("Создание дашборда, текстовое поле заполнено -> кнопка кликабельна")
    void newDashboardCreateFullTextTest() {
        auth();

        WebElement newDashboardButton = driverInstance.findElement(newDashboardBy);
        newDashboardButton.click();

        WebElement summary = driverInstance.findElement(dashboardNameBy);
        WebElement issueCreateButton = driverInstance.findElement(issueDashboardButtonBy);

        summary.sendKeys("new dashboard");
        assertTrue(issueCreateButton.isEnabled());
    }

    private void auth() {
        WebElement username = driverInstance.findElement(usernameFieldBy);
        username.sendKeys(CONFIG.get("correctLogin"));

        WebElement password = driverInstance.findElement(passwordFieldBy);
        password.sendKeys(CONFIG.get("correctPassword"));

        WebElement login = driverInstance.findElement(loginButtonBy);
        login.click();
    }

}
