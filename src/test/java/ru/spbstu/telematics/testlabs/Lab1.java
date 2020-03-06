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
          // ������� ��������� WebDriver
            driver = new OperaDriver();
    }

    @Test
    public void testGoogle() {
        // ������� google
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        // ����
        String expected="ghost - official";
        element.sendKeys(expected);
        // ��������� �����
        element.submit();

        // �������� �������� ��������, �������� ExpectedCondition ������ 500 �����������
	    //�� ��� ���, ���� ������� �� ����� �������������
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(expected);
            }
        });

        // �������� ��������
        System.out.println("Page title is: " + driver.getTitle());

    	//����� �������� �� ���� (<h3 class="LC20lb DKV0Md">Ghost - Official</h3>)
        element = driver.findElement(By.tagName("h3"));
	
	    //�������� �� ������ � ������  ��������� ������
        Assert.assertEquals(expected, element.getText().toLowerCase());
    }

    @After
    public void closeGoogle()
    {
        // ������� �������
        driver.quit();
    }
}
