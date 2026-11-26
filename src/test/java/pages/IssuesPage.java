package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static tests.BaseTest.LOCATORS;

public class IssuesPage {

    private final WebDriver driver;

    private final By issuesButtonBy = By.xpath(LOCATORS.get("issuesButtonLocator"));
    private final By projectsListButtonBy = By.xpath(LOCATORS.get("projectsListButtonLocator"));
    private final By projectLinkBy = By.xpath(LOCATORS.get("projectLinkLocator"));

    public IssuesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnIssuesButton() {
        WebElement issues = driver.findElement(issuesButtonBy);
        issues.click();
    }

    public void clickOnProjectsListButton() {
        WebElement projectsListButton = driver.findElement(projectsListButtonBy);
        projectsListButton.click();
    }

    public String getProjectLink() {
        WebElement projectLink = driver.findElement(projectLinkBy);
        return projectLink.getAttribute("href");
    }

}
