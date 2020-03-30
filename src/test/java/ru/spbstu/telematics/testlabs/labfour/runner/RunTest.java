package ru.spbstu.telematics.testlabs.labfour.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue={"ru.spbstu.telematics.testlabs.labfour.pages"},
        features="src/test/java/ru/spbstu/telematics/testlabs/labfour/scenario/"
)

public class RunTest {
}
