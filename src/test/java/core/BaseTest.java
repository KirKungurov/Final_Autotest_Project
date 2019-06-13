package core;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

public abstract class BaseTest {
    private StringBuffer verificationErrors = new StringBuffer();
    protected WebDriver driver;
    protected String baseUrl= "https://ok.ru";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        Helper.init(baseUrl, driver);
    }

    @After
    public void tearDown(){
        driver.quit();
        String verificationErrorsString = verificationErrors.toString();
        if (!"".equals(verificationErrorsString)){
            fail(verificationErrorsString);
        }
    }
}
