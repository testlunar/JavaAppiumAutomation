package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyListAndOpenSavedList("My list");

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.swipeToLeftArticleToDelete(article_title);
        myListPageObject.checkListItemDissapeared(article_title);
    }

    //Ex5: Тест: сохранение двух статей
    @Test
    public void saveTwoArticleToMyList() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        //добавление 1 статьи
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "No Save button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no add to list button", 5);
        String listName = "Programming";
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Name of this list')]"), listName, "can not find input for name of list", 15);
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]"), "no OK button", 5);

        //добавление 2й статьи
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "element not found", 20);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Python", "can not find input", 20);
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'General-purpose programming language')]"), "element by text not found", 5);
        mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'General-purpose programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "No Save button", 10);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no add to list button", 15);

        //Добавление в уже существующий лист
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'" + listName + "')]"), "no saved list with name " + listName, 5);

        //Открытие сохраненного листа
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no VIEW the list button", 15);
        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/item_title"), "element title not found", 20);
        //удаление статьи и проверка
        mainPageObject.swipeElementToLeft(By.xpath("//*[contains(@text,'Java (programming language)')]"), "can not swipe left", 5);
        mainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "element in MyList is present and was not deleted", 10);
        String title_in_list = mainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "did not find title ",
                15
        );
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_title"), "cant click on title", 5);
        String title_whenOpen = mainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text",
                "did not find title of element after rotation",
                15
        );
        Assert.assertEquals(
                "title is different after 2rotation", title_in_list, title_whenOpen);

    }
}
