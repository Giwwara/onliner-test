package by.onliner.giwwara.business_functionality.steps;

import by.onliner.giwwara.business_functionality.pages.BasketPage;
import by.onliner.giwwara.business_functionality.pages.ItemPage;
import by.onliner.giwwara.context.DriverProvider;
import by.onliner.giwwara.testbase.AbstractPage;
import by.onliner.giwwara.testbase.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class BasketSteps extends AbstractPage {

    private String item;
    private String object;


    @Then("^I should see text about the blanked basket$")
    public void iShouldSeeTextAboutTheBlankedBasket() {
        Assert.assertTrue(Page.on(BasketPage.class).findText());
    }

    @And("^I see button for a pay$")
    public void iSeeButtonForAPay() {
        Assert.assertTrue(Page.on(BasketPage.class).buttonOrder());
    }

    @Then("^I delete object from the basket$")
    public void iDeleteObjectFromTheBasket() {
        Page.on(BasketPage.class).uncoverInvisibleTrash();
    }

    @And("^I refresh site$")
    public void iRefreshSite() {
        DriverProvider.webDriver().navigate().refresh();
    }

    @Given("I save name of the object")
    public void iSaveNameOfTheObject() {
        this.object = Page.on(BasketPage.class).pageBasketTitle();
    }


    @And("I click on the Return button")
    public void iClickOnTheReturnButton() {
        Page.on(BasketPage.class).removeObject();
    }

    @And("I see the same object in the basket")
    public void iSeeTheSameObjectInTheBasket() {
        Assert.assertEquals(object, Page.on(BasketPage.class).pageBasketTitle());
    }

    @Then("I add the second object")
    public void iAddTheSecondObject() {
        Page.on(BasketPage.class).addObject();
    }

    @Then("I compare {string} name of Item and name of Object")
    public void iCompareNameOfItemAndNameOfObject(String signOfItem) {
        Assert.assertEquals(Page.on(ItemPage.class).getItem().replaceAll(signOfItem, "").trim(), object);
    }

    @Then("I see an inscription of the two objects")
    public void iSeeAnInscriptionOfTheObjects() {
        Assert.assertTrue(Page.on(BasketPage.class).sumObject());
    }
}
