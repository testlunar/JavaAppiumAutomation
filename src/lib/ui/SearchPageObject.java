package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SKIP_BUTTON ,
            SEARCH_CONTAINER ,
            CANCEL_BUTTON ,
            SEARCH_RESULT_BY_SUBSTRING_TPL ,
            SEARCH_RESULT_BY_TITLE_DESCRIPTION ,
            SEARCH_RESULT_LOCATOR ,
            PAGE_LIST_ITEM_TITLE ,
            PAGE_LIST_ITEM_TITLE_ID ,
            EMPTY_LABEL ,
            SEARCH_INPUT ,
            SEARCH_INPUT2 ;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getElementByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_DESCRIPTION
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}",description);
    }
    //*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']/..//*[@text='High-level programming language']

    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SKIP_BUTTON,
                "Cannot find skip button", 5);
        this.waitForElementAndClick(SEARCH_CONTAINER,
                "Cannot find and click search container element", 5);
        this.waitForElementPresent(SEARCH_INPUT,
                "Cannot find search input field", 10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndClick(SEARCH_INPUT,
                "Cannot find and click search container element", 5);
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot send text " + search_line + " in search init element", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementPresent(search_result,
                "Cannot find search result", 15);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(CANCEL_BUTTON,
                "Cannot click cancel button", 5);
    }

    public void clearCancelSearch() {
        this.waitForElementAndClear(SEARCH_CONTAINER,
                "Cannot clear search area", 5);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(CANCEL_BUTTON,
                "Cannot find cancel button", 5);
    }

    public void waitForCancelButtonDissappear() {
        this.waitForElementNotPresent(CANCEL_BUTTON,
                "cancel button did not disappear", 5);
    }

    public void clickByArticleWithSomeString(String substring) {
        String search_result = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result,
                "Cannot find and click search result", 10);
    }

    public int getAmountOfSearchArticles() {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR, "no search result container by request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_LOCATOR);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(EMPTY_LABEL, "there is no empty label", 10);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementIsNotPresent(PAGE_LIST_ITEM_TITLE, "we found some results by request");
    }

    public void asserSearchInputHasText(String text) {
        this.assertElementHasText(SEARCH_INPUT, "text is not equal", text);
    }

    public void assertNumberOfArticlesNotZero() {
        this.assertNumberOfElementsNotZero(PAGE_LIST_ITEM_TITLE_ID, "not elements are found");
    }

    public void checkWordInResults(String text) {
        this.checkWordInResults(PAGE_LIST_ITEM_TITLE_ID, "titles does not contain word: " + text, text);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String search_result = getElementByTitleAndDescription(title,description);
        this.waitForElementPresent(search_result,
                "Cannot find search result with title: " + title + " ,description: " + description,
                15);
    }
}
