package com.example.mobile.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        // You can hardcode the app path while learning:
        // Example: "C:\\apps\\MyDemoApp.apk" on Windows
        String appPath = System.getProperty("app", "C:\\apps\\MyDemoApp.apk");

        // If testing a real device, set udid via -Dudid= or leave null for emulator
        String udid = System.getProperty("udid", "");
        String deviceName = System.getProperty("deviceName", "Android");
        int systemPort = Integer.parseInt(System.getProperty("systemPort", "8200"));

        UiAutomator2Options opts = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(deviceName)
                .setUdid(udid.isEmpty() ? null : udid)
                .setApp(appPath)                   // comment out if you use appPackage/appActivity instead
                .setAutoGrantPermissions(true)
                .setSystemPort(systemPort);

        // If your app is already installed, use this instead of setApp():
        // opts.setAppPackage("your.package").setAppActivity("your.activity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), opts);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
