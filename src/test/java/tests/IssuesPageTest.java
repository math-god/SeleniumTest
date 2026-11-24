package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class IssuesPageTest extends BaseTest {

    private final By usernameFieldBy = By.id(LOCATORS.get("usernameFieldLocator"));
    private final By passwordFieldBy = By.id(LOCATORS.get("passwordFieldLocator"));
    private final By loginButtonBy = By.cssSelector(LOCATORS.get("loginButtonLocator"));
    private final By issuesButtonBy = By.xpath(LOCATORS.get("issuesButtonLocator"));
    private final By projectsListButtonBy = By.xpath(LOCATORS.get("projectsListButtonLocator"));
    private final By projectLinkBy = By.partialLinkText("DEMO");

    @Test
    @DisplayName("Проверка списка проектов")
    void projectListItemIsClickable() {
        WebElement username = driverInstance.findElement(usernameFieldBy);
        username.sendKeys(CONFIG.get("correctLogin"));

        WebElement password = driverInstance.findElement(passwordFieldBy);
        password.sendKeys(CONFIG.get("correctPassword"));

        WebElement login = driverInstance.findElement(loginButtonBy);
        login.click();


        WebElement issues = driverInstance.findElement(issuesButtonBy);
        issues.click();

        WebElement projectListButton = driverInstance.findElement(projectsListButtonBy);
        projectListButton.click();

        WebElement projectLink = driverInstance.findElement(projectLinkBy);
        assertEquals("issues/DEMO", projectLink.getAttribute("href"));
    }

}
