package ru.spbstu.telematics.testlabs.labfour.pages;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\operadriver_win64\\operadriver.exe");
        driver = new OperaDriver();
    }

    @Given("^I open home page$")
    public void openHomePage() {
        driver.get("http://urbanthreads.com");
        PageFactory.initElements(driver, this);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("urban threads");
            }
        });
    }
    
    @When("^I search for cats$")
    public void searchForCats()
    {
        searchBox.sendKeys("cat");
        searchButton.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("search results");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());

    }

    @Then("I click on recent releases$")
    public void clickOnRecentReleases() {
        recentReleases.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("urban threads recent release");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());
    }

    @Then("^I verify that page (.+) is (.+)$")
    public void verifyThatPageHeadlineIs(String message, String expected){
        if(message.equals("headline"))
            Assert.assertEquals(headline.getText().toLowerCase(), expected);
        else if(message.equals("name"))
            Assert.assertTrue(driver.getTitle().toLowerCase().contains(expected));
        else throw new NoSuchElementException("Can't find element" + message);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
