package nopcom.autm.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import nopcom.autm.base.BasePage;

public class RegistrationPage extends BasePage {
	
	

    @FindBy(id = "gender-male")
    private WebElement genderMale;

    @FindBy(id = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;


    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(css = "div.result")
    private WebElement registrationResult;

    public void open(String baseUrl) {
        driver.get(baseUrl + "/register");
    }

    public void selectMale() {
        genderMale.click();
    }

    public void enterFirstName(String fn) { firstName.clear(); firstName.sendKeys(fn); }
    public void enterLastName(String ln) { lastName.clear(); lastName.sendKeys(ln); }


    public void enterEmail(String e) { email.clear(); email.sendKeys(e); }
    public void enterPassword(String p) { password.clear(); password.sendKeys(p); }
    public void enterConfirmPassword(String p) { confirmPassword.clear(); confirmPassword.sendKeys(p); }

    public void clickRegister() { registerButton.click(); }

    public String getResultText() { return registrationResult.getText(); }
}
