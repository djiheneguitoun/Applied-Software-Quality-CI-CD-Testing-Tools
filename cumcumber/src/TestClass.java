import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",plugin={"pretty","html:reports_exo1.html"})
public class TestClass{
// This class is empty
}
