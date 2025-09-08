package com.example.mobile.core;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement $(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) { $(locator).click(); }
    protected void type(By locator, String text) { $(locator).sendKeys(text); }
    protected String text(By locator) { return $(locator).getText(); }

    // Helpers for common locator strategies
    protected By acc(String id) { return AppiumBy.accessibilityId(id); } // content-desc/testID
    protected By rid(String id) { return AppiumBy.id(id); }              // resource-id
}



