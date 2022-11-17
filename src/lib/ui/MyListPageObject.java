package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    private static final String
            ARTICLE_XPATH_BY_TEXT = "//*[contains(@text,'{SUBSTRING}')]",
            LIST_TITLE = "org.wikipedia:id/item_title",
            ARTICLE_TITLE = "org.wikipedia:id/page_list_item_title";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleXpath(String name) {
        return ARTICLE_XPATH_BY_TEXT.replace("{SUBSTRING}", name);
    }

    public void swipeToLeftArticleToDelete(String name_article) {
        String articleNameXpath = getArticleXpath(name_article);

        checkMyListAppeared();
        this.swipeElementToLeft(By.xpath(articleNameXpath),
                "can not swipe left", 5);
    }

    public void checkMyListAppeared() {
        this.waitForElementPresent(By.id(LIST_TITLE),
                "element with name of list title not found", 15);
    }

    public void checkListItemDissapeared(String name_article) {
        String articleNameXpath = getArticleXpath(name_article);
        this.waitForElementNotPresent(By.xpath(articleNameXpath),
                "article with name" + name_article + "is present and was not deleted", 10);
    }

    public String getArticleTitle() {
        return this.waitForElementAndGetAttribute(
                By.id(ARTICLE_TITLE),"text", "did not find title ", 15);
    }

    public void clickArticleInSavedList(){
        this.waitForElementAndClick(By.id(ARTICLE_TITLE), "cant click on title", 5);
    }
}
