package tests;

import listener.ScreenshotOnErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AuthPage;
import pages.DashboardPage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnErrorExtension.class)
public class DashboardTest extends BaseTest {

    @Test
    @DisplayName("Создание дашборда, текстовое поле пустое -> кнопка не кликабельна")
    void newDashboardCreateEmptyTextTest() {
        auth(new AuthPage(driverInstance));

        var dashboardPage = new DashboardPage(driverInstance);
        dashboardPage.clickNewDashboard();
        assertFalse(dashboardPage.isCreateButtonEnabled());
    }

    @Test
    @DisplayName("Создание дашборда, текстовое поле заполнено -> кнопка кликабельна")
    void newDashboardCreateFullTextTest() {
        auth(new AuthPage(driverInstance));

        var dashboardPage = new DashboardPage(driverInstance);
        dashboardPage.clickNewDashboard();
        dashboardPage.inputDashboardName("Dashboard Name");
        assertTrue(dashboardPage.isCreateButtonEnabled());
    }

    private void auth(AuthPage authPage) {
        authPage.enterUsername(CONFIG.get("correctLogin"));
        authPage.enterPassword(CONFIG.get("correctPassword"));
        authPage.clickLoginButton();
    }
}
