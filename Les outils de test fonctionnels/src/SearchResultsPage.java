import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By bookLink = By.partialLinkText("Python in Easy Steps by McGrath, Mike");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getBookLink(String link) {
        WebElement specificBookLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(link)));
        return specificBookLink.getAttribute("href");
    }
}
