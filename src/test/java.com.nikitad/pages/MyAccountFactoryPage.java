package pages;

import managers.SingletonDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountFactoryPage {

    WebDriver driver = SingletonDriverManager.getInstance().getDriver();

    @FindBy(xpath="//h2[1]")
    private WebElement myAccount_header;

    public String getHeader() {
        return myAccount_header.getText();
    }
}
