package ru.spbstu.telematics.testlabs.LabTwo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(css="input#searchBox")
    WebElement searchBox;

    @FindBy(css="a#ctl00_searchButtonMP")
    WebElement searchButton;

    @FindBy(css="a#recentRelTab")
    WebElement recentReleases;

    @FindBy(css=".page-headline")
    WebElement headline;

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
    }

    void searchCats()
    {
        searchBox.sendKeys("cat");
        searchButton.click();
    }

    void clickOnRecentReleases()
    {
        recentReleases.click();
    }

    String getPageHeadline()
    {
        return headline.getText().toLowerCase();
    }
}
