package tests;

import base.BasePageDriverInitialization;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.FlightsPage;
import pages.HomePage;

public class BaseTest {
    public HomePage home;
    public FlightsPage flights;

    @BeforeSuite
    public void initDriver(){

       BasePageDriverInitialization.setupMac();
       // BasePageDriverInitialization.driverSetupWindows();

    }

    @BeforeTest
    public void createObjects(){
       home = new HomePage();
       flights = new FlightsPage();

    }

    @AfterSuite
    public void driverQuit(){

     // BasePageDriverInitialization.driverQuit();

    }
}
