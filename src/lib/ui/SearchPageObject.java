package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
            SKIP_BUTTON = "//*[contains(@text,'SKIP')]",
            SEARCH_CONTAINER = "org.wikipedia:id/search_container",
            CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_LOCATOR = "org.wikipedia:id/search_results_list",
            PAGE_LIST_ITEM_TITLE = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            PAGE_LIST_ITEM_TITLE_ID = "org.wikipedia:id/page_list_item_title",
            EMPTY_LABEL = "//*[contains(@text,'No results')]",
            SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT2 = "//*[@resource-id='org.wikipedia:id/search_container']/android.widget.TextView";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON),
                "Cannot find skip button", 5);
        this.waitForElementAndClick(By.id(SEARCH_CONTAINER),
                "Cannot find and click search container element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INPUT),
                "Cannot find search input field", 10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndClick(By.xpath(SEARCH_INPUT),
                "Cannot find and click search container element", 5);
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Cannot send text " + search_line + " in search init element", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result),
                "Cannot find search result", 15);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(By.id(CANCEL_BUTTON),
                "Cannot click cancel button", 5);
    }

    public void clearCancelSearch() {
        this.waitForElementAndClear(By.id(SEARCH_CONTAINER),
                "Cannot clear search area", 5);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(By.id(CANCEL_BUTTON),
                "Cannot find cancel button", 5);
    }

    public void waitForCancelButtonDissappear() {
        this.waitForElementNotPresent(By.id(CANCEL_BUTTON),
                "cancel button did not disappear", 5);
    }

    public void clickByArticleWithSomeString(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result),
                "Cannot find and click search result", 10);
    }

    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(By.id(SEARCH_RESULT_LOCATOR), "no search result container by request", 15);
        return this.getAmountOfElements(By.id(SEARCH_RESULT_LOCATOR));
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(By.xpath(EMPTY_LABEL), "there is no empty label", 10);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementIsNotPresent(By.xpath(PAGE_LIST_ITEM_TITLE), "we found some results by request");
    }

    public void asserSearchInputHasText(String text) {
        this.assertElementHasText(By.xpath(SEARCH_INPUT), "text is not equal", text);
    }

    public void assertNumberOfArticlesNotZero() {
        this.assertNumberOfElementsNotZero(By.id(PAGE_LIST_ITEM_TITLE_ID), "not elements are found");
    }

    public void checkWordInResults(String text) {
        this.checkWordInResults(By.id(PAGE_LIST_ITEM_TITLE_ID), "titles does not contain word: " + text, text);
    }
}
