package POM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWikipedia {
    private WebDriver driver;
    @BeforeEach
    void init(){
        driver=new ChromeDriver();
        driver.get("https://www.wikipedia.org");
    }
    @Test
    void testwiki(){
        WebElement search=driver.findElement(By.id("searchInput"));
        Select select=new Select(driver.findElement(By.id("searchLanguage")));
        search.sendKeys("mutation testing");
        select.selectByValue("en");
        search.submit();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mw-page-title-main")));
        assertEquals("Mutation testing",element.getText()  );
    }
    @Test
    void testwikipom(){
        WikipediaPage wikipage=new WikipediaPage(driver);
        wikipage.fillForm("Mutation testing", "en");
        wikipage.sendForm();
        ResultPage resultPage=new ResultPage(driver);
        resultPage.waiting();
        assertEquals(resultPage.getTitle(), "Mutation testing");
    }
}
