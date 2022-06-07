import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumGridTC2 {
    public static String BaseUrl = "https://search.yahoo.com/?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAAHsyMu45qg55gKH-2iSFJ9qNRihW9UpQie6b1AMD9iecMqnCy7nJc7W4yjRPpKbW_8kExKiIcWEX8eTfPdGdoncECTZ-i1UwtDdOh2Y3fwvksn8zNXibu6qsm2oHQKe9r4qyGnQoL-K4gDn-Y1ie8tqdAJVpkMfejOuI7VB156nG";
    public static String node = "http://192.168.1.176:4444/wd/hub";
    protected ThreadLocal<RemoteWebDriver> driver = null;
    protected static Environment ENV = Environment.Selenium_Grid;
    @BeforeTest
    @Parameters(value = {"RunningEnvironment", "browser"})
    public void setUp(@Optional("Selenium_Grid") String RunningEnvironment, String browser) throws MalformedURLException {
        driver = new ThreadLocal<>();
        if (RunningEnvironment.equals("Local")) ENV = Environment.Local;
        else ENV = Environment.Selenium_Grid;
        System.out.println("The running env is " + ENV);
        if (ENV == Environment.Local) {
            if (browser.equals("chrome")) {
                //Chrome
                WebDriverManager.chromedriver().setup();
                System.out.println("Browser executable path is set as :- " + System.getProperty("webdriver.chrome.driver"));
                driver.set(new ChromeDriver());
            } else if (browser.equals("firefox")) {
                //Firefox
                System.setProperty("webdriver.firefox.bin", "C:\\Users\\BayoumiH2\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
                WebDriverManager.firefoxdriver().setup();
                System.out.println("Browser executable path is set as :- " + System.getProperty("webdriver.gecko.driver"));
                driver.set(new FirefoxDriver());
                driver.get().manage().deleteAllCookies();
                driver.get().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
                driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            } else {
                //Edge
                WebDriverManager.edgedriver().setup();
                System.out.println("Browser executable path is set as :- " + System.getProperty("webdriver.ie.driver"));
                driver.set(new EdgeDriver());

            }
        } else if (ENV == Environment.Selenium_Grid) {
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            if (browser.equals("chrome")) {
                caps.setBrowserName("chrome");
            } else if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\BayoumiH2\\OneDrive - Vodafone Group\\Desktop\\Training\\SeleniumGrid\\geckodriver.exe");
                caps = DesiredCapabilities.firefox();
                caps.setCapability("marionette", true);
                FirefoxBinary bin = new FirefoxBinary(new File(
                        "C:\\Users\\BayoumiH2\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
                caps.setCapability(FirefoxDriver.BINARY, bin);
            }
            else{
                caps=DesiredCapabilities.internetExplorer();
                caps.setBrowserName("iexplore");
                caps.setPlatform(Platform.WINDOWS);
            }
            driver.set(new RemoteWebDriver(new URL(node), caps));
        }
        System.out.println("*** Starting test ***");
        driver.get().manage().window().maximize();
        System.out.println("*** Navigation to Application ***");
        driver.get().get(BaseUrl);
    }

    @Test
    public void OpenYahooSearch() {

        System.out.println("*** Verifying Navigated URL ***");
        Assert.assertTrue(driver.get().getCurrentUrl().contains("yahoo"));
    }

    @AfterClass
    public void tearDown() {
        driver.get().quit();
        driver.remove();
    }
}
