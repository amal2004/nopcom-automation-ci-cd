package nopcom.autm.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import nopcom.autm.driver.WebDriverFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {
    public static String takeScreenshot(String name) {
        try {
            var driver = WebDriverFactory.getDriver();
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("java.io.tmpdir") + File.separator + name + ".png";
            Files.copy(screen.toPath(), Path.of(dest));
            Reporter.log("Saved screenshot: " + dest);
            return dest;
        } catch (Exception e) {
            return null;
        }
    }
}
