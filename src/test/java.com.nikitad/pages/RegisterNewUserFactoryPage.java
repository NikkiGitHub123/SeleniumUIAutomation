package pages;

import managers.SingletonDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterNewUserFactoryPage {

    WebDriver driver = SingletonDriverManager.getInstance().getDriver();

    @FindBy(name = "firstname")
    private WebElement fname_input;

    @FindBy(name = "lastname")
    private WebElement lname_input;

    @FindBy(name = "email")
    private WebElement email_input;

    @FindBy(name = "telephone")
    private WebElement telephone_input;

    @FindBy(name = "password")
    private WebElement password_input;

    @FindBy(name = "confirm")
    private WebElement password_confirm_input;

    @FindBy(name = "newsletter")
    private WebElement newsletter_input;

    @FindBy(xpath = "//input[@name='newsletter' and @value='0']")
    private WebElement subscribe_no;

    @FindBy(xpath = "//input[@name='newsletter' and @value='1']")
    private WebElement subscribe_yes;

    @FindBy(xpath = "//input[@name='agree' and @value='1']")
    private WebElement agree_tnc;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continue_button;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement warning;

    public RegisterNewUserFactoryPage setFirstName(String firstName){
        fname_input.sendKeys(firstName);
        return this;
    }

    public RegisterNewUserFactoryPage setLastName(String lastName){
        lname_input.sendKeys(lastName);
        return this;
    }

    public RegisterNewUserFactoryPage setEmail(String email){
        email_input.sendKeys(email);
        return this;
    }

    public RegisterNewUserFactoryPage setTelephone(String telephone){
        telephone_input.sendKeys(telephone);
        return this;
    }

    public RegisterNewUserFactoryPage setPassword(String password){
        password_input.sendKeys(password);
        return this;
    }

    public RegisterNewUserFactoryPage setPasswordConfirm(String confirm_password){
        password_confirm_input.sendKeys(confirm_password);
        return this;
    }

    public RegisterNewUserFactoryPage setSubscribeNewsletterYes(){
        subscribe_yes.click();
        return this;
    }

    public RegisterNewUserFactoryPage setSubscribeNewsletterNo(){
        subscribe_no.click();
        return this;
    }

    public RegisterNewUserFactoryPage agreeTnC() {
        agree_tnc.click();
        return this;
    }

    public RegisterUserSuccessPage clickContinue() {
        continue_button.click();
        return PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), RegisterUserSuccessPage.class);
    }

    public String readWarning() {
        return warning.getText();
    }
}
