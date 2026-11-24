package listener;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotOnErrorExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        var driver = BaseTest.threadLocalDriver.get();
        if (driver == null) return;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = context.getDisplayName() + "_" + System.currentTimeMillis() + ".png";
            Path dest = Path.of("screenshots", fileName);
            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);
        } catch (Exception e) {
            System.err.println("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        var localDriver = BaseTest.threadLocalDriver.get();
        if (localDriver != null) {
            localDriver.quit();
            BaseTest.threadLocalDriver.remove();
        }
    }
}
