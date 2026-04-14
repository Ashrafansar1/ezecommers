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

    @Test
    public void testLogo() {
        log.info("Checking logo");
        Assert.assertTrue(home.isLogoDisplayed());
    }

    @Test
    public void testPopupClose() {
        log.info("Closing popup");
        home.closePopup();
        Assert.assertFalse(home.isPopupPresent());
    }

    

}