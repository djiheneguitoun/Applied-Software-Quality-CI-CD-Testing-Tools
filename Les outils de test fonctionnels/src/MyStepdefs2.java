import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs2 {


    private static WebDriver driver;
    private static WebDriver driver2;
   private HomePage homePage;
    private SearchResultsPage searchResultsPage;
   private BookPage bookPage;
   private  String bookLink;


    @Given("I am in the page of {string}")
    public void iAmInThePageOf(String arg0) {
        driver = new ChromeDriver();
        driver.get(arg0);
    }

    @When("I select category {string}")
    public void iSelectCategory(String arg0) {

        homePage = new HomePage(driver);
        homePage.selectCategory(arg0);

    }

    @And("I enter {string} in the search bar and submit")
    public void iEnterInTheSearchBarAndSubmit(String arg0) {
        homePage.searchForItem(arg0);

    }

    @And("I select the book {string}")
    public void iSelectTheBook(String arg0) {
        searchResultsPage = new SearchResultsPage(driver);
         bookLink = searchResultsPage.getBookLink(arg0);
    }

    @And("I switch the tab")
    public void iSwitchTheTab() {
        driver2 = new ChromeDriver();

        driver2.get(bookLink);

    }

    @And("I add click on add to cart")
    public void iAddClickOnAddToCart() {
        bookPage = new BookPage(driver2);
        bookPage.addToCart();
    }

    @Then("the URL of the page should contains {string}")
    public void theURLOfThePageShouldContains(String arg0) {
        Assertions.assertTrue(driver2.getCurrentUrl().contains(arg0), "Cart page URL is incorrect.");

    }

    @And("the price on the page should be {string}")
    public void thePriceOnThePageShouldBe(String arg0) {
        String priceText = bookPage.getItemPrice();
        System.out.println("Item Price: " + priceText);
        Assertions.assertEquals(arg0, priceText);
    }

    @And("the number of objects in the cart should be {string}")
    public void theNumberOfObjectsInTheCartShouldBe(String arg0) {
        String itemCount = bookPage.getCartItemCount();
        System.out.println("Cart Item Count: " + itemCount);
        Assertions.assertEquals(arg0, itemCount, "Cart item count is incorrect.");
    }
}
