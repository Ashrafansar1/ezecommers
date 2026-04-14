package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("img[alt='logo']");
    private final By popupCloseBtn = By.xpath("//button[@aria-label='Close']");
    

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
        return driver.findElements(popupCloseBtn).size() > 0;
    }

   
}