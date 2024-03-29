package tests;

import configs.Config;
import core.BaseTest;
import core.Helper;
import core.layers.ConfirmDeletingGroupLayer;
import core.layers.CreateGroupLayer;
import core.layers.GroupCategoryLayer;
import core.pages.*;

import core.wrappers.OwnGroupWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class CreateGroupTest extends BaseTest {
    private final String NAME_OF_CREATING_GROUP = "Test of creation group";

    @Test
    public void createGroup(){
        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToCreateButton();
        new GroupCategoryLayer(driver).selectCategory();
        new CreateGroupLayer(driver).CrateGroup(NAME_OF_CREATING_GROUP);

        GroupPage groupPage = new GroupPage(driver);

        Assert.assertTrue("Создалась группа с неправильным названием",
                groupPage.checkGroupName(NAME_OF_CREATING_GROUP));

    }

    @After
    public void deleteGroup(){
        Helper.deleteGroup(baseUrl, driver, NAME_OF_CREATING_GROUP);
    }
}
