package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By user = By.id("user-name");
    private final By pass = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { this.driver = driver; }
    public void open(String baseUrl) { driver.get(baseUrl); }
    public String title() { return driver.getTitle(); } // "Swag Labs"
    public void login(String u, String p) {
        driver.findElement(user).clear(); driver.findElement(user).sendKeys(u);
        driver.findElement(pass).clear(); driver.findElement(pass).sendKeys(p);
        driver.findElement(loginBtn).click();
    }
    public boolean hasError() { return !driver.findElements(error).isEmpty(); }
}
