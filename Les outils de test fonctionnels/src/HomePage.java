import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;
    private By categoryDropdown = By.id("gh-cat");
    private By searchInput = By.id("gh-ac");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCategory(String categoryValue) {
        Select categorySelect = new Select(driver.findElement(categoryDropdown));
        categorySelect.selectByValue(categoryValue);
    }

    public void searchForItem(String searchText) {
        WebElement searchBox = driver.findElement(searchInput);
        searchBox.sendKeys(searchText);
        searchBox.submit();
    }
}
