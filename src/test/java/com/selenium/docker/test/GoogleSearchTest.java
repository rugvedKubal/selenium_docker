package com.selenium.docker.test;

import com.selenium.docker.pages.GoogleSearchPage;
import com.selenium.docker.pages.SearchResultPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class GoogleSearchTest {

    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;
    private SearchResultPage searchResultPage;


    @BeforeTest
    public void setup() throws Exception {
        //WebDriverManager.firefoxdriver().setup();

        String host = "localhost";
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            dc = DesiredCapabilities.firefox();
        }
        else {
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        String completeUrl = "http://"+ host + ":4444/wd/hub";
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);

        googleSearchPage = new GoogleSearchPage(driver);
        searchResultPage = new SearchResultPage(driver);
        googleSearchPage.goToGooglePage();
    }

    @Test
    public void googleSearchPageTest() {
        googleSearchPage.search("selenium");
        List<String> links = searchResultPage.getAllSearchResultLinks();
        System.out.println("Following are search result links for search key -> 'selenium':\n");
        for(String link : links) {
            System.out.println(link);
        }
        Assert.assertNotNull(links, "Failed to get search result links, following is the result:\n"+links);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
