package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

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
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.assertNumberOfArticlesNotZero();
        searchPageObject.clearCancelSearch();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    // Ex4*: Тест: проверка слов в поиске
    @Test
    public void testCheckWordInSearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.checkWordInResults("java");
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
