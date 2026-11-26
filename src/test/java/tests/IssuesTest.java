package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AuthPage;
import pages.IssuesPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class IssuesTest extends BaseTest {

    @Test
    @DisplayName("Проверка списка проектов")
    void projectListItemIsClickable() {
        var authPage = new AuthPage(driverInstance);
        authPage.enterUsername(CONFIG.get("correctLogin"));
        authPage.enterPassword(CONFIG.get("correctPassword"));
        authPage.clickLoginButton();

        var issuePage = new IssuesPage(driverInstance);
        issuePage.clickOnIssuesButton();
        issuePage.clickOnProjectsListButton();
        assertEquals("issues/DEMO", issuePage.getProjectLink());
    }

}
