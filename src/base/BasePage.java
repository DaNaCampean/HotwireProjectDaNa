package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

import static utils.DriverUtils.driver;

public class BasePage {

  public String getURLbase() {
    String getURL="";
    try {
      getURL = driver.getCurrentUrl();
    } catch (Exception e) {
      System.out.println("Cannot \"getURL\"");
    }
    return getURL;
  }

  public static WebElement isVisible(By byElement) {
    WebElement element=null;
    try {
      element  = WaitUtils.waitForElementToBeVisible(driver, byElement, 30);
    } catch (Exception e) {
      System.out.println("The element is not visible = " + byElement);
    }
    return element;
  }

  public void doubleClick(By element){
    Actions actions = new Actions(driver); // Instantiate the Actions class needed for double click

    try {
      WaitUtils.waitForElementToBeClickable(driver,element,15);
      actions.doubleClick(WaitUtils.waitForElementToBeClickable(driver,element,15)).perform(); // Perform double-click
    } catch (Exception e) {
      System.out.println("The element is not found or Cannot double click on it - " + element);
    }
  }
  public void clickByActions(By element){
    Actions actions = new Actions(driver); // Instantiate the Actions class needed for double click

    try {
      WaitUtils.waitForElementToBeClickable(driver,element,15);
      actions.click(WaitUtils.waitForElementToBeClickable(driver,element,15)).perform(); // Perform click
    } catch (Exception e) {
      System.out.println("The element is not found or Cannot double click on it - " + element);
    }
  }

  public static void clickWait(By element) {
    try {
      WaitUtils.waitForElementToBeClickable(driver,element,15).click();
    } catch (Exception e) {
      System.out.println("The element is not found or not clickable = " + element);
    }
  }

  public String getText(By element) {
     return isVisible(element).getText();
  }

  public String getAttributeString(By element, String attribute){
    return isVisible(element).getDomAttribute(attribute);

  }

  public static void fillFields(By element,String text) {
    try {
      WebElement textField =isVisible(element);
      clickWait(element);
      textField.sendKeys(text);

    } catch (Exception e) {
      System.out.println("The element is not visible = " + element);
    }


  }

}




