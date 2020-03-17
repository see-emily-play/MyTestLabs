package ru.spbstu.telematics.testlabs.LabTwo;


import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTest.class)
@Suite.SuiteClasses({UsingHomePage.class, UsingLoginPage.class, UsingPage3.class})
public class SmokeTestSuite {
}
