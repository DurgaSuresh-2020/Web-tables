package Serenity.Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.assertions.assertj.WebElementStateAssert.assertThat;

@DefaultUrl("https://www.contextures.com/xlsampledata01.html")
public class ContextureSampleDataPage extends PageObject {


    public String beforeXpath = "//table//tr[";
    public String tableRows = "//table//tr";

    public WebElementFacade linkDownloadExcel = $(By.xpath(".//a[contains(@href,'SampleData.zip')]"));
    @FindBy(xpath="//*[@id='save']") private WebElement buttonCookieAccept;
    @FindBy(xpath="//*[@id='gdpr-consent-notice']") private WebElement iframeCookiePopUp;


    public List<String> getTableRows(String xpath) {
        return findAll(xpath).stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public String tableCellValue(String Xpath)
    {
        WebElementFacade total = $(By.xpath(Xpath));
        return total.getText();
    }


    public void acceptCookie(WebDriver driver)
    {
        if(iframeCookiePopUp.isEnabled())
        {
            driver.switchTo().frame("gdpr-consent-notice");
            if (buttonCookieAccept.isDisplayed())
            {
                buttonCookieAccept.click();
            }
        }
    }

    public void downloadExcelFile()
    {
        linkDownloadExcel.click();
    }

    public List<String> getItems()
    {
        String afterXpath = "]/td[4]";
        List<String> items = new ArrayList<String>();
        System.out.println(getTableRows(tableRows).size());

        for (int i=2;i<=getTableRows(tableRows).size();i++)
        {
            items.add(tableCellValue(beforeXpath+i+afterXpath));
        }
        return items;
    }

    public List<String> getDistinctItems(List<String> list)
    {
        List<String> distinctElements = list.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctElements);
        return distinctElements;
    }


    public String getExpensiveUnitCost()
    {
        String afterXpath = "]/td[6]";
        Float ExpensivePrice = Float.parseFloat(tableCellValue(beforeXpath+"2"+afterXpath).replace(",",""));
        String expensivePrice = null;

        for (int i=3;i<=getTableRows(tableRows).size();i++)
        {
            Float PriceI=(Float.parseFloat(tableCellValue(beforeXpath+i+afterXpath).replace(",","")));
            if(PriceI>ExpensivePrice)
            {
                ExpensivePrice=PriceI;
                expensivePrice=tableCellValue(beforeXpath+i+afterXpath);
            }
        }System.out.println("Expensive unitcost - "+expensivePrice);
        return expensivePrice;
    }

    public String getExpensiveItem(String expensivePrice)
    {
        return tableCellValue("//p[contains(text(),'"+expensivePrice+"')]/parent::td//preceding-sibling::td[2]/p");
    }


    public List<String> getItemslessthanUnits(int value)
    {
        String afterXpath = "]/td[5]";
        List<String> items = new ArrayList<String>();
        for(int i=2;i<=getTableRows(tableRows).size();i++)
        {
            Integer unit = Integer.parseInt(tableCellValue(beforeXpath+i+afterXpath));
            if(unit<value)
            {
                items.add(tableCellValue("//p[text()='"+unit+"']/parent::td//preceding-sibling::td[1]/p"));
            }
        }
        System.out.println("Items with units less than "+value+" are "+getDistinctItems(items));
        return getDistinctItems(items);
    }

    public Integer getItemsforUnits(int ivalue,String svalue)
    {
        List<String> itemslessthanUnit = getItemslessthanUnits(ivalue);
        int counter = 0;
        for(int i=0;i<itemslessthanUnit.size();i++)
        {
            if(itemslessthanUnit.get(i)==svalue)
            {
                counter=+1;
            }
        }System.out.println("Number of "+svalue+" with units less than "+ivalue+" are "+counter);
        return counter;
    }

}
