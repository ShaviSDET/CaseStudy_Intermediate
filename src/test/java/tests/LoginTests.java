package tests;

import base.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Listeners({listeners.TestListener.class})
public class LoginTests extends DriverFactory {

    @Test(description="1) Verify landing page title")
    public void verifyLandingTitle() {
        LoginPage lp = new LoginPage(getDriver());
        lp.open(BASE_URL);
        Assert.assertEquals(lp.title(), "Swag Labs", "Landing page title mismatch");
    }

    @Test(description="2 & 5) Valid login, then verify title (Positive)")
    @Parameters({"validUser","validPass"})
    public void validLogin(String user, String pass) {
        LoginPage lp = new LoginPage(getDriver());
        lp.open(BASE_URL);
        lp.login(user, pass);

        InventoryPage inv = new InventoryPage(getDriver());
        Assert.assertTrue(inv.isLoaded(), "Inventory page not loaded after valid login");
        Assert.assertEquals(inv.title(), "Swag Labs", "Post-login title mismatch");
    }

    @Test(description="3 & 6) Invalid login, must stay on login (Negative)")
    @Parameters({"invalidUser","invalidPass"})
    public void invalidLogin(String user, String pass) {
        LoginPage lp = new LoginPage(getDriver());
        lp.open(BASE_URL);
        lp.login(user, pass);

        Assert.assertTrue(lp.hasError(), "Error banner should appear for invalid login");
        Assert.assertEquals(lp.title(), "Swag Labs", "Title should remain on login page");
    }
}
