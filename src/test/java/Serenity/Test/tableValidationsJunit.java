package Serenity.Test;

import Serenity.steps.tableValidationsStep;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class tableValidationsJunit {

    @Managed
    WebDriver driver;

    @Steps
    tableValidationsStep table;


    @Test
    public void getItems()
    {
        //given
        table.launchContextures();

        //and
        table.acceptCookies(driver);

        //then
        table.getDifferentItems();
    }





}
