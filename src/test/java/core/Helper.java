package core;

import core.layers.ConfirmDeletingGroupLayer;
import core.pages.GroupPage;
import core.pages.GroupsPage;
import core.pages.MainPage;
import core.pages.OwnGroupsPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Helper {

    private Helper(){
    }

    public static void init(String baseUrl, WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
    }

    public static void deleteGroup(String baseUrl, WebDriver driver, String expectedNameOfGroup){
        init(baseUrl,driver);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToOwnGroupButton();
        OwnGroupsPage ownGroupsPage = new OwnGroupsPage(driver);
        Assert.assertNotNull("Группа уже удалена",
                ownGroupsPage.findNeededOwnGroup(expectedNameOfGroup));
        GroupPage groupPage = new GroupPage(driver);
        groupPage.deleteGroup();
        new ConfirmDeletingGroupLayer(driver).confirmDeleting();

    }
}
