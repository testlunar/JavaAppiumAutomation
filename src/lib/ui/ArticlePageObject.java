package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "//*[@resource-id='pcs']/android.view.View[1]/android.widget.TextView[1]",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_ARTICLE_BUTTON = "org.wikipedia:id/page_save",
            SNACKBAR_BUTTON = "org.wikipedia:id/snackbar_action",
            MY_LIST_NAME = "//*[contains(@text,'Name of this list')]",
            MY_LIST_OK_BUTTON = "//*[contains(@text,'OK')]",
            ARTICLE_TITLE = "//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(By.xpath(TITLE), "title is not found", 15);
    }

    public String getArticleTitle() {
        WebElement element = waitForTitleElement();
        return element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToElement(By.xpath(FOOTER_ELEMENT), "Can not find the end of the article", 20);
    }


    public void addArticleToNewList(String name_folder) {
        this.waitForElementAndClick(By.id(SAVE_ARTICLE_BUTTON), "No Save button", 5);
        this.waitForElementAndClick(By.id(SNACKBAR_BUTTON), "no add to list button", 10);
        this.waitForElementAndSendKeys(By.xpath(MY_LIST_NAME), name_folder, "can not find input for name of list", 15);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "no OK button", 5);
    }

    public void addArticleToSomeList(String name_folder) {
        this.waitForElementPresent(By.id(SAVE_ARTICLE_BUTTON), "no save button",25);
        this.waitForElementAndClick(By.id(SAVE_ARTICLE_BUTTON), "could not click save button", 25);
        this.waitForElementAndClick(By.id(SNACKBAR_BUTTON), "no add to list button", 25);
        this.waitForElementAndClick(By.xpath("//*[contains(@text,'" + name_folder + "')]"), "no saved list with name " + name_folder, 5);
        this.waitForElementAndClick(By.id(SNACKBAR_BUTTON), "no VIEW the list button", 20);

    }

    public void openSavedListFromSnackBar() {
        this.waitForElementAndClick(By.id(SNACKBAR_BUTTON), "no VIEW the list button", 20);
    }

    public void assertTitleIsPresent() {
        this.assertElementPresent(By.xpath(ARTICLE_TITLE),"text","No title is found");
    }

}
