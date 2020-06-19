package by.onliner.giwwara.business_functionality.pages;

import by.onliner.giwwara.testbase.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"userbar\"]/div[3]")
    WebElement basketLogo;

    @FindBy(xpath = "//*[@class='fast-search__input']")
    WebElement fastSearchElement;

    @FindBy(xpath = "//*[contains(@class,'product__title-link')][1]")
    WebElement itemFastSearchElement;

    public BasketPage clickLogo() {
        click(basketLogo);
        return new BasketPage();
    }

    public MainPage fastSearch(String pizza) {
        sendKeys(fastSearchElement, pizza);
        return this;
    }

    public ItemPage iframeFastSearch(WebDriver driver) {
        WebElement iframe = driver.findElement(By.className("modal-iframe"));
        driver.switchTo().frame(iframe);
        click(itemFastSearchElement);
        driver.switchTo().defaultContent();
        return new ItemPage();
    }

}
