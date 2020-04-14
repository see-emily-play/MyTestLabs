package ru.spbstu.telematics.testlabs.labsix;

import io.appium.java_client.android.AndroidDriver;
import org.junit.*;

import java.util.concurrent.TimeUnit;

public class AppiumTest {

    private static AndroidDriverManager driverManager;
    static AndroidDriver driver;
    private LoginLayout loginLayout;
    private RegisterLayout registerLayout;
    private ExpenseLayout expenseLayout;

    @BeforeClass
    public static void prepareTest() {
        driverManager = new AndroidDriverManager();
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void initPageObject() {
        loginLayout = new LoginLayout(driver);
        registerLayout = new RegisterLayout(driver);
        expenseLayout = new ExpenseLayout(driver);
        driver.launchApp();
    }

    void register()
    {
        registerLayout.clickRegister();
        registerLayout.enterEmailAndPassword("daria@gmail.com", "Daria Smirnova", "123456789");
    }

    @Test
    public void registrationTest() {
        register();
        Assert.assertEquals(".activities.LoginActivity", driver.currentActivity());
    }

    @Test
    public void loginTest(){
        register();
        loginLayout.enterEmailAndPassword("daria@gmail.com",  "123456789");
        Assert.assertEquals(".activities.BudgetActivity", driver.currentActivity());
    }

    @Test
    public void addExpenseTest()
    {
        register();
        loginLayout.enterEmailAndPassword("daria@gmail.com",  "123456789");
        expenseLayout.addExpense();
        expenseLayout.enterExpense("Gift", "200", "15/03/2020", "Shopping");
        Assert.assertTrue(expenseLayout.containsExpense("Gift"));
    }

    @After
    public void tearDown(){
        driver.closeApp();
    }

}
