package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;//Log4j
    public Properties p;

    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException {
        //Loading config.properties file
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p=new Properties();
        p.load(file);

        logger= (Logger) LogManager.getLogger(this.getClass());

        //if it is executing in Remote VM, then we have to use the below condition
        if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //os
            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN10);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No matching OS");
                return;
            }

            //browser
            switch (br.toLowerCase())
            {
                case "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default: System.out.println("No Matching browser"); return;
            }

            driver = new RemoteWebDriver(new URL("http://192.168.1.6:4444/"),capabilities);

        }

        if (p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase())
            {
                case "chrome" : driver=new ChromeDriver(); break;
                case "edge" : driver=new EdgeDriver(); break;
                case "firefox" : driver=new FirefoxDriver(); break;
                default: System.out.println("Invalid Browser");  return;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL1")); //Reading URL from properties file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity","Regression","Master"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        String generatoedString = RandomStringUtils.randomAlphabetic(5);
        return generatoedString;
    }

    public String randomNum()
    {
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumeric()
    {
        String generatoedString = RandomStringUtils.randomAlphabetic(3);
        String generatedNumber= RandomStringUtils.randomNumeric(3);
        return(generatoedString+"@"+generatedNumber);
    }

    public String captureScreen(String tname)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp +".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
