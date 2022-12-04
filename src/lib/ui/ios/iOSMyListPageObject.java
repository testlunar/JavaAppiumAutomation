package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_XPATH_BY_TEXT = "xpath://*[contains(@text,'{SUBSTRING}')]";
        LIST_TITLE = "id:org.wikipedia:id/item_title";
        ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
