package pages;

import managers.SingletonDriverManager;
import org.openqa.selenium.WebDriver;

public class RegisterUserSuccessPage {

    WebDriver driver = SingletonDriverManager.getInstance().getDriver();

    public String getPageTitle() {
        return this.driver.getTitle();
        //Your Account Has Been Created!
    }

    public String getUrl () {
        return driver.getCurrentUrl();
    }
}

