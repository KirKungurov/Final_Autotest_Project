package core.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final By EMAIL = By.xpath(".//input[@name='st.email']");
    private  static final By PASSWORD = By.xpath(".//input[@name='st.password']");
    private static final By BUTTON = By.xpath(".//input[@data-l='t,sign_in']");
    private static final By LOGO = By.className("anonym_logo");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String login, String password) {
        type(EMAIL, login);
        type(PASSWORD, password);
        click(BUTTON, "Пропала кнопка Войти");
        new MainPage(driver);
    }

    @Override
    protected  void check(){
        Assert.assertTrue("Не прогрузился логотип",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO),
                        4, 500));
        Assert.assertTrue("Не прогрузилась кнопка Войти",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON),
                        4, 500));
    }
}