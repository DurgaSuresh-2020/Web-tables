package Serenity.Test;
import Serenity.steps.tableValidationsStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class tableValidationsSerenity {
    @Managed
    WebDriver driver;

    @Steps
    tableValidationsStep table;

    @Given("I launch Contexure Page")
    public void launch_Contexture_Page()
    {
        table.launchContextures();;
    }

    @And("accept cookies if needed")
    public void accept_Cookies()
    {
        table.acceptCookies(driver);
    }

    @Then("fetch different items available in the table")
    public void get_different_items()
    {
        table.getDifferentItems();
    }

    @Then("fetch items with units less than {int}")
    public void get_items_less_than_units(int units)
    {
        table.getItemsLessThanUnits(units);
    }

    @Then("fetch {string} with units less than 5 }")
    public void get_items_for_units(String items )
    {
        table.getItemsforUnits(5,items);
    }

    @Then("fetch {string} with units less than {int}")
    public void fetch_with_units_less_than(String item, Integer units) {
        table.getItemsforUnits(units,item);
    }


    @Then("get the most expensive item in the table")
    public void get_expensive_item()
    {
        table.getExpensiveItem();
    }



}



