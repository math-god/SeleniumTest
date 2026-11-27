package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static tests.BaseTest.LOCATORS;

public class DashboardPage extends BasePage {

    private final By newDashboardBy = By.xpath(LOCATORS.get("newDashboardLocator"));
    private final By dashboardNameBy = By.xpath(LOCATORS.get("dashboardNameLocator"));
    private final By issueDashboardButtonBy = By.xpath(LOCATORS.get("issueDashboardButtonLocator"));

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickNewDashboard() {
        driver.findElement(newDashboardBy).click();
    }

    public boolean isCreateButtonEnabled() {
        return driver.findElement(issueDashboardButtonBy).isEnabled();
    }

    public void inputDashboardName(String dashboardName) {
        driver.findElement(dashboardNameBy).sendKeys(dashboardName);
    }
}
