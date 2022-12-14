package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListPageObject extends MainPageObject {
    protected static  String
            ARTICLE_XPATH_BY_TEXT ,
            LIST_TITLE ,
            ARTICLE_TITLE;

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleXpath(String name) {
        return ARTICLE_XPATH_BY_TEXT.replace("{SUBSTRING}", name);
    }

    public void swipeToLeftArticleToDelete(String name_article) {
        String articleNameXpath = getArticleXpath(name_article);

        checkMyListAppeared();
        this.swipeElementToLeft(articleNameXpath,
                "can not swipe left", 5);

        if(Platform.getInstance().isiOS()){
            this.clickElementToTheRightUpperCorner(articleNameXpath,
                    "can not swipe left");

        }
    }

    public void checkMyListAppeared() {
        this.waitForElementPresent(LIST_TITLE,
                "element with name of list title not found", 15);
    }

    public void checkListItemDissapeared(String name_article) {
        String articleNameXpath = getArticleXpath(name_article);
        this.waitForElementNotPresent(articleNameXpath,
                "article with name" + name_article + "is present and was not deleted", 10);
    }

    public String getArticleTitle() {
        return this.waitForElementAndGetAttribute(ARTICLE_TITLE,"text", "did not find title ", 15);
    }

    public void clickArticleInSavedList(){
        this.waitForElementAndClick(ARTICLE_TITLE, "cant click on title", 5);
    }
}
