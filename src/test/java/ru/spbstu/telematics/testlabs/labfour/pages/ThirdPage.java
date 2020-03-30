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

public class ThirdPage {
    @FindBy(css="a.color-link")
    WebElement colorSheetButton;

    @FindBy(css="h2.page-headline")
    WebElement headline;

    @FindBy(css="a#ctl00_MainContent_ProductCtrl_AddToFaves_addToFaves" )
    WebElement addToWishListButton;

    WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\operadriver_win64\\operadriver.exe");
        driver = new OperaDriver();
    }

    @Given("^I open design page$")
    public void openDesignPage() {
        driver.get("https://urbanthreads.com/products.aspx?productid=UT21764");
        PageFactory.initElements(driver, this);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("urban threads");
            }
        });
    }

    @When("^I click view color change sheet button$")
    public void viewColorChangeSheet()
    {
        colorSheetButton.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("thread list");
            }
        });
    }

    @When("^I click add to wish list button$")
    public void clickAddToWishListButton()
    {
        addToWishListButton.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("login");
            }
        });
    }

    @Then("^I verify that page's (.+) is (.+)$")
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
