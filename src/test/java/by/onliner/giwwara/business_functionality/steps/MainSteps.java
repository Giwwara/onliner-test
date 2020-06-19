package by.onliner.giwwara.business_functionality.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import by.onliner.giwwara.business_functionality.pages.MainPage;
import by.onliner.giwwara.testbase.AbstractPage;
import by.onliner.giwwara.context.DriverProvider;
import by.onliner.giwwara.testbase.Page;
import by.onliner.giwwara.util.PropertyProvider;


public class MainSteps extends AbstractPage {

    @Given("^I go to the main page$")
    public void iGoToTheMainPage() {
        DriverProvider.webDriver().get(PropertyProvider.getProperty("URL"));
    }

    @When("^I click on the basket logo$")
    public void iClickOnTheBasketLogo() {
        Page.on(MainPage.class).clickLogo();
    }

    @When("^I write \"([^\"]*)\" into fast search float$")
    public void iWriteIntoFastSearchFloat(String item){
        Page.on(MainPage.class).fastSearch(item);
    }

    @Then("^I chose the first item in the list$")
    public void iChoseTheFirstItemInTheList() {
        Page.on(MainPage.class).iframeFastSearch(DriverProvider.webDriver());
    }
}

