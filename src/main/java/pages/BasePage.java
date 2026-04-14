package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return WaitUtils.getWait(driver)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void click(By locator) {
        WaitUtils.getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }
}