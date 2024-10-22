package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//a[@title='My Account']")
    WebElement lnkMyAccount;

    @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
    WebElement lnkMyRegister;

    @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
    WebElement lnkLogin;

    public void clickMyAcount()
    {
        lnkMyAccount.click();
    }

    public void clickRegister()
    {
        lnkMyRegister.click();
    }

    public void clickLogIn()
    {
        lnkLogin.click();
    }
}
