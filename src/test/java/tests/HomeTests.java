package tests;

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

    /**
     * Supplies one menu name per test invocation.
     * Priorities 4-9 are preserved via the data order.
     */
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

    /**
     * Single parameterised test that replaces the six individual hover tests.
     * TestNG runs it once per row in {@link #headerMenus()}, naming each
     * invocation after the menu label for clear reporting.
     */
    @Test(priority = 4, dataProvider = "headerMenus")
    public void testHoverHeaderMenu(String menuName) {
        log.info("Hovering on header menu: " + menuName);
        home.closePopup();
        home.hoverOnMenu(menuName);
    }
}

