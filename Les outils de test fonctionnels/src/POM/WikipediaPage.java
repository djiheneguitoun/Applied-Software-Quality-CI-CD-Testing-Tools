package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.input.Input;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WikipediaPage extends PageObject{
    @FindBy(id="searchInput") private WebElement searchInput;
    @FindBy(id="searchLanguage") private WebElement searchLanguage;
    public WikipediaPage(WebDriver driver)  {
        super(driver);
    }

    public Select getSelect() {
        return new Select(searchLanguage);
    }

    public void fillForm (String search, String language) {
        searchInput.sendKeys(search);
        getSelect().selectByValue(language);

    }

    public void sendForm() {
        searchInput.submit();
    }



}
