package scripts;

import managers.PropertyFileManager;
import managers.SingletonDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.UUID;

public class LoginTests {

    PropertyFileManager prop = new PropertyFileManager();

    private String homepageurl = prop.getProperty("config", "homepageurl");
    private String registerPageUrl = prop.getProperty("config", "registerPageUrl");
    HomeFactoryPage homePage;
    LoginFactoryPage loginFactoryPage;
    MyAccountFactoryPage myAccountFactoryPage;
    RegisterNewUserFactoryPage registerNewUserPage;
    RegisterUserSuccessPage registerSuccessPage;
    String testEmail, testPassword;

    @BeforeMethod
    public void setUp() {
        SingletonDriverManager.getInstance().openFirefoxBrowser();
        SingletonDriverManager.getInstance().navigateToUrl(homepageurl);
    }

    @Test(dependsOnMethods = "registerCustomer_isSuccessful")
    public void login_isSuccessful() {
        homePage = PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), HomeFactoryPage.class);
        loginFactoryPage = homePage.clickOnLogin();
        loginFactoryPage.enterEmail(testEmail);
        loginFactoryPage.enterPassword(testPassword);
        myAccountFactoryPage = loginFactoryPage.clickLogin();
        Assert.assertEquals(myAccountFactoryPage.getHeader(), "My Account");
    }

    @Test
    public void registerCustomer_isSuccessful() {
        testEmail = "Automation_" + UUID.randomUUID() + "@gmail.com";
        System.out.println("testEmail: " + testEmail);
        testPassword = "Password123";
        System.out.println("Password: " + testPassword);
        homePage = PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), HomeFactoryPage.class);
        registerNewUserPage = homePage.clickOnRegister();

        String landingPageUrl = SingletonDriverManager.getInstance().getDriver().getCurrentUrl();
        Assert.assertEquals(landingPageUrl, registerPageUrl);

        registerNewUserPage.setFirstName("Automation");
        registerNewUserPage.setLastName("User");
        registerNewUserPage.setEmail(testEmail);
        registerNewUserPage.setTelephone("123-123-1234");
        registerNewUserPage.setPassword(testPassword);
        registerNewUserPage.setPasswordConfirm(testPassword);
        registerNewUserPage.setSubscribeNewsletterNo();
        registerNewUserPage.agreeTnC();

        registerSuccessPage = registerNewUserPage.clickContinue();
        String successPageTitle = registerSuccessPage.getPageTitle();
        Assert.assertEquals(successPageTitle, "Your Account Has Been Created!");
    }

    @AfterMethod
    public void tearDown() {
        SingletonDriverManager.getInstance().closeBrowser();
    }
}
