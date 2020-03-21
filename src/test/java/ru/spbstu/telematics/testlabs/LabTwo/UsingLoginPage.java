package ru.spbstu.telematics.testlabs.LabTwo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Category(SmokeTest.class)
public class UsingLoginPage {
    WebDriver driver;
    LoginPage page;

    @Before         //создать WebDriver, перейти на сайт Urban Threads и дождаться загрузки страницы
    public void openSite()
    {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\operadriver_win64\\operadriver.exe");
        driver = new OperaDriver();
        driver.get("http://urbanthreads.com/User/login.aspx");
        page= PageFactory.initElements(driver, LoginPage.class);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("login");
            }
        });
    }

    @Test //ввести неверные логин с паролем, увидеть сообщение об ошибке (email address and password combination isn't recognized")
    public void testLogin()
    {
        page.login();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        System.out.println("Error: " + page.getLoginError());

        Assert.assertTrue(page.getLoginError().contains("email address and password combination isn't recognized"));
    }

    @Test //нажать кнопку Forgot Password, ввести неверный e-mail, получтить сообщение об ошибке (email address
           //isn't found)
    public void testForgotPassword()
    {
        page.forgotPassword();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("password");
            }
        });
        page.enterEmail();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        System.out.println("Error: " + page.getEmailError());

        Assert.assertTrue(page.getEmailError().contains("email address"));
    }

    @After // закрыть браузер
    public void closeSite()
    {
        driver.quit();
    }

}
