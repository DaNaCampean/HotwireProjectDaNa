package pages;

import base.BasePageDriverInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FlightsPage extends BasePageDriverInitialization {

    Actions actions = new Actions(driver);// Instantiate the Actions class needed passengers popup


    // Selectors section
    By findAFlightTextXPath = By.xpath("//span[text()='Find a flight']"); //"Find a Flight" text from the button
    By searchFlyFromTextField = By.xpath("//div[@class='col-xs-12 margin-top-6']/div[@class='location-typeahead']/div[contains(@class, 'hw-form-group form-group floating-label')]/input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis']"); // Fly from textField
    By searchFlyToTextField = By.xpath("//div[@class='col-xs-12 margin-top-4']/div[@class='location-typeahead']/div[contains(@class, 'hw-form-group form-group floating-label')]/input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis']"); // Fly to textField
    By dateFormXPath = By.xpath("   //div[@data-bdd='farefinder-flight-startdate-input']"); // date form
    By fromToFieldDropDownXPath = By.xpath("//ul[@class='dropdown-menu large']"); // Fly From dropdown
    By fromFieldSelectLaxXPath = By.xpath("//li/a/b[contains(text(), 'LAX')]");// select LAX item, no matter where in the dropdown is
    By toFieldSelectOtpXPath = By.xpath("//li[a[contains(text(), 'OTP')]]");// select OTP item, no matter where in the dropdown is
    String setDateXPath = "//td[@aria-label='%s']"; //  date string. %s is there for creating a custom xpath for reading the date after selection

    By readDepartingDateXPath = By.xpath("//input[@name='startDate']");
    By readReturningDateXPath = By.xpath("//input[@name='endDate']");

    By passengersDropDownXPath = By.xpath("//input[@name='farefinder-occupant-selector-flight']");
    By passengersDropXPath = By.xpath("//span[@class='guest-picker__popover Tooltip Tooltip--bottom Tooltip--popover Tooltip--lg in fade']");
    By selectAdultsXPath = By.xpath("//*[name()='svg' and @data-id='SVG_PLUS__16']");// 2x Adults
    By selectDonePassengersXPath = By.xpath("//span[@class='btn__label' and text()='Done']"); // DONE button
    By readPassengersXPath = By.xpath("//input[@name='farefinder-occupant-selector-flight']");
    By searchFlightsXPath = By.xpath("//div[@class = 'submit-button']");

    By flightsResults = By.xpath("//h3[contains(text(),'Recommended departing flights')]");

    // Methods section

    public String getTextSearchButton() {
        WebElement buttonText = driver.findElement(findAFlightTextXPath);
        return buttonText.getText();
    }

    public String FillFlyFromTextField() {
        WebElement searchTextField = driver.findElement(searchFlyFromTextField);
        searchTextField.sendKeys("LAX");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromToFieldDropDownXPath));
        driver.findElement(fromFieldSelectLaxXPath).click();
        return searchTextField.getDomAttribute("value");
    }

    public String FillFlyToTextField() {
        WebElement searchTextField = driver.findElement(searchFlyToTextField);
        searchTextField.sendKeys("Bucharest");
        Utils.implicitlyWaitFiveSeconds();
        driver.findElement(fromToFieldDropDownXPath);
        driver.findElement(toFieldSelectOtpXPath).click();
        return searchTextField.getDomAttribute("value");
    }

    public List<String> datesChoosing(LocalDate departingDate, LocalDate returningDate) {
        // create Date WebElements
        WebElement dateForm = driver.findElement(dateFormXPath);
        //click on date form
        dateForm.click();

        //  // format dates to be able to select them in the UI
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String departingDateString = Utils.formatDates(departingDate, "MMMM d, yyyy");
        String returningDateString = Utils.formatDates(returningDate, "MMMM d, yyyy");

       //   By setDepartingDateXPath = By.xpath("//td[@aria-label='" + departingDateString + "']");
//        By setReturningDateXPath= By.xpath("//td[@aria-label='" + returningDateString + "']");

        driver.findElement(By.xpath(String.format(setDateXPath, departingDateString))).click(); //creating the xpath for date, with partial string declared at the top: setDateXPath = "//td[@aria-label='%s']";
        driver.findElement(By.xpath(String.format(setDateXPath, returningDateString))).click(); //creating the xpath for date, with partial string declared at the top: setDateXPath = "//td[@aria-label='%s']";

        String startDate = driver.findElement(readDepartingDateXPath).getDomAttribute("value"); // get the value of the value Attribute
        String endDate = driver.findElement(readReturningDateXPath).getDomAttribute("value");
        return Arrays.asList(startDate, endDate); //create an array list with start and end dates, for using it in the assert

    }

    public String passengersSelection() {
        WebElement passengers = driver.findElement(passengersDropDownXPath);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // passengers.click();
        actions.click(passengers).perform();   // dropdown passengers
        driver.findElement(passengersDropXPath);
        driver.findElement(selectAdultsXPath).click(); // 2x Adults
        driver.findElement(selectDonePassengersXPath).click(); // DONE button

        return driver.findElement(readPassengersXPath).getDomAttribute("value");


    }

    public String findAFlight() {
        //search flights

        WebElement searchFlight = driver.findElement(searchFlightsXPath);
        Utils.implicitlyWaitFiveSeconds();
        driver.manage().deleteAllCookies(); // Deletes all the cookies
        searchFlight.click();
        Utils.waitForElementVisible(driver, flightsResults,15);
        return driver.findElement(flightsResults).getText();
    }

    public String findAFlight2() {
        //search flights
        By flightsResults = By.xpath("//h1[@class='uitk-heading uitk-heading-5 uitk-spacing uitk-spacing-margin-blockend-two']");


        return driver.findElement(flightsResults).getText();
    }

   // <h1 class="uitk-heading uitk-heading-5 uitk-spacing uitk-spacing-margin-blockend-two">Sorry, we're having a problem on our end.</h1>
}









