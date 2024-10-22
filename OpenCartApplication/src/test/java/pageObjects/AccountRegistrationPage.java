package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@name='firstname']")
    WebElement txtFirstName;

    @FindBy(xpath="//input[@name='lastname']")
    WebElement txtLastName;

    @FindBy(xpath="//input[@name='email']")
    WebElement txtEmail;

    //input[@name='telephone']
    @FindBy(xpath="//input[@name='telephone']")
    WebElement txtTelephone;

    @FindBy(xpath="//input[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath="//input[@placeholder='Password Confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@type='checkbox']")
    WebElement chkdPolicy;

    @FindBy(xpath="//input[@type='submit']")
    WebElement btnSubmit;


    @FindBy(xpath="//div[@id='content']//h1")
    WebElement msgConfirmation;

    public void setFirstname(String fname)
    {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String phonenum)
    {
        txtTelephone.sendKeys(phonenum);
    }

    public void setTxtPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }

    public void setTxtConfirmPassword(String pwd)
    {
        txtConfirmPassword.sendKeys(pwd);
    }

    public void clickCheckbox()
    {
        chkdPolicy.click();
    }

    public void clickSubmitButton()
    {
        btnSubmit.click();
    }

    public String getConfirmationMsg()
    {
        try
        {
            return (msgConfirmation.getText());
        } catch (Exception e)
        {
            return (e.getMessage());
        }
    }

}
