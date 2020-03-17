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

@Category(SmokeTest.class)
public class UsingHomePage {
    WebDriver driver;
    HomePage page;

    @Before    //создать WebDriver, перейти на сайт Urban Threads и дождаться загрузки страницы
    public void openSite()
    {
        driver = new OperaDriver();
        driver.get("http://urbanthreads.com");
        page= PageFactory.initElements(driver, HomePage.class);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().contains("urban threads");
            }
        });
    }

    @Test //ввести в поле поиска "cat", проверить, что перешли к результатам поиска
    public void testSearchCats()
    {
        page.searchCats();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("search results");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("search results"));
    }

    @Test //нажать на кнопку "Recent releases" и убедиться, что перешли к нужной странице
    public void testClickOnRecentReleases()
    {
        page.clickOnRecentReleases();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("urban threads recent release");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertEquals("recent releases", page.getPageHeadline());
    }

    @After // закрыть браузер
    public void closeSite()
    {
        driver.quit();
    }

}
