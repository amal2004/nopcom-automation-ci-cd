package nopcom.autm.listeners;


import org.testng.*;

import nopcom.autm.driver.WebDriverFactory;
import nopcom.autm.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshot = ScreenshotUtil.takeScreenshot(result.getName());
        if (screenshot != null) {
            Reporter.log("Screenshot on failure: " + screenshot);
        }
        WebDriverFactory.quitDriver();
    }

    @Override
    public void onTestStart(ITestResult result) { }

    @Override
    public void onFinish(ITestContext context) {
        WebDriverFactory.quitDriver();
    }
    // other methods omitted for brevity
}
