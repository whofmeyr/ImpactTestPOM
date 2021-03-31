package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

public class ImpactDashboardPage {

    WebDriver driver;

    By editId = By.id("dashSettings");

    By PR_widget = By.id("uitk_ext_1405");
    By ApplyButtonID = By.id("button-1031-btnInnerEl");
    By PR_widgetOnDashboard = By.id("todo_radius_header_hd-textEl");

    WebElement theWidget;
    Class<? extends WebElement> widgetClass;
    String widgetClassString;
    String dashboardTitle = "impact - advertiser account";
    String plusClassString = "fa fa-lg fa-plus";
    String minusClassString = "fa fa-lg fa-minus";

    public ImpactDashboardPage(WebDriver driver){
        this.driver = driver;
        Assert.assertTrue(this.getDashboardTitle().toLowerCase().contains(dashboardTitle));
    }

    //Click on edit button
    public void clickEdit(){
        driver.findElement(editId).click();
    }

    //Get the title of Login Page
    public String getDashboardTitle(){
        return    driver.getTitle();
    }

    // find the widget we want
    public boolean findWidget() {
        theWidget = driver.findElement(PR_widget);
       return theWidget != null;
    }

    // is the button a plus (+)
    public boolean isButtonPlus() {
        widgetClass = theWidget.getClass();
        widgetClassString = widgetClass.getName();
        return     widgetClassString == plusClassString;
    }

    // is the button a mins(-)
    public boolean isButtonMinus() {
        widgetClass = theWidget.getClass();
        widgetClassString = widgetClass.getName();
        return     widgetClassString == minusClassString;
    }

    // click the button;
    public void clickWidgetButton() {
        theWidget.click();
    }

    // click the Apply button
    public void clickApplyButton() {
        driver.findElement(ApplyButtonID).click();
    }
}
