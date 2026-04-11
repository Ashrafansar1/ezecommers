package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;
import utils.DriverManager;

// ✅ Logger imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    protected WebDriver driver;

    // ✅ Logger define
    protected static Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Test Execution Started =====");

        driver = DriverManager.getDriver();
        log.info("Browser launched successfully");

        String url = ConfigReader.getBaseUrl();
        driver.get(url);
        log.info("Navigated to URL: " + url);
    }

    @AfterClass
    public void tearDown() {
        log.info("Closing browser...");

        DriverManager.quitDriver();

        log.info("===== Test Execution Finished =====");
    }
}