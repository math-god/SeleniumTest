package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import listener.ScreenshotOnErrorExtension;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class BaseTest {
    protected static final ObjectMapper mapper = new ObjectMapper();

    public static final Map<String, String> CONFIG;
    public static final Map<String, String> LOCATORS;

    public static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected WebDriver driverInstance;

    static {
        try {
            Map<String, String> configMap = mapper.readValue(DataFileType.CONFIG.file, new TypeReference<>() {
            });
            CONFIG = Collections.unmodifiableMap(configMap);
        } catch (IOException e) {
            System.err.println("Невозможно прочитать файл с данными для тестов: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }

        try {
            Map<String, String> configMap = mapper.readValue(DataFileType.SELECTORS.file, new TypeReference<>() {
            });
            LOCATORS = Collections.unmodifiableMap(configMap);
        } catch (IOException e) {
            System.err.println("Невозможно прочитать файл с локаторами для тестов: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    @BeforeEach
    protected void setup() {
        var driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8081");
        threadLocalDriver.set(driver);
        driverInstance = threadLocalDriver.get();
    }

    @AfterEach
    protected void teardown() {
        driverInstance = null;
    }


    @AllArgsConstructor
    public enum DataFileType {
        SELECTORS(new File("src/test/resources/data/locators.json")),
        CONFIG(new File("src/test/resources/data/config.json"));

        private final File file;
    }
}
