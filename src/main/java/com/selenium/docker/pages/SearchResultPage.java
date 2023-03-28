package com.selenium.docker.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "result-stats")
    private WebElement searchResultStatus;
    @FindBy(xpath = "//*[@id='rso']//a")
    private List<WebElement> searchResultLinks;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public List<String> getAllSearchResultLinks() {
        wait.until(ExpectedConditions.visibilityOf(this.searchResultStatus));
        List<String> links = new ArrayList<>();
        for (WebElement link : searchResultLinks) {
            String hrefLink = link.getAttribute("href");
            if(hrefLink != null) {
                links.add(hrefLink);
            }
        }
        return links;
    }
}
