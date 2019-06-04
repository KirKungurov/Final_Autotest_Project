package core;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;

public abstract class BaseTest {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    protected WebDriver driver;

    @Before
    public void setUp() throws Exception{
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://ok.ru";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
        String verificationErrorsString = verificationErrors.toString();
        if (!"".equals(verificationErrorsString)){
            fail(verificationErrorsString);
        }
    }
}
