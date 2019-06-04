package core.transformers;

import core.wrappers.FriendWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsTransformer {

    public static List<FriendWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<FriendWrapper>emptyList();
        }
        List<FriendWrapper> friends = new ArrayList<FriendWrapper>();
        for (WebElement element: elements){
            friends.add(new FriendWrapper(driver, element));
        }
        return friends;
    }

}
