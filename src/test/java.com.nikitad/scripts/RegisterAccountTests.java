package scripts;

import managers.PropertyFileManager;
import managers.SingletonDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.HomeFactoryPage;
import pages.RegisterNewUserFactoryPage;
import pages.RegisterUserSuccessPage;

import java.util.UUID;

public class RegisterAccountTests {

    PropertyFileManager prop = new PropertyFileManager();

    private String homepageurl = prop.getProperty("config", "homepageurl");
    private String registerPageUrl = prop.getProperty("config", "registerPageUrl");
    HomeFactoryPage homePage;
    RegisterNewUserFactoryPage registerNewUserPage;
    RegisterUserSuccessPage registerSuccessPage;

    @BeforeMethod
    public void setUp() {
        SingletonDriverManager.getInstance().openFirefoxBrowser();
        SingletonDriverManager.getInstance().navigateToUrl(homepageurl);
    }

    @Test
    public void registerCustomer_isSuccessful() {
        String testEmail = "Automation_" + UUID.randomUUID() + "@gmail.com";
        System.out.println("testEmail: " + testEmail);
        String password = "Password123";
        System.out.println("Password: " + password);
        homePage = PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), HomeFactoryPage.class);
        registerNewUserPage = homePage.clickOnRegister();

        String landingPageUrl = SingletonDriverManager.getInstance().getDriver().getCurrentUrl();
        Assert.assertEquals(landingPageUrl, registerPageUrl);

        registerNewUserPage.setFirstName("Automation");
        registerNewUserPage.setLastName("User");
        registerNewUserPage.setEmail(testEmail);
        registerNewUserPage.setTelephone("123-123-1234");
        registerNewUserPage.setPassword(password);
        registerNewUserPage.setPasswordConfirm(password);
        registerNewUserPage.setSubscribeNewsletterNo();
        registerNewUserPage.agreeTnC();

        registerSuccessPage = registerNewUserPage.clickContinue();
        String successPageTitle = registerSuccessPage.getPageTitle();
        Assert.assertEquals(successPageTitle, "Your Account Has Been Created!");
    }

    @Test
    public void registerCustomer_missingTnC_fails() {
        String testEmail = "Automation_" + UUID.randomUUID() + "@gmail.com";
        System.out.println("testEmail: " + testEmail);
        String password = "Password123";
        System.out.println("Password: " + password);
        homePage = PageFactory.initElements(SingletonDriverManager.getInstance().getDriver(), HomeFactoryPage.class);
        registerNewUserPage = homePage.clickOnRegister();

        String landingPageUrl = SingletonDriverManager.getInstance().getDriver().getCurrentUrl();
        Assert.assertEquals(landingPageUrl, registerPageUrl);

        registerNewUserPage.setFirstName("Automation");
        registerNewUserPage.setLastName("User");
        registerNewUserPage.setEmail(testEmail);
        registerNewUserPage.setTelephone("123-123-1234");
        registerNewUserPage.setPassword(password);
        registerNewUserPage.setPasswordConfirm(password);
        registerNewUserPage.setSubscribeNewsletterNo();

        registerNewUserPage.clickContinue();
        Assert.assertEquals("Warning: You must agree to the Privacy Policy!", registerNewUserPage.readWarning());
    }

    @AfterMethod
    public void tearDown() {
        SingletonDriverManager.getInstance().closeBrowser();
    }
}
