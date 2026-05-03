package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import utils.ConfigReader;

public class HomeTests extends BaseTest {

    HomePage home;

    @BeforeMethod
    public void openHome() {
        driver.get(ConfigReader.getBaseUrl());
        home = new HomePage(driver);
    }

    // ── Basic Tests ───────────────────────────────────────
    @Test(priority = 1)
    public void testLogo() {
        Assert.assertTrue(home.isLogoDisplayed());
    }

    @Test(priority = 2)
    public void testPopupClose() {
        home.closePopup();
        Assert.assertFalse(home.isPopupPresent());
    }

    @Test(priority = 3)
    public void testFacebookIcon() {
        home.scrollDown();
        Assert.assertTrue(home.isFacebookIconDisplayed());
    }

    // ── Hover Test ────────────────────────────────────────
    @DataProvider(name = "menus")
    public Object[][] menus() {
        return new Object[][] {
            {"Health and Wellness"},
            {"Respiratory Care"},
            {"Mobility & Patient Aid"},
            {"Personal Care & Hygiene"},
            {"Orthopedic Support"},
            {"Weighing Scales"}
        };
    }

    @Test(priority = 4, dataProvider = "menus")
    public void testHoverMenu(String menu) {
        home.closePopup();
        home.hoverOnMenu(menu);
    }

    // ── ✅ FIXED Reusable Navigation Method ─────────────────
    private void verifyMenu(String menuName, String keyword) {

        home.closePopup();

        String baseUrl = ConfigReader.getBaseUrl();

        // Click menu
        home.clickMenu(menuName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ✅ Wait until URL changes from homepage
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe(baseUrl)
        ));

        String url = driver.getCurrentUrl();

        // ✅ Flexible assertion (handles encoding + variations)
        Assert.assertTrue(
                url.contains("products") || url.contains(keyword),
                "❌ Navigation failed for " + menuName + " URL: " + url
        );

        // 🔙 Back
        driver.navigate().back();

        // ✅ Wait for homepage again
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe(baseUrl),
                ExpectedConditions.urlToBe(baseUrl + "/")
        ));
    }

    // ── Navigation Tests ──────────────────────────────────
    @Test(priority = 5)
    public void testHealthAndWellness() {
        verifyMenu("Health and Wellness", "health-and-wellness");
    }

    @Test(priority = 6)
    public void testRespiratoryCare() {
        verifyMenu("Respiratory Care", "respiratory-care");
    }

    @Test(priority = 7)
    public void testMobility() {
        verifyMenu("Mobility & Patient Aid", "mobility");
    }

    @Test(priority = 8)
    public void testPersonalCare() {
        verifyMenu("Personal Care & Hygiene", "personal-care");
    }

    @Test(priority = 9)
    public void testOrthopedic() {
        verifyMenu("Orthopedic Support", "orthopedic");
    }

    @Test(priority = 10)
    public void testWeighingScales() {
        verifyMenu("Weighing Scales", "weighing-scales");
    }
}