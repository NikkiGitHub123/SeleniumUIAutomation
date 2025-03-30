import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/*
This class is a cheat sheet on how to interact with certain web elements on a web page
The websites used for practice are:
https://www.lambdatest.com/selenium-playground
https://jqueryui.com/draggable/
https://letcode.in/frame
 */
public class SeleniumHowTo {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void howto_simple_form() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        WebElement firstValue_input = driver.findElement(By.id("sum1"));
        firstValue_input.sendKeys("5");
        System.out.println("Inside the box it says: " + firstValue_input.getAttribute("placeholder"));

        WebElement secondValue_input = driver.findElement(By.id("sum2"));
        secondValue_input.sendKeys("10");

        WebElement sum_button = driver.findElement(By.xpath("//button[contains(text(), 'Get Sum')]"));
        sum_button.click();

        WebElement result_label = driver.findElement(By.xpath("//p[@id='addmessage']"));
        String actualResult = result_label.getText();

        Assert.assertEquals(15, Integer.parseInt(actualResult));
    }

    @Test
    public void howto_select_dropdown() {
        driver.get("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");

        WebElement select_option = driver.findElement(By.id("select-demo"));
        Select selectElement = new Select(select_option);
        selectElement.selectByValue("Sunday");

        WebElement selection = selectElement.getFirstSelectedOption();
        System.out.println( "Selection: " + selection.getText());

        WebElement multi_select_option = driver.findElement(By.id("multi-select"));
        Select multiSelectElement = new Select(multi_select_option);
        multiSelectElement.selectByValue("California");
        multiSelectElement.selectByValue("Florida");
        multiSelectElement.selectByValue("Washington");

        List<WebElement> multiSelectionList = multiSelectElement.getAllSelectedOptions();
        for (WebElement sel: multiSelectionList
        ) {
            System.out.println("Selection: "+ sel.getText());
        }
    }

    @Test
    public void howto_javascript_alert() {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        driver.findElement(By.xpath("//p[text()='JavaScript Alerts']/button")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("First alert says: " + alert.getText());
        alert.accept();

        driver.findElement(By.xpath("(//button[text()='Click Me'])[2]")).click();
        Alert alert1 = driver.switchTo().alert();

        System.out.println("Second alert says: " + alert1.getText());
        alert1.dismiss();
        System.out.println(driver.findElement(By.xpath("//p[@id='confirm-demo']")).getText());

        driver.findElement(By.xpath("(//button[text()='Click Me'])[3]")).click();
        Alert alert2 = driver.switchTo().alert();

        System.out.println("Third alert says: " + alert1.getText());
        alert2.sendKeys("Rambo");
        alert2.accept();

        System.out.println(driver.findElement(By.xpath("//p[@id='prompt-demo']")).getText());
    }

    @Test
    public void howto_window_popup_modal() {
        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");

        try {
            String parentWindowHandle = driver.getWindowHandle();

            driver.findElement(By.linkText("Like us On Facebook")).click();
            Set<String> windowHandles = driver.getWindowHandles();
            List<String> windowHandleNames = new ArrayList<>(windowHandles);

            driver.switchTo().window(windowHandleNames.get(1));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//title")));
            //    System.out.println(driver.findElement(By.xpath("//input[@name='email']")).getText()); // get placeholder attribute


            Thread.sleep(5000);
            System.out.println("Title: " + driver.getTitle());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void howto_frames() {
        driver.get("https://letcode.in/frame");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstFr")));

            driver.switchTo().frame("firstFr"); // First level of frame

            driver.findElement(By.name("fname")).sendKeys("First Name");
            driver.findElement(By.name("lname")).sendKeys("Last Name");

            WebElement innerIframe = driver.findElement(By.xpath("//iframe[@src='innerframe']")); //Nested Frame
            driver.switchTo().frame(innerIframe);
            driver.findElement(By.name("email")).sendKeys("test@beyond.com");

            driver.switchTo().parentFrame(); // Switches to 1 frame before current (in case of nested frames)
            driver.findElement(By.name("fname")).sendKeys(" Addition");

            driver.switchTo().defaultContent(); //Switch to default homepage iframe
            System.out.println(driver.findElement(By.tagName("title")).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void howto_windows_authenticationpopup() {
        String username = "admin";
        String password = "admin";
        driver.get("https://" + username + ":" + password + "@the-internet-herokuapp.com/basic_auth");
        // Selenium 4 allows for a way to work through devtools to enter admin credentials
        // AutoIT or Sikuli are external applications that help with Windows authentication
    }

    @Test
    public void howto_takeScreenshot() {
        // Screenshot only includes visible portions
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        try {
            File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler fileHandler = new FileHandler();
            FileHandler.copy(screenshotAs, new File("./img1.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void howto_actions() {
        //Actions class is used for mouse actions like Drag, Drop, Hover over, etc
        driver.get("https://www.lambdatest.com/");

       /*
        //move To Element
       WebElement dev = driver.findElement(By.xpath("//button[text()='Developers ']"));
       Actions actions = new Actions(driver);
       actions.moveToElement(dev).perform();

       driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/support/api-doc/']")).click();
       String url = driver.getCurrentUrl();
       Assert.assertEquals(url, "https://www.lambdatest.com/support/api-doc/");
        */

       /*
        // Drag and Drop
        driver.get("https://letcode.in/test");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).perform();
        */

        /*
        // Drag and Drop by
        driver.get("https://jqueryui.com/draggable/");
        driver.switchTo().frame(0);
        Actions builder = new Actions(driver);

        WebElement source = driver.findElement(By.id("draggable"));
        builder.dragAndDropBy(source, 50, 20).perform();
        */

        //Drag and Drop By wrt another element
        driver.get("https://jqueryui.com/draggable/");
        driver.switchTo().frame(0);
        Actions builder = new Actions(driver);

        WebElement source = driver.findElement(By.id("draggable"));

        Point location = source.getLocation();
        int x = location.getX();
        int y = location.getY();

        builder.dragAndDropBy(source, x+50, y+20).perform();

        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}