package pages;

import managers.SingletonDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeFactoryPage {

    WebDriver driver;
    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement myAccountElement;

    @FindBy(linkText = "Register")
    private WebElement regLinkElement;

    @FindBy(linkText = "Login")
    private WebElement loginElement;

    public HomeFactoryPage clickOnMyAccount() {
        SingletonDriverManager.getInstance().waitUntilNextElementAppears(By.xpath( "//a[@title='My Account']"), 10);
        myAccountElement.click();
        return this;
    }

    public RegisterNewUserFactoryPage clickOnRegister() {
        clickOnMyAccount();
        SingletonDriverManager.getInstance().waitUntilNextElementAppears(By.linkText("Register"), 10);
        regLinkElement.click();
        return PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(),RegisterNewUserFactoryPage.class);
    }

    public LoginFactoryPage clickOnLogin() {
        clickOnMyAccount();
        SingletonDriverManager.getInstance().waitUntilNextElementAppears(By.linkText("Login"), 10);
        loginElement.click();
        return PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), LoginFactoryPage.class);
    }

}
