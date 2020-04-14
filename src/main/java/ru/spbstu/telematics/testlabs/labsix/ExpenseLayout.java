package ru.spbstu.telematics.testlabs.labsix;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

class ExpenseLayout {

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/add_new_expense")
    MobileElement expenseButton;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_title_edit")
    MobileElement title;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_sum_edit")
    MobileElement sum;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_date_edit")
    MobileElement date;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/new_expense_category_picker")
    MobileElement category;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/save_new_expense")
    MobileElement saveButton;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/expense_title")
    List<MobileElement> expenseTitle;


    ExpenseLayout(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)), this);
    }

    void addExpense()
    {
        expenseButton.click();
    }

    void enterExpense(String title, String sum, String date, String category) {
        this.title.sendKeys(title);
        this.sum.sendKeys(sum);
        this.date.sendKeys(date);
        this.category.sendKeys(category);
        saveButton.click();
    }

    boolean containsExpense(String expense){
        for (MobileElement element: expenseTitle) {
            if(element.getText().equals(expense))
                return true;
        }
        return false;
    }
}
