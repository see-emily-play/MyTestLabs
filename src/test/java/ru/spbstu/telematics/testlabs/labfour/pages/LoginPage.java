package ru.spbstu.telematics.testlabs.labfour.pages;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
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

import java.util.concurrent.TimeUnit;

public class LoginPage {
    @FindBy(css="input#ctl00_MainContent_LoginForm_UserName")
    WebElement loginBox;

    @FindBy(css="input#Password")
    WebElement passwordBox;

    @FindBy(css="a#ctl00_MainContent_LoginForm_LoginBtn")
    WebElement loginButton;

    @FindBy(css="div.error_login")
    WebElement loginError;

    @FindBy(css="a#ctl00_MainContent_LoginForm_forgotPWLink")
    WebElement forgotPasswordButton;

    @FindBy(css="input#ctl00_MainContent_PasswordSender_UserNameContainerID_UserName")
    WebElement emailBox;

    @FindBy(css="input#ctl00_MainContent_PasswordSender_UserNameContainerID_SubmitButton")
    WebElement submitButton;

    @FindBy(css="div.form-area.forgot_form")
    WebElement emailError;

    WebDriver driver;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\operadriver_win64\\operadriver.exe");
        driver = new OperaDriver();
    }

    @Given("^I open login page$")
    public void openLoginPage() {
        driver.get("http://urbanthreads.com/User/login.aspx");
        PageFactory.initElements(driver, this);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("login");
            }
        });
    }

    @When("^I enter login (.+) and password (.+)$")
    public void enterLoginAndPassword(String login, String password)
    {
        loginBox.sendKeys(login);
        passwordBox.sendKeys(password);
        loginButton.click();
    }

    @When("^I click forgot password button$")
    public void clickForgotPasswordButton()
    {
        forgotPasswordButton.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("password");
            }
        });
    }

    @And("^I enter email(.+)$")
    public void enterEmail(String email)
    {
        emailBox.sendKeys(email);
        submitButton.click();
    }

    @Then("^I verify that (.+) message contains: (.+) isn't recognized$")
    public void verifyThatMessageContains(String error, String message){
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        if (error.equals("emailError"))
            Assert.assertTrue(emailError.getText().toLowerCase().contains(message));
        else if(error.equals("loginError"))
            Assert.assertTrue(loginError.getText().toLowerCase().contains(message));
        else throw new NoSuchElementException("Can't find element" + error);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}
