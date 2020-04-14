package ru.spbstu.telematics.testlabs.labsix;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

class RegisterLayout {

    @FindBy(id="platkovsky.alexey.epamtestapp:id/register_button")
    MobileElement registerButton;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    MobileElement registrationEmail;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    MobileElement registrationUsername;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    MobileElement registrationPassword;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    MobileElement registrationConfirmPassword;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    MobileElement registrationButton;

    RegisterLayout(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    void clickRegister()
    {
        registerButton.click();
    }

    void enterEmailAndPassword(String email, String username, String password) {
        registrationEmail.sendKeys(email);
        registrationUsername.sendKeys(username);
        registrationPassword.sendKeys(password);
        registrationConfirmPassword.sendKeys(password);
        registrationButton.click();
    }
}
