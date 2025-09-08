package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class DriverFactory {
    protected static WebDriver driver;
    protected static String BASE_URL;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"baseUrl"})
    public void beforeSuite(@Optional("https://www.saucedemo.com/") String baseUrl) {
        BASE_URL = baseUrl; // Suite-level URL
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    public WebDriver getDriver() { return driver; }
}
