package core.transformers;

import core.wrappers.AlertsWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationsTransformer {

    public static List<AlertsWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<AlertsWrapper>emptyList();
        }
        List<AlertsWrapper> alerts = new ArrayList<AlertsWrapper>();
        for (WebElement element: elements){
            alerts.add(new AlertsWrapper(driver, element));
        }
        return alerts;
    }
}
