package test;

//import time.sleep;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import pages.ImpactDemoLogin;

import pages.ImpactDashboardPage;

public class TestImpactDemo {

    static final boolean useFirefox = true;

    static final boolean doTest = true;
    static WebDriver driver;

    ImpactDemoLogin objLogin;

    ImpactDashboardPage dashboardPage;

    static String driverFFPath = "C:\\ap\\utils\\geckodriver.exe";
    static String driverCHPath = "C:\\ap\\utils\\chromedriver.exe";

    @BeforeTest

    public void setup() {

        System.out.println("In BerforeTest");
        if(useFirefox) {
            System.setProperty("webdriver.gecko.driver", driverFFPath);

            driver = new FirefoxDriver();
        }
        else {
            System.setProperty("webdriver.chrome.driver", driverCHPath);

            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demo.impact.com/login.user");

    }

    @AfterClass(alwaysRun = true)
    protected void terminate() {
        System.out.println("In AfterClass");
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
            } catch (UnreachableBrowserException ex) {
                System.out.print(ex.getMessage());
//            } catch (NoSuchSessionException noSuchSessionException) {
//                TestReporter.log("Tried to quit browser with NULL session: " + noSuchSessionException.getMessage());
            }
        }

 //       if (application != null) {
 //           application = null;
 //       }
    }

    public static WebDriver getdriver(){
        if (driver == null){
            if(useFirefox) {
                System.setProperty("webdriver.gecko.driver", driverFFPath);

                driver = new FirefoxDriver();
            }
            else {
                System.setProperty("webdriver.chrome.driver", driverCHPath);

                driver = new ChromeDriver();
            }
        }
        return driver;
    }

    /**
     * This test case will login in "https://demo.impact.com/login.user"
     * <p>
     * Verify login page title as "impact - security"
     * <p>
     * Wait for browser check
     * <p>
     * Verify login page title is "impact | welcome!"
     * <p>
     * Login to application
     * <p>
     * Verify the home page using Dashboard message
     */

    @Test(priority = 0)

    public void test_Home_Page_Is_Correct()  throws InterruptedException {

        System.out.println("Running test_Home_Page_Is_Correct");
        //Create Login Page object

        objLogin = new ImpactDemoLogin(driver);

// throws java.lang.IllegalMonitorStateException        driver.manage().timeouts().wait(60000);
        Thread.sleep(12000);
/*
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(200, TimeUnit.SECONDS)
                .pollingEvery(60, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        Thread.sleep(30000);
        WebElement username = wait.until (new java.util.function.Function<WebDriver, WebElement>(){

            public WebElement apply(WebDriver driver ) {
                return driver.findElement(By.id("j_username"));
            }
        });
        Assert.assertTrue(username.isDisplayed());
*/

        //Verify security page title

        String securityPageTitle = objLogin.getLoginTitle();

        Assert.assertTrue(securityPageTitle.toLowerCase().contains("impact - security"));

        if(doTest) {
            // we have to wait for the welcome page after browser test

            //time.sleep(10);

//        String loginPageTitle = objLogin.getLoginTitle();
//        while( loginPageTitle.equalsIgnoreCase(securityPageTitle) ) {
//            loginPageTitle = objLogin.getLoginTitle();
//        }
//
//        WebDriverWait wait = new WebDriverWait(driver,30);
//
//        wait.until(ExpectedConditions.titleIs("Impact | Welcome!") );
//causes exception        driver.manage().timeouts().wait(60000);
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(10, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
//        Thread.sleep(30000);
            WebElement username = wait.until(new java.util.function.Function<WebDriver, WebElement>() {

                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.id("j_username"));
                }
            });
            Assert.assertTrue(username.isDisplayed());
            String loginPageTitle = objLogin.getLoginTitle();

            //Verify login page title

            Assert.assertTrue(loginPageTitle.toLowerCase().contains("impact | welcome!"));
            // login to application

            dashboardPage = objLogin.loginToImpactDemo("qademouser", "5umbrella@9");

            //Verify home page
            // done in page constructor

        }
     }

    /**
     * This test case will follow the test_Home_Page_Is_Correct() test and
     * assumes that the Dashboard page is active.
     * <p>
     * Verify login page title as "Impact - Advertiser Account"
     * <p>
     * Check that the Edit icon is displayed id = "dashSettingsHolder"
     * <p>
     * Verify login page title is "impact | welcome!"
     * <p>
     * Login to application
     * <p>
     * Verify the home page using Dashboard message
     */

    @Test(priority = 1)

    public void test_Dashboard()  {

        System.out.println("Running test_Dashboard");
        Assert.assertNotNull(dashboardPage);
        dashboardPage.clickEdit();

// page does not change - just a popup
// String editTitle = objDashboardPage.getDashboardTitle();
//        Assert.assertTrue(editTitle.toLowerCase().contains("impact - security"));

        // make sure the widget is there
        Assert.assertTrue(dashboardPage.findWidget());

        // check that the button is a plus (+)
        Assert.assertTrue(dashboardPage.isButtonPlus());

        // click the widget add button
        dashboardPage.clickWidgetButton();

        // click the Apply button
        dashboardPage.clickApplyButton();

        // check that the widget popup has gone away
        Assert.assertFalse(dashboardPage.findWidget());

        // check that the widget appears on the dashboard


    }

}

