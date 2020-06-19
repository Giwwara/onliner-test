package by.onliner.giwwara.business_functionality.pages;

import by.onliner.giwwara.testbase.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasketPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(text(),'Ваша корзина пуста')]")
    WebElement textOfBlank;

    @FindBy(xpath = "//*[contains(@class, 'cart-form__description')]/a[@href]")
    WebElement itemTitleInBasket;

    @FindBy(xpath = "//*[contains(text(), 'Оформить заказ')]")
    WebElement buttonOrderElement;

    @FindBy(xpath = "//*[contains(@class, 'cart-form__offers-item_secondary')]")
    WebElement invisibleCart;

    @FindBy(xpath = "//*[@class='cart-form__control']/a[contains(@class, 'button-style_auxiliary')]")
    WebElement trashElement;

    @FindBy(xpath = "//*[contains(text(),'Вернуть товар')]")
    WebElement removeObjectElement;

    @FindBy(xpath = "//*[contains(@class, 'cart-form__button_increment')]")
    WebElement addObjectElement;

    @FindBy(xpath = "//*[contains(text(),'2 товара на сумму')]")
    WebElement sumObjectElement;


    public boolean findText() {
        return isElementDisplayed(textOfBlank);
    }

    public String pageBasketTitle() {
        return getText(itemTitleInBasket);
    }

    public boolean buttonOrder() {
        return buttonOrderElement.isEnabled();
    }

    public BasketPage uncoverInvisibleTrash() {
        moveMouse(invisibleCart);
        clickHideElement(trashElement);
        return this;
    }

    public BasketPage removeObject() {
        click(removeObjectElement);
        return this;
    }

    public BasketPage addObject() {
        moveMouse(invisibleCart);
        clickHideElement(addObjectElement);
        return this;
    }

    public boolean sumObject() {
        return sumObjectElement.isDisplayed();
    }


}
