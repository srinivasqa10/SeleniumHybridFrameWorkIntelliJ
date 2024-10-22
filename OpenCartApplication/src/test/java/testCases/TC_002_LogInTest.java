package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.util.Properties;

public class TC_002_LogInTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verifyLogin() {
        logger.info("***** Starting TC_002_LogInTest *****");

        try {


            //HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAcount();
            logger.info("Clicked on My Account Link");
            Thread.sleep(5000);
            hp.clickLogIn();
            logger.info("Clicked on Login");

            //LogInPage
            LogInPage lp = new LogInPage(driver);
            lp.setEmail(p.getProperty("email"));
            Thread.sleep(5000);
            logger.info("Entered the email");
            lp.setPassword(p.getProperty("password"));
            logger.info("Entered the Password");
            Thread.sleep(5000);
            lp.clickLoginBtn();

            //MyAccountPage
            MyAccountPage macp = new MyAccountPage(driver);
            boolean targetpage = macp.isMyAccountPageExists();

            Assert.assertTrue(targetpage);

            logger.info("***** Finished TC_002_LogInTest *****");
        } catch (Exception e)
        {
            Assert.fail();
        }
    }

}
