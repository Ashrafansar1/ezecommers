package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class EzLifeTests extends BaseTest {

    private HomePage homePage;

    @Test(priority = 1, description = "TC_01: Verify homepage loads with correct title and logo")
    public void testHomePageTitleAndLogo() {

        log.info("TC_01 Started");

        homePage = new HomePage(driver);

        String title = homePage.getPageTitle();
        log.info("Page Title: " + title);

        Assert.assertTrue(
            title.contains("EZ Life") || title.contains("Healthcare"),
            "Page title mismatch. Actual: " + title
        );

        log.info("Title validation passed");

        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo should be visible");
        log.info("Logo is displayed");

        log.info("TC_01 Passed");
    }

    @Test(priority = 2, description = "TC_02: Verify popup is closed successfully")
    public void testPopupClosed() {

        log.info("TC_02 Started");

        homePage = new HomePage(driver);

        homePage.closePopup();
        log.info("Popup close action performed");

        Assert.assertFalse(homePage.isPopupPresent(), "Popup is still visible!");
        log.info("Popup is not visible");

        log.info("TC_02 Passed");
    }
}