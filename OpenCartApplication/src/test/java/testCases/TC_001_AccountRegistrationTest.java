package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration() throws InterruptedException {

        logger.info("***** Starting TC_001_Account RegistrationTest *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAcount();
            logger.info("Clicked on My Account Link");
            hp.clickRegister();
            logger.info("Clicked on My Register Link");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Providing Customer Details");
            regpage.setFirstname(randomString().toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNum());

            String password = randomAlphaNumeric();//We are adding this step becuase Password and confirm Password should be same, we are saving randomAlphaNumeric in a varaible.

            regpage.setTxtPassword(password);
            regpage.setTxtConfirmPassword(password);

            regpage.clickCheckbox();
            regpage.clickSubmitButton();
            Thread.sleep(5000);

            logger.info("Validating Expected Result");
            String confmsg = regpage.getConfirmationMsg();
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        }
        catch (Exception e)
        {
            logger.error("Test Failed");
            logger.debug("Debug logs");
            Assert.fail();
        }
        logger.info("***** Finished TC_001_Account RegistrationTest *****");
    }
}
