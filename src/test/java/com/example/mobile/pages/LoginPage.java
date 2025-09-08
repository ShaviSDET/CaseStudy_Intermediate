package com.example.mobile.pages;

import com.example.mobile.core.BasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Replace these with the REAL values from Appium Inspector
    private final By username = acc("test-Username");
    private final By password = acc("test-Password");
    private final By loginBtn  = acc("test-LOGIN");
    private final By successMarker = acc("test-Products"); // element visible post-login

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }

    public boolean isLoggedIn() {
        try { return text(successMarker) != null; }
        catch (Exception e) { return false; }
    }
}



