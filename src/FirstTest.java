import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),"no Skip button",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"element by id not found",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),"Java","can not find input",15);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),"element by id not found",5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "element is present",5);

    }

    @Test
    public void compareArticleTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),"no Skip button",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"element by id not found",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),"Java","can not find input",15);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Object-oriented programming language')]"),"element by text not found",5);
        WebElement element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text = 'Object-oriented programming language']/..//android.widget.TextView[1]"),
                "element by text not found",15);
        String titleElement = element.getAttribute("text");
        Assert.assertEquals("not equal","Java (programming language)",titleElement);
    }

    @Test
    public void testCancelAndClearSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),"no Skip button",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"element by id not found",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),"Java","can not find input",15);
        waitForElementAndClear(By.id("org.wikipedia:id/search_container"),"can not find input",15);
    }

    @Test
    public void testCheckTextInSeachElement() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),"no Skip button",5);
        waitForElementPresent(By.id("org.wikipedia:id/search_container"),"element by id not found",5);
        assertElementHasText(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']/android.widget.TextView"),"text is not equal","Search Wikipedia");
    }

    //Ex3: Тест: отмена поиска
    @Test
    public void testSearchCancel() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),"no Skip button",5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"element by id not found",5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),"Java","can not find input",15);

        assertNumberOfElementsNotZero(By.id("org.wikipedia:id/page_list_item_title"),"not elements are found");
        waitForElementAndClear(By.id("org.wikipedia:id/search_container"),"can not find input",15);
        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_title"), "titles are present",5);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by,error_message,5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by,error_message,timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by,error_message,timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by,error_message,timeOutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String error_message, String expectedText){
       String actualText = waitForElementPresent(by, error_message).getAttribute("text");
       Assert.assertEquals(error_message, expectedText, actualText);
    }

    private void assertNumberOfElementsNotZero(By by, String error_message){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        Integer numberOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).size();
        Assert.assertTrue(error_message, numberOfElements>0);
    }

}
