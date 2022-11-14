package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String title = articlePageObject.getArticleTitle();
        Assert.assertEquals("titles are not equal", "Java (programming language)", title);
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSomeString("Automation for Apps");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    //Ex6: Тест: assert title
    @Test
    public void testCheckTitleIsPresent() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        mainPageObject.assertElementPresent(By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text", "No title is found");
    }
}
