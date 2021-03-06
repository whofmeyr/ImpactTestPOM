package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class ImpactDemoLogin {

    WebDriver driver;

    By userImpactDemoName = By.name("j_username");

    By passwordImpactDemo = By.name("j_password");

    By login = By.id("loginButton");

    public ImpactDemoLogin(WebDriver driver){
        this.driver = driver;
    }

    //Set user name in textbox
    public void setUserName(String strUserName){
        driver.findElement(userImpactDemoName).sendKeys(strUserName);
    }

    //Set password in password textbox
    public void setPassword(String strPassword){
        driver.findElement(passwordImpactDemo).sendKeys(strPassword);
    }

    //Click on login button
    public void clickLogin(){
        driver.findElement(login).click();
    }

    //Get the title of Login Page
    public String getLoginTitle(){
        return    driver.getTitle();
    }

    /**
     * This POM method will be exposed in test case to login in the application
     * @param strUserName
     * @param strPasword
     * @return
     */

    public ImpactDashboardPage loginToImpactDemo(String strUserName, String strPasword){
        //Fill user name
        this.setUserName(strUserName);

        //Fill password
        this.setPassword(strPasword);

        //Click Login button
        this.clickLogin();

        return new ImpactDashboardPage(driver);
    }

}
