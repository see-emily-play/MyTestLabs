package ru.spbstu.telematics.testlabs.LabTwo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page3 {

    @FindBy(css="a.color-link")
    WebElement colorSheetButton;

    @FindBy(css="h2.page-headline")
    WebElement headline;

    //WebDriver driver;

    @FindBy(css="a#ctl00_MainContent_ProductCtrl_AddToFaves_addToFaves" )
    WebElement addToWishListButton;

    @FindBy(css="div.center.margin_bottom_60px > h2.margin_bottom_20px")
    WebElement registerHeadline;


    /*public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }*/

    void viewColorChangeSheet()
    {
        colorSheetButton.click();
    }

    String getPageHeadline()
    {
        return headline.getText().toLowerCase();
    }


    void addToWishList()
    {
        addToWishListButton.click();
    }

    String getRegisterHeadline()
    {
        return registerHeadline.getText().toLowerCase();
    }

}
