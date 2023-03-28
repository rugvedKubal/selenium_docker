package com.selenium.docker.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(name = "q")
    private WebElement searchTextBox;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goToGooglePage() {
        this.driver.get("https://www.google.com");
        this.wait.until(ExpectedConditions.visibilityOf(this.searchTextBox));
    }

    public void search(String searchKey) {
        this.searchTextBox.sendKeys(searchKey);
        this.searchTextBox.sendKeys(Keys.ENTER);
    }
}
