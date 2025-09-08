package com.example.mobile.tests;

import com.example.mobile.core.BaseTest;
import com.example.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void canLogin() {
        LoginPage lp = new LoginPage(driver);

        // Use real credentials that work in your app or demo app
        lp.login("bob@example.com", "10203040");

        Assert.assertTrue(lp.isLoggedIn(), "Expected to be logged in.");
    }
}



