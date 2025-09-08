package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    public static String take(WebDriver driver, String namePrefix) {
        try {
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            File dest = new File("target/screenshots/" + namePrefix + "_" + ts + ".png");
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (Exception e) { return null; }
    }
}
