package tests;

import configs.Config;
import core.BaseTest;
import core.wrappers.FoundedGroupsWrapper;
import core.pages.FoundedGroupsPage;
import core.pages.GroupsPage;
import core.pages.LoginPage;
import core.pages.MainPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JoinToGroupWithAgeRestrictionsTest extends BaseTest {

    private String NAME_Of_NEEDED_GROUP = "Autotesting 2019 JoinToGroup";
    private final String DESCRIPTION_OF_NEEDED_GROUP = "For bots";

    @Test
    public void joinToGroupWithAgeRestriction(){

        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickSearchGroup(NAME_Of_NEEDED_GROUP);
        FoundedGroupsPage foundedGroupsPage = new FoundedGroupsPage(driver);

        Assert.assertNull("Найдена группа, которую не должно находить",
                foundedGroupsPage.findNeededGroup(NAME_Of_NEEDED_GROUP, DESCRIPTION_OF_NEEDED_GROUP));
    }
}
