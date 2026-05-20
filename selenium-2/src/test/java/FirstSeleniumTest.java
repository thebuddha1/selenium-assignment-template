import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;

public class FirstSeleniumTest {

    private WebDriver driver;

    @Before
    public void setup()
        throws MalformedURLException {

        ConfigReader config = new ConfigReader();

        ChromeOptions options = new ChromeOptions();

        if (config.getProperty("headless").equals("true")) {
            options.addArguments("--headless");
        }

        options.addArguments("--window-size=1920,1080");

        driver = new RemoteWebDriver(
            new URL("http://selenium:4444/wd/hub"),
            options
        );

        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess() {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.openLoginPage();

        loginPage.loginSuccessfully(
            "t73n0wtest@test.com",
            "Test123"
        );

        String bodyText = homePage.getBodyText();

        Assert.assertTrue(bodyText.contains("Logout"));
    }

    @Test
    public void testLoginFail() {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.openLoginPage();

        String errorText = loginPage.loginFail(
                    "wrong@test.com",
                    "wrongpassword"
                );

        Assert.assertTrue(errorText.contains("Warning: No match for E-Mail Address and/or Password."));
    }

    @Test
    public void testLogout() {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.openLoginPage();

        loginPage.loginSuccessfully(
            "t73n0wtest@test.com",
            "Test123"
        );

        homePage.logout();

        Assert.assertTrue(homePage.getBodyText().contains("Login"));
    }

    @Test
    public void testRegistrationFormElements() {

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.fillRegistrationForm();

        Assert.assertTrue(registerPage.getBodyText().contains("Privacy Policy"));
    }

    @Test
    public void testProductSearch() {

        HomePage homePage = new HomePage(driver);

        SearchResultPage searchResultPage = homePage.search("MacBook");

        String resultText = searchResultPage.getFirstProductText();

        Assert.assertTrue(resultText.contains("MacBook"));
    }

    @Test
    public void testContactForm() {

        ContactPage contactPage = new ContactPage(driver);

        contactPage.fillContactForm(
            "Test",
            "test@test.com",
            "This is a selenium textarea test."
        );

        Assert.assertTrue(driver.getCurrentUrl().contains("success"));
    }

    @Test
    public void testPageTitle() {
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals(
            "Your Store",
            driver.getTitle()
        );
    }

    @Test
    public void testMultiplePages() {
        
        String[] urls = {
            "https://tutorialsninja.com/demo/index.php?route=product/category&path=20",
            "https://tutorialsninja.com/demo/index.php?route=product/category&path=18",
            "https://tutorialsninja.com/demo/index.php?route=product/category&path=25"
        };

        for (String url : urls) {
            driver.get(url);

            Assert.assertTrue(
                driver.getTitle().contains("Your Store")
            );
        }
    }

    @Test
    public void testBrowserNavigation() {

        HomePage homePage = new HomePage(driver);

        homePage.search("MacBook");

        driver.navigate().back();

        Assert.assertTrue(driver.getCurrentUrl().contains("tutorialsninja"));

        driver.navigate().forward();

        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    @Test
    public void testAddProductToCart() {

        HomePage homePage = new HomePage(driver);

        SearchResultPage searchResultPage = homePage.search("MacBook");

        CartPage cartPage = searchResultPage.openFirstProduct();

        cartPage.addProductToCart();

        cartPage.openCart();

        String cartText = cartPage.getCartProductText();

        Assert.assertTrue(cartText.contains("MacBook"));
    }

    @After
    public void close() {

        if (driver != null) {
            driver.quit();
        }
    }
}