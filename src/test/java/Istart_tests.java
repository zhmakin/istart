import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Istart_tests {

    private static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.gecko.driver", "c:/Program Files/Mozilla Firefox/browser/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    @Test
    public void testAction()
    {
        StartPage sp = new StartPage(driver);

        //1) Test for empty search
        sp.AssretEmptySearch();

        //2) Test for expected results search
        sp.AssretDriverPackSearch();

        //3) Test for expected results of search using custom field
        sp.AssretDriverPackSearchInCustomField();
    }
}
