package pages;

import managers.SingletonDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFactoryPage {

    WebDriver driver = SingletonDriverManager.getInstance().getDriver();

    @FindBy(name = "email")
    private WebElement email_input;

    @FindBy(name = "password")
    private WebElement password_input;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement login_button;

    public LoginFactoryPage enterEmail(String email) {
        email_input.sendKeys(email);
        return this;
    }

    public LoginFactoryPage enterPassword(String password) {
        password_input.sendKeys(password);
        return this;
    }

    public MyAccountFactoryPage clickLogin() {
        login_button.click();
        return PageFactory.initElements(this.driver, MyAccountFactoryPage.class);
    }
}
