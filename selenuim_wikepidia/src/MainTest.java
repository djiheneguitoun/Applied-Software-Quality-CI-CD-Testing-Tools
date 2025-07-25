import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

class MainTest {
    private static WebDriver driver;
    private static WebDriver driver2;

    @BeforeAll
    static void init() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/win/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver2 = new ChromeDriver();
    }

    @Test
    void testAddToCart() {
        driver.get("https://www.ebay.com/");

        // Step 1: Select the "Books" category
        Select categorySelect = new Select(driver.findElement(By.id("gh-cat")));
        categorySelect.selectByValue("267");  // "Books" category

        // Step 2: Search for "python in easy steps"
        WebElement searchInput = driver.findElement(By.id("gh-ac"));
        searchInput.sendKeys("python in easy steps");
        searchInput.submit();

        // Step 3: Click on the specific book titled "Python in Easy Steps by McGrath, Mike"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement specificBookLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Python in Easy Steps by McGrath, Mike")));
        String bookLink = specificBookLink.getAttribute("href");

        // Open the book link in the second driver
        driver2.get(bookLink);

        // Step 4: Add the book to the cart
        WebElement addToCartButton = driver2.findElement(By.id("atcBtn_btn_1"));
        addToCartButton.click();

        // Wait for the cart page to load and verify
        WebDriverWait waitCart = new WebDriverWait(driver2, Duration.ofSeconds(10));
        waitCart.until(ExpectedConditions.urlContains("cart"));

        // Assertion: Verify the current URL is for the cart
        Assertions.assertTrue(driver2.getCurrentUrl().contains("cart"), "Cart page URL is incorrect.");

        // Step 5: Verify the total price displayed on the cart page
        WebElement fieldPrice = driver2.findElement(By.className("item-price"));
        System.out.println("Item Price: " + fieldPrice.getText());
        Assertions.assertEquals(fieldPrice.getText(), "US $4.58" +
                "");  // Adjust the expected price if necessary

        // Step 6: Verify the quantity shown in the cart icon (should be 1 item)
        WebElement cartIcon = driver2.findElement(By.id("gh-cart-n"));
        String itemCount = cartIcon.getText();
        System.out.println("Cart Item Count: " + itemCount);
        Assertions.assertEquals(itemCount, "1", "Cart item count is incorrect.");
    }


}
