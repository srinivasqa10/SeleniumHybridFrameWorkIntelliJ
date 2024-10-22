package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    public void setEmail(String email) {
        txtEmailAddress.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickLoginBtn() {
        btnLogin.click();
    }
}
