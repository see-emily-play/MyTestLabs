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
public class UsingPage3 {

    WebDriver driver;
    Page3 page;

    @Before         //создать WebDriver, перейти на сайт Urban Threads и дождаться загрузки страницы
    public void openSite()
    {
        driver = new OperaDriver();
        driver.get("https://urbanthreads.com/products.aspx?productid=UT21764");
        page= PageFactory.initElements(driver, Page3.class);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("urban threads");
            }
        });
    }

    @Test //нажать кнопку Add to wish list, дождаться загрузки страницы и проверить,
          //что перешли к странице регистрации
    public void testAddToWishList()
    {
        page.addToWishList();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("login");
            }
        });

        Assert.assertTrue(page.getRegisterHeadline().contains("register"));
    }

    @Test //нажать кнопку View color change sheet, дождаться загрузки страницы,
          //убедиться, что перешли к странице Thread List
    public void testViewColorChangeSheet()
    {
        page.viewColorChangeSheet();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("thread list");
            }
        });

        Assert.assertTrue(page.getPageHeadline().contains("thread list"));
    }

    @After // закрыть браузер
    public void closeSite()
    {
        driver.quit();
    }

}
