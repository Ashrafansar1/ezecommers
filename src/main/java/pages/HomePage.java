package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("img[alt='logo']");
    private final By popupCloseBtn = By.xpath("//button[@aria-label='Close']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    // 🔥 Popup Close Method with Explicit Wait
    public void closePopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            if (isElementDisplayed(popupCloseBtn)) {
                wait.until(ExpectedConditions.elementToBeClickable(popupCloseBtn)).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(popupCloseBtn));
                System.out.println("Popup closed successfully");
            } else {
                System.out.println("Popup not displayed or already closed");
            }

        } catch (Exception e) {
            System.out.println("Popup handling error: " + e.getMessage());
        }
    }

    // Optional: Check if popup is present
    public boolean isPopupPresent() {
        return driver.findElements(popupCloseBtn).size() > 0;
    }
}