package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Map;

public class HomePage extends BasePage {

    private final By logo = By.cssSelector("img[alt='logo']");
    private final By popupCloseBtn = By.xpath("//button[@aria-label='Close']");
    private final By facebookIcon = By.xpath("//a[contains(@href, 'facebook.com')]");

    private static final Map<String, By> MENU_LOCATORS = Map.of(
        "Health and Wellness", By.xpath("//button[contains(., 'Health and Wellness')]"),
        "Respiratory Care", By.xpath("//button[normalize-space()='Respiratory Care']"),
        "Mobility & Patient Aid", By.xpath("//button[normalize-space()='Mobility & Patient Aid']"),
        "Personal Care & Hygiene", By.xpath("//button[contains(., 'Personal Care')]"),
        "Orthopedic Support", By.xpath("//button[contains(., 'Orthopedic Support')]"),
        "Weighing Scales", By.xpath("//button[normalize-space()='Weighing Scales']")
    );

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public boolean isPopupPresent() {
        return !driver.findElements(popupCloseBtn).isEmpty();
    }

    public void closePopup() {
        if (isPopupPresent()) click(popupCloseBtn);
    }

    public void scrollDown() {
        WebElement element = driver.findElement(facebookIcon);
        jsScrollIntoView(element);
    }

    public boolean isFacebookIconDisplayed() {
        return isElementDisplayed(facebookIcon);
    }

    public void hoverOnMenu(String menuName) {
        WebElement menu = getMenuElement(menuName);
        new Actions(driver).moveToElement(menu).pause(Duration.ofMillis(100)).perform();
    }

    // ✅ FIXED CLICK METHOD
    public void clickMenu(String menuName) {
        WebElement element = getMenuElement(menuName);

        try {
            element.click();
        } catch (Exception e) {
            // fallback if normal click fails
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    private By getMenuLocator(String menuName) {
        By locator = MENU_LOCATORS.get(menuName);
        if (locator == null) {
            throw new IllegalArgumentException("Invalid menu: " + menuName);
        }
        return locator;
    }

    private WebElement getMenuElement(String menuName) {
        WebElement element = waitForElementsVisible(getMenuLocator(menuName)).get(0);
        jsScrollIntoView(element);
        return element;
    }

    private void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }
}