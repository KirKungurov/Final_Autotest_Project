package core.transformers;

import core.wrappers.FoundedGroupsWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoundedGroupsTransformer {

    public static List<FoundedGroupsWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<FoundedGroupsWrapper>emptyList();
        }
        List<FoundedGroupsWrapper> groups = new ArrayList<FoundedGroupsWrapper>();
        for (WebElement element: elements){
            groups.add(new FoundedGroupsWrapper(driver, element));
        }
        return groups;
    }
}
