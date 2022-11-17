package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.*;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);

    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String error_message, String expectedText) {
        String actualText = waitForElementPresent(by, error_message).getAttribute("text");
        assertEquals(error_message, expectedText, actualText);
    }

    public void assertNumberOfElementsNotZero(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        Integer numberOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).size();
        assertTrue(error_message, numberOfElements > 0);
    }

    public void checkWordInResults(By by, String error_message, String word) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.withMessage(error_message + "\n");
        List<WebElement> numberOfElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        Iterator<WebElement> iter = numberOfElements.iterator();

        while (iter.hasNext()) {
            WebElement we = iter.next();
            assertTrue(error_message + " " + word, we.getText().toLowerCase().contains(word));
        }
    }

    public void swipeUp(int tmeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int y_start = (int) (size.height * 0.8);
        int y_end = (int) (size.height * 0.2);

        action.press(x, y_start).waitAction(tmeOfSwipe).moveTo(x, y_end).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToElement(By by, String errorMessage, int maxSwipes) {
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

    public void swipeElementToLeft(By by, String errorMessage, int maxSwipes) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(150).moveTo(left_x, middle_y).release().perform();
    }

    public int getAmountOfElements(By by) {
        List list = driver.findElements(by);
        return list.size();
    }

    public void assertElementIsNotPresent(By by, String errorMessage) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "Element " + by.toString() + " is supposed  to be not present";
            throw new AssertionError(default_message + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by, String attribute, String errorMessage){
        assertTrue(errorMessage,driver.findElement(by).getAttribute(attribute).isEmpty());
    }
}
