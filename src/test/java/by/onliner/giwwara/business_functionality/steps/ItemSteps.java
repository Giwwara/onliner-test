package by.onliner.giwwara.business_functionality.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import by.onliner.giwwara.business_functionality.pages.ItemPage;
import by.onliner.giwwara.testbase.AbstractPage;
import by.onliner.giwwara.testbase.Page;


public class ItemSteps extends AbstractPage {
    private String item = "";
    private String value = "";

    @And("^I click on the button To Basket$")
    public void iClickOnTheButtonToBasket() {
        Page.on(ItemPage.class).transferObjectBasket();
    }

    @When("^I click on the button In Basket$")
    public void iClickOnTheButtonInBasket() throws InterruptedException {
        Page.on(ItemPage.class).goToBasket();
    }


    @And("I save name of item")
    public void iSaveNameOfItem() {
        Page.on(ItemPage.class).setItem();
    }
}
