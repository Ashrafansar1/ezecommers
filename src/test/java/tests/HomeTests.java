package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
}