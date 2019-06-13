package core;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Helper {
    private Helper(){}

    public static void init(String baseUrl, WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }
}
