package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
   static {
       TITLE = "xpath://*[@resource-id='pcs']/android.view.View[1]/android.widget.TextView[1]";
               FOOTER_ELEMENT = "id:xpath://*[@text='View article in browser']";
               SAVE_ARTICLE_BUTTON = "id:org.wikipedia:id/page_save";
               SNACKBAR_BUTTON = "id:org.wikipedia:id/snackbar_action";
               MY_LIST_NAME = "xpath://*[contains(@text,'Name of this list')]";
               MY_LIST_OK_BUTTON = "xpath://*[contains(@text,'OK')]";
               ARTICLE_TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]";
   }


    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
