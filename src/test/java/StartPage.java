import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StartPage {

        protected static WebDriver driver;
        protected static WebDriverWait wait;
        private String url = "https://new.internet-start.net/";
        private String emptyDivIdAttributeId = "previews";
        private String customSearchInputXpath = "//input[@id='customSearchInput']";
        private String searchButtonXpath = "//button";
        private String searchFieldXpath = "//input";
        private String searchResultListClassName = "article_header-title";
        private String searchInfo = "driverpack";

        private WebElement searchLine, searchButton;

        private void InitPageAttributes()
        {
            searchButton = driver.findElement(By.ByXPath.xpath(searchButtonXpath));
            searchLine = driver.findElement(By.ByXPath.xpath(searchFieldXpath));
        }

        public StartPage(WebDriver d)
        {
            driver = d;
            PageFactory.initElements(d,this);
            wait = new WebDriverWait(d, 2);
        }

        public void Navigate()
        {
            driver.get(url);
            InitPageAttributes();
        }

        public void AssretEmptySearch()
        {
            Navigate();
            Assert.assertTrue("!!! SearchLine is not displayed!!!",searchLine.isDisplayed());
            Assert.assertTrue("!!! SearchButton is not displayed!!!",searchButton.isDisplayed());
            searchLine.sendKeys("");
            searchButton.click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(emptyDivIdAttributeId))));
            Assert.assertTrue("!!!Empty search result is filled!!!",
                    driver.findElement(By.id(emptyDivIdAttributeId)).getText().equals(""));
        }

        public void AssretDriverPackSearchInCustomField()
        {
            AssretEmptySearch();
            WebElement CustomSearchField = driver.findElement(By.xpath(customSearchInputXpath));
            WebElement CustomSearchButton = driver.findElement(By.xpath(searchButtonXpath));
            Assert.assertTrue("!!! CustomSearchField is not displayed!!!",CustomSearchField.isDisplayed());
            Assert.assertTrue("!!! CustomSearchButton is not displayed!!!",CustomSearchButton.isDisplayed());

            CustomSearchField.sendKeys(searchInfo);
            CustomSearchButton.click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(searchResultListClassName))));
            List<WebElement> articles = driver.findElements(By.className(searchResultListClassName));

            Assert.assertTrue("!!!Empty '" + searchInfo +"' search result!!!",articles.size() > 0);
            int relatedResultsCnt = 0;
            for(int i=0;i<articles.size();i++){
                if(articles.get(i).getText().toLowerCase().contains(searchInfo)) relatedResultsCnt++;
            }
            Assert.assertTrue("!!!Search results does not have '" + searchInfo +"'!!!",relatedResultsCnt > 0);
        }

        public void AssretDriverPackSearch()
        {
            Navigate();

            Assert.assertTrue("!!! SearchLine is not displayed!!!",searchLine.isDisplayed());
            Assert.assertTrue("!!! SearchButton is not displayed!!!",searchButton.isDisplayed());
            searchLine.sendKeys(searchInfo);
            searchButton.click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(searchResultListClassName))));
            List<WebElement> articles = driver.findElements(By.className(searchResultListClassName));

            Assert.assertTrue("!!!Empty '" + searchInfo +"' search result!!!",articles.size() > 0);
            int relatedResultsCnt = 0;
            for(int i=0;i<articles.size();i++){
                if(articles.get(i).getText().toLowerCase().contains(searchInfo)) relatedResultsCnt++;
            }
            Assert.assertTrue("!!!Search results does not have '" + searchInfo +"'!!!",relatedResultsCnt > 0);
        }
}
