import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class ChromeTest {

    public static void main(String[] args) {
        int x;
        // It will download compatible chrome executable for you
        WebDriverManager.chromedriver().setup();
        System.out.println("Browser executable path is set as :- "+System.getProperty("webdriver.chrome.driver"));
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        System.out.println("Title is "+driver.getTitle());
        driver.quit();
////Setting system properties of ChromeDriver
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\BayoumiH2\\OneDrive - Vodafone Group\\Desktop\\Training\\chromedriverold.exe");
//
////Creating an object of ChromeDriver
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
////Deleting all the cookies
//        driver.manage().deleteAllCookies();
//
////Specifiying pageLoadTimeout and Implicit wait
//        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
////launching the specified URL
//        driver.get("https://www.google.com/");
//
////Locating the elements using name locator for the text box
//        driver.findElement(By.name("q")).sendKeys("YouTube");
//
////name locator for google search button
//        WebElement searchIcon = driver.findElement(By.name("btnK"));
//        searchIcon.click();
    }
}

