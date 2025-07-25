import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartButton = By.id("atcBtn_btn_1");
    private By itemPrice = By.className("item-price");
    private By cartIcon = By.id("gh-cart-n");

    public BookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.urlContains("cart"));
    }

    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

    public String getCartItemCount() {
        return driver.findElement(cartIcon).getText();
    }
}
