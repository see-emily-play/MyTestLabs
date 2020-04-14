package ru.spbstu.telematics.testlabs.labsix;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;


class AndroidDriverManager {

    private static AppiumDriverLocalService appiumDriverLocalService = null;

    private static AndroidDriver driver;

    AndroidDriver getDriver() {
        if (driver == null) {
            startService();
            createDriver();
        }
        return driver;
    }

    private void startService() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort();
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        AppiumDriverLocalService appiumDriverLocalService = builder.build();
        AndroidDriverManager.appiumDriverLocalService = appiumDriverLocalService;
        appiumDriverLocalService.start();
    }

    private void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("device", "android");
        capabilities.setCapability("deviceName", "Nexus 5X API 27");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "app-debug.apk");
        capabilities.setCapability("--local-timezone", "false");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("appWaitDuration", 30000);
        capabilities.setCapability("disabledAndroidWatchers", "true");
        capabilities.setCapability("app-wait-activity", "activity-to-wait-for");
        driver = new AndroidDriver<MobileElement>(appiumDriverLocalService.getUrl(), capabilities);
    }

}
