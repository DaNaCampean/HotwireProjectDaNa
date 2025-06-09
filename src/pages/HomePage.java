package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    //Locators section

    By flightsTabXPath = By.xpath("//div[@data-bdd = 'farefinder-option-flights' and @class = 'farefinder-option inactive']");

    // Methods section

    public void selectFlights(){
        doubleClick(flightsTabXPath);
    }
}
