package Serenity.steps;

import Serenity.Pages.ContextureSampleDataPage;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class tableValidationsStep {

    ContextureSampleDataPage dataPage;

    @Step
    public void launchContextures(){
        dataPage.open();
        assertThat(dataPage.getTitle().contains("Excel Sample Data"));
    }

    @Step
    public void acceptCookies(WebDriver driver){
        dataPage.acceptCookie(driver);
    }

    @Step
    @Screenshots(disabled=true)
    public void getDifferentItems()
    {
        List<String> items =  dataPage.getDistinctItems(dataPage.getItems());
        log("There are "+items.size()+" different items listed and they are -"+items);

    }

    @Step
    @Screenshots(disabled=true)
    public void getItemsLessThanUnits(Integer units)
    {
        List<String> items = dataPage.getItemslessthanUnits(units);
        log("There are "+items.size()+" different items which have units less than - "+units+" units and they are -"+items);
    }

    @Step
    @Screenshots(disabled=true)
    public void getItemsforUnits(int units,String item)
    {
        Integer NoOfItems = dataPage.getItemsforUnits(units,item);
        log("There are "+NoOfItems+" number of - "+item+" which have units less than - "+units);
    }

    @Step
    @Screenshots(disabled=true)
    public void getExpensiveItem()
    {
        String expensiveUnitCost = dataPage.getExpensiveUnitCost();
        String expensiveItem = dataPage.getExpensiveItem(expensiveUnitCost);
        log("The most expensive item in the table is - "+expensiveItem+" which has unit cost of - "+expensiveUnitCost);
    }

    @Step
    @Screenshots(disabled=true)
    public void log(String message) {}

}
