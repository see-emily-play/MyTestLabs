package ru.spbstu.telematics.testlabs.LabTwo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    //@FindBy(xpath="//*[@id=\"ctl00_MainContent_PasswordSender_UserNameContainerID_EmailPanel\"]/div/div[3]/text()")
    @FindBy(css="div.form-area.forgot_form")
    WebElement emailError;

    void login()
    {
        loginBox.sendKeys("abc@gmail.com");
        passwordBox.sendKeys("123456");
        loginButton.click();
    }

    String getLoginError()
    {
        return loginError.getText().toLowerCase();
    }

    void forgotPassword()
    {
        forgotPasswordButton.click();
    }

    void enterEmail()
    {
        emailBox.sendKeys("sample");
        submitButton.click();
    }

    String getEmailError()
    {
        return emailError.getText().toLowerCase();
    }


}
