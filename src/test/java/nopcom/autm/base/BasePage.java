package nopcom.autm.base;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import nopcom.autm.driver.WebDriverFactory;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected void jsClick(org.openqa.selenium.WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
}
