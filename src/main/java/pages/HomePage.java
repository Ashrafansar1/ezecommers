package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("img[alt='logo']");
    private final By popupCloseBtn = By.xpath("//button[@aria-label='Close']");
    private final By facebookicon = By.xpath("//a[contains(@href, 'facebook.com')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public void closePopup() {
        if (isPopupPresent()) {
            click(popupCloseBtn);
        }
    }

    public boolean isPopupPresent() {
        return !driver.findElements(popupCloseBtn).isEmpty();
    }

    public void scrollDown() {
        WebElement element = driver.findElement(facebookicon);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isFacebookIconDisplayed() {
        return isElementDisplayed(facebookicon);
    }
}