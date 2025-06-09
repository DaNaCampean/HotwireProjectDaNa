package pages;


import base.BasePageDriverInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePageDriverInitialization {
    //Locators section
    By flightsTabXPath = By.xpath("//div[@data-bdd = 'farefinder-option-flights' and @class = 'farefinder-option inactive']");

    // Methods section
    public String getUrl(){
        return driver.getCurrentUrl();
    }


    public void selectFlights(){
        WebElement flightsClick = driver.findElement(flightsTabXPath);
        // Instantiate the Actions class needed for double click
        Actions actions = new Actions(driver);

        // Perform double-click
        actions.doubleClick(flightsClick).perform();
    }
}
