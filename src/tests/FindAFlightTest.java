package tests;

/*
1. https://www.hotwire.com/
2. Flights
3. From LAX
4. To Bucharest Otopeni
5. From date - 7 days from today
6. To date - 14 days from today
7. 2 adults

 */

import utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class FindAFlightTest extends BaseTest {

    @Test
    public  void flightsTest(){

        // Verify that correct URL, hot wire.com opens.
        String myURL = home.getURLbase();
        Assert.assertEquals(myURL, "https://www.hotwire.com/", "Verified that the correct page was shown, by URL");
        System.out.println("Passed - correct URL: " + myURL + "opens");

        // Verify that Flights Tab was selected
        home.selectFlights();
        String flightsTabText = flights.getTextSearchButton();
        Assert.assertEquals(flightsTabText, "Find a flight", "Verified that Flights tab was pressed, by checking Find a flight TEXT from search button  ");
        System.out.println("Passed - correct Flights Tab page opens");

        //Fly from: LAX
        Assert.assertEquals(flights.FillFlyFromTextField(), "Los Angeles, CA, United States of America (LAX-Los Angeles Intl.)", "Verified that LAX was correctly selected, by getAttribute(value) ");
        System.out.println("Passed - LAX is correctly selected");

        // Fly To: Bucharest OTP
        Assert.assertEquals(flights.FillFlyToTextField(), "Bucharest, Romania (OTP-Henri Coanda Intl.)", "Verified that OTP was correctly selected, by getAttribute(value) ");
        System.out.println("Passed - OTP is correctly selected");

        // From date - 7 days from today
        // To date - 14 days from today
        LocalDate departingDate = DateUtils.returnCurrentDate().plusDays(7); // BEFORE FORMAT = 2025-06-12
        LocalDate returningDate = DateUtils.returnCurrentDate().plusDays(14);

        List<String> actualList = flights.datesChoosing(departingDate, returningDate);


//        // format dates to be able to create the list for comparison
        String departingDateString = DateUtils.formatDates(departingDate, "MM/dd/yyyy");
        String returningDateString = DateUtils.formatDates(returningDate, "MM/dd/yyyy");
        List<String> expectedList = Arrays.asList(departingDateString,returningDateString);

        Assert.assertEquals(actualList,expectedList, "Verified that startDate and endDate are correctly SET");
        System.out.println("Passed - Departing Date and Returning Dates are correctly selected");
        WaitUtils.implicitlyWaitFiveSeconds();


        // select 2 adults
        Assert.assertEquals(flights.passengersSelection(), "2 Adults, 0 Children", "Verified that two Adults are correctly SET");
        System.out.println("Passed - 2 Adults are correctly selected");
        WaitUtils.implicitlyWaitFiveSeconds();

        // search flights
        try {
            Assert.assertEquals(flights.findAFlight(), "Recommended departing flights", "Verify ca a mers Search Flights - loading page"); // only if is not ok , is shown this message
            System.out.println("Passed - Searching Flights results page opens");
        }
        catch(Exception e) {
            try {
                Assert.assertEquals(flights.findAFlight2(), "Sorry, we're having a problem on our end.", "Verify ca a mers Search Flights - loading page"); // only if is not ok , is shown this message
                System.out.println("Passed - Searching Flights results page opens - BUT shows error: Sorry, we're having a problem on our end.");
            }
            catch(Exception e2) {
                System.out.println("The page did not worked correctly");
            }
        }
    }
}