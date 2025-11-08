package nopcom.autm.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import nopcom.autm.config.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            initDriver();
        }
        return tlDriver.get();
    }

    private static void initDriver() {
        String browser = ConfigReader.getBrowser();
        
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            
            
            if (ConfigReader.isHeadless()) {
                options.addArguments("--headless=new","--disable-gpu","--window-size=1920,1080");
            } else {
                options.addArguments("--window-size=1920,1080");
            }
  
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
            driver.manage().window().maximize();
            tlDriver.set(driver);
        } else if ("edge".equalsIgnoreCase(browser)) {
        	
        	 EdgeOptions edgeOptions = new EdgeOptions();
             edgeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage",
                                      "--disable-gpu", "--window-size=1920,1080");
             WebDriver driver = new EdgeDriver(edgeOptions);
             tlDriver.set(driver);
        }else if ("firefox".equalsIgnoreCase(browser)) {
        	  FirefoxOptions ffOptions = new FirefoxOptions();
              if (Boolean.getBoolean("headless")) {
                  ffOptions.addArguments("--headless");
                  ffOptions.addArguments("--width=1920");
                  ffOptions.addArguments("--height=1080");
              }
              WebDriver driver = new FirefoxDriver(ffOptions);
              tlDriver.set(driver);
        }else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

    }

    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}

