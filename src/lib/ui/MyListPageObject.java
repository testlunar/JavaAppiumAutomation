package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    private static final String
            ARTICLE_NAME = "//*[contains(@text,'{SUBSTRING}')]",
    LIST_TITLE = "org.wikipedia:id/item_title";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleName(String name) {
        return ARTICLE_NAME.replace("{SUBSTRING}", name);
    }

    public void swipeToLeftArticleToDelete(String name_article) {
        String articleNameXpath = getArticleName(name_article);

        checkMyListAppeared();
        this.swipeElementToLeft(By.xpath(articleNameXpath),
                "can not swipe left", 5);
    }

    public void checkMyListAppeared(){
        this.waitForElementPresent(By.id(LIST_TITLE),
                "element with name of list title not found", 15);
    }

    public void checkListItemDissapeared(String name_article){
        String articleNameXpath = getArticleName(name_article);
        this.waitForElementNotPresent(By.xpath(articleNameXpath),
                "article with name" + name_article + "is present and was not deleted", 10);
    }
}
