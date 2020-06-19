package by.onliner.giwwara.business_functionality.pages;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import by.onliner.giwwara.testbase.AbstractPage;

public class ItemPage extends AbstractPage {

    private String item;

    @FindBy(className = "catalog-masthead__title")
    WebElement itemPageTitle;

    @FindBy(xpath = "//*[contains (@class, \"product-aside__item-button\")][1]")
    WebElement itemToBasketElement;

    @FindBy(xpath = "//*[contains (@class, \"product-aside__item-button_checked\")]")
    WebElement itemInBasketElement;


    public String pageObjectTitle() {
        return getText(itemPageTitle);
    }

    public ItemPage transferObjectBasket() {
        click(itemToBasketElement);
        return this;
    }

    public BasketPage goToBasket() throws InterruptedException {
        Thread.sleep(1000);
        click(itemInBasketElement);
        return new BasketPage();
    }

    public void setItem() {
        Serenity.setSessionVariable("item").to(pageObjectTitle());
    }

    public String getItem() {
        return Serenity.sessionVariableCalled("item");
    }
}
