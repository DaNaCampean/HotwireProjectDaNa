package tests;

import base.BaseURL;
import pages.FlightsPage;
import pages.HomePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.DriverUtils;

import static utils.DriverUtils.driverQuit;

public class BaseTest {
    public HomePage home;
    public FlightsPage flights;

    @BeforeSuite
    public void initDriver(){
        DriverUtils.setupMacWebDriverPath();
        //DriverUtils.setupWindowsWebDriverPath();
        DriverUtils.chromeDriverOpen();
        BaseURL.goToURL();
    }

    @BeforeTest
    public void createObjects(){
        home = new HomePage();
        flights = new FlightsPage();

    }

    @AfterSuite
    public void driverExit(){
        driverQuit();
    }
}
