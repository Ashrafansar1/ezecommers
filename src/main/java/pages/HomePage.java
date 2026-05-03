package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Map;

public class HomePage extends BasePage {

    // ── Locators ─────────────────────────────────────────────────────────────
    private final By logo          = By.cssSelector("img[alt='logo']");
    private final By popupCloseBtn = By.xpath("//button[@aria-label='Close']");
    private final By facebookIcon  = By.xpath("//a[contains(@href, 'facebook.com')]");

    /**
     * Maps a human-readable menu key to its XPath locator.
     * Add / remove entries here if the site changes — no other code needs touching.
     */
    private static final Map<String, By> MENU_LOCATORS = Map.of(
        "Health and Wellness",                    By.xpath("//button[contains(., 'Health and Wellness')]"),
        "Respiratory Care",                       By.xpath("//button[normalize-space()='Respiratory Care']"),
        "Mobility & Patient Aid",                 By.xpath("//button[normalize-space()='Mobility & Patient Aid']"),
        "Personal Care & Hygiene",                By.xpath("//button[normalize-space()='Personal Care & Hygiene (Incontinence Care)']"),
        "Orthopedic Support",                     By.xpath("//button[contains(., 'Orthopedic Support')]"),
        "Weighing Scales",                        By.xpath("//button[normalize-space()='Weighing Scales']")
    );

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ── Logo ──────────────────────────────────────────────────────────────────
    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    // ── Popup ─────────────────────────────────────────────────────────────────
    public boolean isPopupPresent() {
        return !driver.findElements(popupCloseBtn).isEmpty();
    }

    public void closePopup() {
        if (isPopupPresent()) {
            click(popupCloseBtn);
        }
    }

    // ── Facebook icon ─────────────────────────────────────────────────────────
    public void scrollDown() {
        WebElement element = driver.findElement(facebookIcon);
        jsScrollIntoView(element);
    }

    public boolean isFacebookIconDisplayed() {
        return isElementDisplayed(facebookIcon);
    }

    // ── Header menus ──────────────────────────────────────────────────────────

    /**
     * Hovers over the named header menu button.
     *
     * @param menuName one of the keys defined in {@link #MENU_LOCATORS}
     * @throws IllegalArgumentException if the key is not recognised
     */
    public void hoverOnMenu(String menuName) {
        By locator = MENU_LOCATORS.get(menuName);
        if (locator == null) {
            throw new IllegalArgumentException("Unknown menu: '" + menuName + "'. "
                + "Valid keys: " + MENU_LOCATORS.keySet());
        }

        WebElement menu = waitForElementsVisible(locator).get(0);
        jsScrollIntoView(menu);

        new Actions(driver)
            .moveToElement(menu)
            .pause(Duration.ofMillis(100))
            .perform();

        System.out.println("Hovered on: " + menu.getText());
    }

    // ── Private helpers ───────────────────────────────────────────────────────
    private void jsScrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

