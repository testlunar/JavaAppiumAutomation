import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\user\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "element by id not found", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "element is present", 5);

    }

    @Test
    public void compareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        WebElement element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        String titleElement = element.getAttribute("text");
        Assert.assertEquals("not equal", "Java (programming language)", titleElement);
    }

    @Test
    public void testCancelAndClearSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementAndClear(By.id("org.wikipedia:id/search_container"), "can not find input", 15);
    }

    @Test
    public void testCheckTextInSeachElement() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementPresent(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        assertElementHasText(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']/android.widget.TextView"), "text is not equal", "Search Wikipedia");
    }

    //Ex3: Тест: отмена поиска
    @Test
    public void testSearchCancel() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);

        assertNumberOfElementsNotZero(By.id("org.wikipedia:id/page_list_item_title"), "not elements are found");
        waitForElementAndClear(By.id("org.wikipedia:id/search_container"), "can not find input", 15);
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "titles are present", 5);
    }

    // Ex4*: Тест: проверка слов в поиске
    @Test
    public void testCheckWordInSearchResults() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);

        checkWordInResults(By.id("org.wikipedia:id/page_list_item_title"), "titles does not contain word: ", "java");

    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Appium", "can not find input", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Automation for Apps')]"), "element by text not found", 5);
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Automation for Apps']/..//android.widget.TextView[1]"),
                "element by text not found", 15);

        swipeUpToElement(By.xpath("//*[@text='View article in browser']"), "Can not find the end of the article", 20);

    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "No Save button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no add to list button", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Name of this list')]"), "MyList", "can not find input for name of list", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]"), "no OK button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no VIEW the list button", 10);
        waitForElementPresent(By.id("org.wikipedia:id/item_title"), "element title not found", 15);
        swipeElementToLeft(By.xpath("//*[contains(@text,'Java (programming language)')]"), "can not swipe left", 5);
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "element in MyList is present and was not deleted", 10);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        String searchLine = "Linkin Park Discography";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "can not find input", 15);
        String search_result_locator = "org.wikipedia:id/search_results_list";
        waitForElementPresent(By.id(search_result_locator), "no search result container by request " + searchLine, 15);

        int amount_result = getAmountOfElements(By.id(search_result_locator));
        Assert.assertTrue("results are less than 1", amount_result > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        String searchLine = "shdjsafha";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "can not find input", 15);
        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_label = "//*[contains(@text,'No results')]";
        waitForElementPresent(By.xpath(empty_label), "some results were found but should not", 10);
        assertElementIsNotPresent(By.xpath(search_result_locator), "we found some results by request " + searchLine);
    }

    @Test
    public void testChangesScreenOrientationTestResults() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        String searchLine = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "can not find input", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "element by text " + searchLine + " not found", 20);
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text",
                "did not find title of element before rotation",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text",
                "did not find title of element after rotation",
                15
        );
        Assert.assertEquals(
                "title is different after rotation", title_before_rotation, title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after2rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text",
                "did not find title of element after rotation",
                15
        );
        Assert.assertEquals(
                "title is different after 2rotation", title_before_rotation, title_after2rotation);
    }

    @Test
    public void searchArticleInBackground() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementPresent(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        driver.runAppInBackground(2);
        waitForElementPresent(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found after background", 5);

    }

    @Test
    public void saveTwoArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"), "no Skip button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "element by id not found", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "can not find input", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"), "element by text not found", 5);
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        //добавление 1 статьи
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "No Save button", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no add to list button", 5);
        String listName = "Programming";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Name of this list')]"), listName, "can not find input for name of list", 15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]"), "no OK button", 5);

        //добавление 2й статьи
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "element not found", 20);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Python", "can not find input", 20);
        waitForElementAndClick(By.xpath("//*[contains(@text,'General-purpose programming language')]"), "element by text not found", 5);
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'General-purpose programming language']/..//android.widget.TextView[1]"),
                "element by text not found", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "No Save button", 10);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no add to list button", 15);

        //Добавление в уже существующий лист
        waitForElementAndClick(By.xpath("//*[contains(@text,'" + listName + "')]"), "no saved list with name " + listName, 5);

        //Открытие сохраненного листа
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "no VIEW the list button", 15);
        waitForElementPresent(By.id("org.wikipedia:id/item_title"), "element title not found", 20);
        //удаление статьи и проверка
        swipeElementToLeft(By.xpath("//*[contains(@text,'Java (programming language)')]"), "can not swipe left", 5);
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Java (programming language)')]"), "element in MyList is present and was not deleted", 10);
        String title_in_list = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "did not find title ",
                15
        );
        waitForElementAndClick(By.id("org.wikipedia:id/page_list_item_title"),"cant click on title",5);
        String title_whenOpen = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']/../android.widget.TextView[1]"),
                "text",
                "did not find title of element after rotation",
                15
        );
        Assert.assertEquals(
                "title is different after 2rotation", title_in_list, title_whenOpen);

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String error_message, String expectedText) {
        String actualText = waitForElementPresent(by, error_message).getAttribute("text");
        Assert.assertEquals(error_message, expectedText, actualText);
    }

    private void assertNumberOfElementsNotZero(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        Integer numberOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).size();
        Assert.assertTrue(error_message, numberOfElements > 0);
    }

    private void checkWordInResults(By by, String error_message, String word) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        List<WebElement> numberOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        Iterator<WebElement> iter = numberOfElements.iterator();

        while (iter.hasNext()) {
            WebElement we = iter.next();
            Assert.assertTrue(error_message + " " + word, we.getText().toLowerCase().contains(word));
        }
    }

    protected void swipeUp(int tmeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action.press(x, y_start).waitAction(tmeOfSwipe).moveTo(x, y_end).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;

        driver.findElements(by);
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "could not find element by swipe. \n" + errorMessage);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;

        }
    }

    protected void swipeElementToLeft(By by, String errorMessage, int maxSwipes) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(150).moveTo(left_x, middle_y).release().perform();
    }

    private int getAmountOfElements(By by) {
        List list = driver.findElements(by);
        return list.size();
    }

    private void assertElementIsNotPresent(By by, String errorMessage) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "Element " + by.toString() + " is supposed  to be not present";
            throw new AssertionError(default_message + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }
}
