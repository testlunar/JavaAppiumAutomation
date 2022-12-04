package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE ,
            FOOTER_ELEMENT ,
            SAVE_ARTICLE_BUTTON,
            SNACKBAR_BUTTON,
            MY_LIST_NAME ,
            MY_LIST_OK_BUTTON ,
            ARTICLE_TITLE ;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(TITLE, "title is not found", 15);
    }

    public String getArticleTitle() {
        WebElement element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return element.getAttribute("text");
        }else{
            return element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(FOOTER_ELEMENT, "Can not find the end of the article", 40);
        }else{
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Can not find the end of the article",40);
        }
    }


    public void addArticleToNewList(String name_folder) {
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "No Save button", 5);
        this.waitForElementAndClick(SNACKBAR_BUTTON, "no add to list button", 10);
        this.waitForElementAndSendKeys(MY_LIST_NAME, name_folder, "can not find input for name of list", 15);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "no OK button", 5);
    }

    public void addArticleToSomeList(String name_folder) {
        this.waitForElementPresent(SAVE_ARTICLE_BUTTON, "no save button",25);
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "could not click save button", 25);
        this.waitForElementAndClick(SNACKBAR_BUTTON, "no add to list button", 25);
        this.waitForElementAndClick("xpath://*[contains(@text,'" + name_folder + "')]", "no saved list with name " + name_folder, 5);
        this.waitForElementAndClick(SNACKBAR_BUTTON, "no VIEW the list button", 20);

    }

    public void openSavedListFromSnackBar() {
        this.waitForElementAndClick(SNACKBAR_BUTTON, "no VIEW the list button", 20);
    }

    public void assertTitleIsPresent() {
        this.assertElementPresent(ARTICLE_TITLE,"text","No title is found");
    }

    public void addArticleToMySaved(){
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON,"can not find option to add article to reading list",5);
    }

}
