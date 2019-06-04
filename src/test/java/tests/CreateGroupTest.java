package tests;

import configs.Config;
import core.BaseTest;
import core.layers.ConfirmDeletingGroupLayer;
import core.layers.CreateGroupLayer;
import core.layers.GroupCategoryLayer;
import core.pages.GroupPage;
import core.pages.GroupsPage;
import core.pages.LoginPage;
import core.pages.MainPage;

import org.junit.Assert;
import org.junit.Test;

public class CreateGroupTest extends BaseTest {

    @Test
    public void createGroup() throws Exception{
        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToCreateButton();
        new GroupCategoryLayer(driver).selectCategory();
        new CreateGroupLayer(driver).CrateGroup(Config.nameOfCreatingGroup);

        GroupPage groupPage = new GroupPage(driver);

        Assert.assertTrue("Создалась группа с неправильным названием",
                groupPage.checkGroupName(Config.nameOfCreatingGroup));

        groupPage.deleteGroup();
        new ConfirmDeletingGroupLayer(driver).confirmDeleting();
    }
}
