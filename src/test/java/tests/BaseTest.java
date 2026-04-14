package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    protected WebDriver driver;

    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        log.info("===== Test Started =====");

        driver = DriverManager.getDriver();

        log.info("Browser launched");
    }

    @AfterClass
    public void tearDown() {
        log.info("Closing browser");

        DriverManager.quitDriver();

        log.info("===== Test Finished =====");
    }
}