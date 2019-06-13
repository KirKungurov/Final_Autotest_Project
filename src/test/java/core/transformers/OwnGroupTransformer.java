package core.transformers;

import core.wrappers.FoundedGroupsWrapper;
import core.wrappers.OwnGroupWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OwnGroupTransformer {

    public static List<OwnGroupWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<OwnGroupWrapper>emptyList();
        }
        List<OwnGroupWrapper> groups = new ArrayList<OwnGroupWrapper>();
        for (WebElement element: elements){
            groups.add(new OwnGroupWrapper(element));
        }
        return groups;
    }
}
