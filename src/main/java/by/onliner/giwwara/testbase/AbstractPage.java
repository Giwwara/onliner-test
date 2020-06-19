package by.onliner.giwwara.testbase;

import by.onliner.giwwara.context.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected void click(WebElement element){

            waitForElementIsVisible(element, 10);
            waitForElementIsEnabled(element, 10);
            isElementDisplayed(element);
            element.click();
            waitForPageFullyLoad();

    }

    protected void sendKeys(WebElement element, String text){
        waitForElementIsVisible(element, 10);
        waitForElementIsEnabled(element, 10);
        isElementDisplayed(element);
        element.sendKeys(text);

    }

    protected String getText(WebElement element){
        waitForElementIsVisible(element, 10);
         waitForElementIsEnabled(element, 10);
        isElementDisplayed(element);
        return element.getText();

    }

    protected void waitForElementIsEnabled(WebElement element, long timeOutInSeconds) {
        new WebDriverWait(DriverProvider.webDriver(), timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitForElementIsVisible(WebElement element, long timeOutInSeconds) {
        new WebDriverWait(DriverProvider.webDriver(), timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPageFullyLoad() {
        new WebDriverWait(DriverProvider.webDriver(), 120).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverProvider.webDriver(), 5);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    protected void clickHideElement(WebElement element){
        waitForElementIsEnabled(element, 10);
        waitForElementIsVisible(element, 10);
        JavascriptExecutor js = (JavascriptExecutor)DriverProvider.webDriver();
        js.executeScript("arguments[0].click();", element);
        waitForPageFullyLoad();

    }

    protected void moveMouse(WebElement element){
        waitForElementIsVisible(element, 10);
        waitForElementIsEnabled(element, 10);
        Actions actions = new Actions(DriverProvider.webDriver());
        actions.moveToElement(element).perform();
    }
}

