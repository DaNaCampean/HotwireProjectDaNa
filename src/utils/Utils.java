package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static base.BasePageDriverInitialization.driver;

public class Utils {
//in utils, we can use wait for elements to be visible , rather than  implicit wait with seconds
    // take screenshots

    public static void implicitlyWaitFiveSeconds(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // wait until the element became visible
    public static void waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }



    public static LocalDate returnCurrentDate(){
        // Set the current date
       return LocalDate.now();

    }

    public static String formatDates(LocalDate date, String formatPattern){
        // format dates to be able to create the list for comparison
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return date.format(formatter);






    }


}

