package managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SingletonDriverManager {

    private static SingletonDriverManager instance;
    private WebDriver driver;

    private SingletonDriverManager() {
        // Private constructor to prevent external instantiation
    }
    public static SingletonDriverManager getInstance() {
        if (instance == null) {
            instance = new SingletonDriverManager();
        }
        return instance;
    }
    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }
    public void closeBrowser() {
        driver.quit();
    }


    // Explicit Wait for an element to be present
    public WebElement waitUntilNextElementAppears(By locator, int timeOut) {
        WebElement element = new WebDriverWait(SingletonDriverManager.getInstance().getDriver(), Duration.ofSeconds(timeOut)).until
                (ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }
    public void openFirefoxBrowser() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
}
