package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.ConfigReader;

public class HomeTests extends BaseTest {

    HomePage home;

    @BeforeMethod
    public void openHome() {
        driver.get(ConfigReader.getBaseUrl());
        home = new HomePage(driver);
    }

    // ── Basic page tests ──────────────────────────────────────────────────────

    @Test(priority = 1)
    public void testLogo() {
        log.info("Checking logo");
        Assert.assertTrue(home.isLogoDisplayed());
    }

    @Test(priority = 2)
    public void testPopupClose() {
        log.info("Closing popup");
        home.closePopup();
        Assert.assertFalse(home.isPopupPresent());
    }

    @Test(priority = 3)
    public void testFacebookIcon() {
        log.info("Checking Facebook icon");
        home.scrollDown();
        Assert.assertTrue(home.isFacebookIconDisplayed());
    }

    // ── Menu hover tests ──────────────────────────────────────────────────────

    
    @DataProvider(name = "headerMenus")
    public Object[][] headerMenus() {
        return new Object[][] {
            { "Health and Wellness"     },   // priority 4
            { "Respiratory Care"        },   // priority 5
            { "Mobility & Patient Aid"  },   // priority 6
            { "Personal Care & Hygiene" },   // priority 7
            { "Orthopedic Support"      },   // priority 8
            { "Weighing Scales"         },   // priority 9
        };
    }

    
    @Test(priority = 4, dataProvider = "headerMenus")
    public void testHoverHeaderMenu(String menuName) {
        log.info("Hovering on header menu: " + menuName);
        home.closePopup();
        home.hoverOnMenu(menuName);
    }
    @Test(priority = 5)
    public void clickHealthAndWellness() {

        log.info("Clicking on 'Health and Wellness' menu");

        home.closePopup();
        home.clickHealthAndWellness();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Wait for navigation
        wait.until(ExpectedConditions.urlContains("categorySlug=health-and-wellness"));

        String currentUrl = driver.getCurrentUrl();

        // ✅ Combined assertion (cleaner)
        Assert.assertTrue(
                currentUrl.contains("products") &&
                currentUrl.contains("categorySlug=health-and-wellness"),
                "❌ Navigation failed. URL: " + currentUrl
        );

        log.info("✅ Navigated to Health and Wellness page");

        // 🔙 Go back
        driver.navigate().back();

        String baseUrl = ConfigReader.getBaseUrl();

        // ✅ Wait for homepage
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(baseUrl),
                ExpectedConditions.urlToBe(baseUrl + "/")
        ));

        // ✅ Final validation
        Assert.assertTrue(
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
                "❌ Failed to return to Home Page"
        );

        log.info("✅ Returned to Home Page successfully");
    
    }
    @Test(priority = 6)
    public void clickRespiratoryCare() {
    	
		log.info("Clicking on 'Respiratory Care' menu");

		home.closePopup();
		home.clickRespiratoryCare();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// ✅ Wait for navigation
		wait.until(ExpectedConditions.urlContains("categorySlug=respiratory-care"));

		String currentUrl = driver.getCurrentUrl();

		// ✅ Combined assertion (cleaner)
		Assert.assertTrue(
				currentUrl.contains("products") &&
				currentUrl.contains("categorySlug=respiratory-care"),
				"❌ Navigation failed. URL: " + currentUrl
		);

		log.info("✅ Navigated to Respiratory Care page");

		// 🔙 Go back
		driver.navigate().back();

		String baseUrl = ConfigReader.getBaseUrl();

		// ✅ Wait for homepage
		wait.until(ExpectedConditions.or(
				ExpectedConditions.urlToBe(baseUrl),
				ExpectedConditions.urlToBe(baseUrl + "/")
		));

		// ✅ Final validation
		Assert.assertTrue(
				driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
				driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
				"❌ Failed to return to Home Page"
		);

		log.info("✅ Returned to Home Page successfully");
    }
    @Test(priority = 7)
    public void clickMobilityAndPatientAid() {

        log.info("Clicking on 'Mobility & Patient Aid' menu");

        home.closePopup();
        home.clickMobilityAndPatientAid();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Updated WAIT (fixed URL encoding issue)
        wait.until(ExpectedConditions.urlContains("mobility"));

        String currentUrl = driver.getCurrentUrl();

        // ✅ Updated Assertion (no encoding issue)
        Assert.assertTrue(
                currentUrl.contains("products") &&
                currentUrl.contains("mobility"),
                "❌ Navigation failed. URL: " + currentUrl
        );

        log.info("✅ Navigated to Mobility & Patient Aid page");

        // 🔙 Navigate back
        driver.navigate().back();

        String baseUrl = ConfigReader.getBaseUrl();

        // ✅ Wait for homepage
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(baseUrl),
                ExpectedConditions.urlToBe(baseUrl + "/")
        ));

        // ✅ Final validation
        Assert.assertTrue(
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
                "❌ Failed to return to Home Page"
        );

        log.info("✅ Returned to Home Page successfully");
    
}
    @Test(priority = 8)
    public void clickPersonalCareAndHygiene() {

        log.info("Clicking on 'Personal Care & Hygiene' menu");

        home.closePopup();
        home.clickPersonalCareAndHygiene();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ FIX: use partial URL (avoid encoding issues like %26, %28, %29)
        wait.until(ExpectedConditions.urlContains("personal-care"));

        String currentUrl = driver.getCurrentUrl();

        // ✅ Assertion
        Assert.assertTrue(
                currentUrl.contains("products") &&
                currentUrl.contains("personal-care"),
                "❌ Navigation failed. URL: " + currentUrl
        );

        log.info("✅ Navigated to Personal Care & Hygiene page");

        // 🔙 Navigate back
        driver.navigate().back();

        String baseUrl = ConfigReader.getBaseUrl();

        // ✅ Wait for homepage
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(baseUrl),
                ExpectedConditions.urlToBe(baseUrl + "/")
        ));

        // ✅ Final validation
        Assert.assertTrue(
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
                "❌ Failed to return to Home Page"
        );

        log.info("✅ Returned to Home Page successfully");
    
}
    @Test(priority = 9)
    public void clickOrthopedicSupport() {

        log.info("Clicking on 'Orthopedic Support' menu");

        home.closePopup();
        home.clickOrthopedicSupport();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ FIX: partial match (no encoding issues)
        wait.until(ExpectedConditions.urlContains("orthopedic"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("products") &&
                currentUrl.contains("orthopedic"),
                "❌ Navigation failed. URL: " + currentUrl
        );

        log.info("✅ Navigated to Orthopedic Support page");

        // 🔙 Back
        driver.navigate().back();

        String baseUrl = ConfigReader.getBaseUrl();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(baseUrl),
                ExpectedConditions.urlToBe(baseUrl + "/")
        ));

        Assert.assertTrue(
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
                driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
                "❌ Failed to return to Home Page"
        );

        log.info("✅ Returned to Home Page successfully");
    
    
}
  
    
    @Test(priority = 10)
    public void clickWeighingScales() {
    	log.info("Clicking on 'Weighing Scales' menu");
		home.closePopup();
		home.clickWeighingScales();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// ✅ Wait for navigation
		wait.until(ExpectedConditions.urlContains("categorySlug=weighing-scales"));
		String currentUrl = driver.getCurrentUrl();
		// ✅ Combined assertion (cleaner)
		Assert.assertTrue(
				currentUrl.contains("products") &&
				currentUrl.contains("categorySlug=weighing-scales"),
				"❌ Navigation failed. URL: " + currentUrl
				);
		log.info("✅ Navigated to Weighing Scales page");
		// 🔙 Go back
		driver.navigate().back();
		String baseUrl = ConfigReader.getBaseUrl();
		// ✅ Wait for homepage
		wait.until(ExpectedConditions.or(
				ExpectedConditions.urlToBe(baseUrl),
				ExpectedConditions.urlToBe(baseUrl + "/")
				));
		// ✅ Final validation
		Assert.assertTrue(
				driver.getCurrentUrl().equalsIgnoreCase(baseUrl) ||
				driver.getCurrentUrl().equalsIgnoreCase(baseUrl + "/"),
				"❌ Failed to return to Home Page"
				);
		log.info("✅ Returned to Home Page successfully");
		
    }
     
}

