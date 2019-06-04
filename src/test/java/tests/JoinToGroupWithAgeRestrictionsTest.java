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

    @Test
    public void joinToGroupWithAgeRestriction() throws Exception{
        boolean isNecessaryGroupFounded = true;

        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickSearchGroup(Config.nameOfNecessaryGroup);
        FoundedGroupsPage foundedGroupsPage = new FoundedGroupsPage(driver);

        List<FoundedGroupsWrapper> foundedGroups = foundedGroupsPage.findAllFoundedGroups();
        for (FoundedGroupsWrapper group: foundedGroups){
            if (group.isGroupNeeded(Config.nameOfCreatingGroup,Config.descriptionOfNecessaryGroup)){
                isNecessaryGroupFounded = false;
            }
        }

        Assert.assertTrue("Найдена группа, которую не должно находить",
                isNecessaryGroupFounded);
    }
}
