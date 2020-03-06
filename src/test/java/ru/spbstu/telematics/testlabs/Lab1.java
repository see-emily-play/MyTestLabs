package ru.spbstu.telematics.testlabs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Lab1 {
    WebDriver driver;

    @Before
    public void openGoogle()
    {
          // Создать экземпляр WebDriver
            driver = new OperaDriver();
    }

    @Test
    public void testGoogle() {
        // открыть google
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        // Ввод
        String expected="ghost - official";
        element.sendKeys(expected);
        // Отправить форму
        element.submit();

        // ожидание загрузки страницы, вызывает ExpectedCondition каждые 500 миллисекунд
	    //до тех пор, пока условие не будет удовлетворено
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(expected);
            }
        });

        // Название страницы
        System.out.println("Page title is: " + driver.getTitle());

    	//Поиск элемента по тэгу (<h3 class="LC20lb DKV0Md">Ghost - Official</h3>)
        element = driver.findElement(By.tagName("h3"));
	
	    //одинаков ли запрос и первый  результат поиска
        Assert.assertEquals(expected, element.getText().toLowerCase());
    }

    @After
    public void closeGoogle()
    {
        // Закрыть браузер
        driver.quit();
    }
}
