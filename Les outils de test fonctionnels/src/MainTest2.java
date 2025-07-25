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

class MainTest2 {
    private static WebDriver driver;
    private static WebDriver driver2;

    @BeforeAll
    static void init() {
        driver = new ChromeDriver();
        driver2 = new ChromeDriver();
    }

    @Test
    void testAddToCart() {
        driver.get("https://www.ebay.com/");

        Select categorySelect = new Select(driver.findElement(By.id("gh-cat")));
        categorySelect.selectByValue("267");

        WebElement searchInput = driver.findElement(By.id("gh-ac"));
        searchInput.sendKeys("python in easy steps");
        searchInput.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement specificBookLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Python in Easy Steps by McGrath, Mike")));
        String bookLink = specificBookLink.getAttribute("href");

        driver2.get(bookLink);

        WebElement addToCartButton = driver2.findElement(By.id("atcBtn_btn_1"));
        addToCartButton.click();

        WebDriverWait waitCart = new WebDriverWait(driver2, Duration.ofSeconds(10));
        waitCart.until(ExpectedConditions.urlContains("cart"));

        Assertions.assertTrue(driver2.getCurrentUrl().contains("cart"), "Cart page URL is incorrect.");

        WebElement fieldPrice = driver2.findElement(By.className("item-price"));
        Assertions.assertEquals(fieldPrice.getText(), "US $4.58" +
                "");

        WebElement cartIcon = driver2.findElement(By.id("gh-cart-n"));
        String itemCount = cartIcon.getText();
        System.out.println("Cart Item Count: " + itemCount);
        Assertions.assertEquals(itemCount, "1", "Cart item count is incorrect.");
    }
    @Test
    void testAddToCartPom() {
        //given
        driver.get("https://www.ebay.com/");
        //when
        HomePage homePage = new HomePage(driver);
        homePage.selectCategory("267");
        //and
        homePage.searchForItem("python in easy steps");
        //and
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        String bookLink = searchResultsPage.getBookLink("Python in Easy Steps by McGrath, Mike");
        //and
        driver2.get(bookLink);
        //and
        BookPage bookPage = new BookPage(driver2);
        bookPage.addToCart();
        //then
        Assertions.assertTrue(driver2.getCurrentUrl().contains("cart"), "Cart page URL is incorrect.");
//and
        String priceText = bookPage.getItemPrice();
        System.out.println("Item Price: " + priceText);
        Assertions.assertEquals("US $4.58", priceText);
//and
        String itemCount = bookPage.getCartItemCount();
        System.out.println("Cart Item Count: " + itemCount);
        Assertions.assertEquals("1", itemCount, "Cart item count is incorrect.");
    }

}
