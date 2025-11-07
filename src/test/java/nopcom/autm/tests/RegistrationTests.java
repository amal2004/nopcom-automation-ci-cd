package nopcom.autm.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import nopcom.autm.config.ConfigReader;
import nopcom.autm.driver.WebDriverFactory;
import nopcom.autm.pages.RegistrationPage;
import nopcom.autm.utils.CSVUtils;
import nopcom.autm.utils.ExcelUtils;

import java.util.Iterator;
import java.util.List;

@Listeners({nopcom.autm.listeners.TestListener.class})
public class RegistrationTests {

    @BeforeMethod
    public void setup() {
        // driver created lazily in Page objects
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }

    @DataProvider(name = "excelData")
    public Iterator<Object[]> excelDataProvider() {
        List<String[]> rows = ExcelUtils.readExcel("testdata/users.xlsx");
        return rows.stream().map(r -> new Object[]{r}).iterator();
    }

    @Test(dataProvider = "excelData", retryAnalyzer = nopcom.autm.listeners.RetryAnalyzer.class)
    public void registrationFromExcel(String[] row) {
    	 
    	 
        String first = row[0];
        String last = row[1];
        String email = row[2];
        String password = row[3];


        RegistrationPage reg = new RegistrationPage();
        reg.open(ConfigReader.getBaseUrl());
        reg.selectMale();
        reg.enterFirstName(first);
        reg.enterLastName(last);
        email = "test" + System.currentTimeMillis() + "@mail.com";
        reg.enterEmail(email);
        reg.enterPassword(password);
        reg.enterConfirmPassword(password);
        reg.clickRegister();

        String result = reg.getResultText();
        Assert.assertTrue(result.contains("Your registration completed") || result.contains("completed"),
                "Expected registration success, got: " + result);
    }

    // optional CSV provider
    @DataProvider(name = "csvData")
    public Iterator<Object[]> csvDataProvider() {
        List<String[]> rows = CSVUtils.readCsv("testdata/users.csv");
        return rows.stream().map(r -> new Object[]{r}).iterator();
    }
}
