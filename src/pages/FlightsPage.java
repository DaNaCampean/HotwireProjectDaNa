package pages;

import base.BasePage;
import utils.DateUtils;
import utils.WaitUtils;

import org.openqa.selenium.By;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static utils.DriverUtils.driver;

public class FlightsPage extends BasePage {

    // Selectors section
    By findAFlightTextXPath = By.xpath("//span[text()='Find a flight']"); //"Find a Flight" text from the button
    By searchFlyFromTextField = By.xpath("//div[@class='col-xs-12 margin-top-6']/div[@class='location-typeahead']/div[contains(@class, 'hw-form-group form-group floating-label')]/input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis']"); // Fly from textField
    By searchFlyToTextField = By.xpath("//div[@class='col-xs-12 margin-top-4']/div[@class='location-typeahead']/div[contains(@class, 'hw-form-group form-group floating-label')]/input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis']"); // Fly to textField
    By dateFormXPath = By.xpath("   //div[@data-bdd='farefinder-flight-startdate-input']"); // date form
    By fromToFieldDropDownXPath = By.xpath("//ul[@class='dropdown-menu large']"); // Fly From dropdown
    By fromFieldSelectLaxXPath = By.xpath("//li/a/b[contains(text(), 'LAX')]");// select LAX item, no matter where in the dropdown is
    By toFieldSelectOtpXPath = By.xpath("//li[a[contains(text(), 'OTP')]]");// select OTP item, no matter where in the dropdown is
    By readDepartingDateXPath = By.xpath("//input[@name='startDate']");
    By readReturningDateXPath = By.xpath("//input[@name='endDate']");
    By passengersDropDownXPath = By.xpath("//input[@name='farefinder-occupant-selector-flight']");
    By selectAdultsXPath = By.xpath("//*[name()='svg' and @data-id='SVG_PLUS__16']");// 2x Adults
    By selectDonePassengersXPath = By.xpath("//span[@class='btn__label' and text()='Done']"); // DONE button
    By readPassengersXPath = By.xpath("//input[@name='farefinder-occupant-selector-flight']");
    By searchFlightsXPath = By.xpath("//div[@class = 'submit-button']");
    By flightsResults = By.xpath("//h3[contains(text(),'Recommended departing flights')]"); //recommended flights
    By flightsResultsHavingProblem = By.xpath("//h1[@class='uitk-heading uitk-heading-5 uitk-spacing uitk-spacing-margin-blockend-two']"); //Sorry, we're having a problem on our end.

    String setDateXPath = "//td[@aria-label='%s']"; //  date string. %s is there for creating a custom xpath for reading the date after selection

    // Methods section

    public String getTextSearchButton() {
        return getText(findAFlightTextXPath);
    }

    public String FillFlyFromTextField() {
        fillFields(searchFlyFromTextField,"LAX");
        WaitUtils.implicitlyWaitFiveSeconds();
        isVisible(fromToFieldDropDownXPath);
        clickWait(fromFieldSelectLaxXPath);

        return getAttributeString(searchFlyFromTextField,"value");
    }

    public String FillFlyToTextField() {
        fillFields(searchFlyToTextField,"Bucharest");
        WaitUtils.implicitlyWaitFiveSeconds();
        isVisible(fromToFieldDropDownXPath);
        clickWait(toFieldSelectOtpXPath);

        return getAttributeString(searchFlyToTextField,"value");
    }

    public List<String> datesChoosing(LocalDate departingDate, LocalDate returningDate) {
        clickWait(dateFormXPath);
        // format dates to be able to select them in the UI
        String departingDateString = DateUtils.formatDates(departingDate, "MMMM d, yyyy");
        String returningDateString = DateUtils.formatDates(returningDate, "MMMM d, yyyy");

        By departingXPathCustomCreation = By.xpath(String.format(setDateXPath, departingDateString)); //creating the xpath for date, with partial string declared at the top: setDateXPath = "//td[@aria-label='%s']";
        By returningXPathCustomCreation = By.xpath(String.format(setDateXPath, returningDateString)); //creating the xpath for date, with partial string declared at the top: setDateXPath = "//td[@aria-label='%s']";
        clickWait(departingXPathCustomCreation);  //click on date
        clickWait(returningXPathCustomCreation);  //click on date

        String startDate = getAttributeString(readDepartingDateXPath, "value"); // get the value of the value Attribute
        String endDate = getAttributeString(readReturningDateXPath,"value"); // get the value of the value Attribute
        return Arrays.asList(startDate, endDate); //create an array list with start and end dates, for using it in the assert
    }

    public String passengersSelection() {
        // if I don't put this sleep, passengers "popup" is not shown
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        clickByActions(passengersDropDownXPath);  // dropdown passengers click
        clickWait(selectAdultsXPath);  // 2x Adults
        clickWait(selectDonePassengersXPath); // DONE button wait for the button to be clickable

        return getAttributeString(readPassengersXPath, "value");
    }



    public String findAFlight() {
        //search flights
        WaitUtils.implicitlyWaitFiveSeconds();
        driver.manage().deleteAllCookies(); // Deletes all the cookies
        clickWait(searchFlightsXPath);
        isVisible(flightsResults);
        return getText(flightsResults);
    }

    public String findAFlight2() {
        return getText(flightsResultsHavingProblem); //search flights

    }
}









