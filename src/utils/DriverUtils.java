package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtils {

    public static WebDriver driver;

    public static void setupMacWebDriverPath(){
        // System.setProperty("webdriver.chrome.driver", "/Users/dana/Desktop/JAVA_automation/3apra25/chromedriver-mac-x64/chromedriver");
        System.setProperty("webdriver.chrome.driver", "/Users/dana/IdeaProjects/AutomationFiles/chromedriver-mac-arm64/chromedriver");
    }

    public static void setupWindowsWebDriverPath(){
        System.setProperty("webdriver.chrome.driver", "D:\\Backup Softvision\\AUTOMATION STUFF\\selenium\\chromedriver-win64\\chromedriver.exe");
    }

    public static void chromeDriverOpen() {
        if (driver == null) {  // this avoid browser opening for each objects creation from base page
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
    }

    public static void driverQuit(){
        driver.quit();
    }
}

