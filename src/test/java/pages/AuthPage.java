package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static tests.BaseTest.LOCATORS;

public class AuthPage {

    private final WebDriver driver;

    private final By usernameFieldBy = By.xpath(LOCATORS.get("usernameFieldLocator"));
    private final By passwordFieldBy = By.xpath(LOCATORS.get("passwordFieldLocator"));
    private final By loginButtonBy = By.xpath(LOCATORS.get("loginButtonLocator"));
    private final By authButtonBy = By.xpath(LOCATORS.get("authButtonLocator"));
    private final By resetButtonBy = By.xpath(LOCATORS.get("resetLinkLocator"));
    private final By authError = By.xpath(LOCATORS.get("authErrorLocator"));
    private final By emailFieldBy = By.xpath(LOCATORS.get("emailFieldLocator"));

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(usernameFieldBy);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordFieldBy);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonBy);
        loginButton.click();
    }

    public String getAuthError() {
        return driver.findElement(authError).getText();
    }

    public void clickResetButton() {
        WebElement resetButton = driver.findElement(resetButtonBy);
        resetButton.click();
    }

    public boolean isAuthButtonEnabled() {
        return driver.findElement(authButtonBy).isEnabled();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailFieldBy);
        emailField.sendKeys(email);
    }
}
