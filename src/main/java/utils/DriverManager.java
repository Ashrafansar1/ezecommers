package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            String browser = ConfigReader.getBrowser();

            switch (browser.toLowerCase()) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions chromeOptions = new ChromeOptions();

                    if (ConfigReader.isHeadless()) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();

                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    if (ConfigReader.isHeadless()) {
                        firefoxOptions.addArguments("--headless");
                    }

                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();

                    EdgeOptions edgeOptions = new EdgeOptions();

                    if (ConfigReader.isHeadless()) {
                        edgeOptions.addArguments("--headless=new");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}