package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResultPage extends PageObject {


    @FindBy(className="mw-page-title-main") private WebElement titleElement;


    public ResultPage(WebDriver driver)  {
        super(driver);
    }

    public String getTitle() {
        return titleElement.getText();
    }

    public void waiting() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(titleElement));
    }
}
