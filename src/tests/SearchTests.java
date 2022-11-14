package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelButtonDissappear();
    }


    @Test
    public void testCancelAndClearSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clearCancelSearch();
        searchPageObject.waitForCancelButtonDissappear();
    }

    @Test
    public void testCheckTextInSeachElement() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.asserSearchInputHasText("Search Wikipedia");
    }

    //Ex3: Тест: отмена поиска
    @Test
    public void testSearchCancel() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);

        mainPageObject.assertNumberOfElementsNotZero(By.id("org.wikipedia:id/page_list_item_title"), "not elements are found");
        mainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_container"), "can not find input", 15);
        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "titles are present", 5);
    }

    // Ex4*: Тест: проверка слов в поиске
    @Test
    public void testCheckWordInSearchResults() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);

        mainPageObject.checkWordInResults(By.id("org.wikipedia:id/page_list_item_title"), "titles does not contain word: ", "java");

    }


    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);

        int amount_result = searchPageObject.getAmountOfSearchArticles();
        Assert.assertTrue("results are less than 1", amount_result > 0);
    }

    @Test
    public void testAmountOfEmptySearch(){
        String searchLine = "shdjsafha";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }
}
