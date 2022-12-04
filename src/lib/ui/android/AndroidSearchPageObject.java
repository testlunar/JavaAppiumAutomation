package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON = "xpath://*[contains(@text,'SKIP')]";
                SEARCH_CONTAINER = "id:org.wikipedia:id/search_container";
                CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
                SEARCH_RESULT_BY_TITLE_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']/..//*[@text='{DESCRIPTION}']";
                SEARCH_RESULT_LOCATOR = "id:org.wikipedia:id/search_results_list";
                PAGE_LIST_ITEM_TITLE = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
                PAGE_LIST_ITEM_TITLE_ID = "id:org.wikipedia:id/page_list_item_title";
                EMPTY_LABEL = "xpath://*[contains(@text,'No results')]";
                SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INPUT2 = "xpath://*[@resource-id='org.wikipedia:id/search_container']/android.widget.TextView";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
