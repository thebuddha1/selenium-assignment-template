import org.openqa.selenium.*;

class HomePage extends PageBase {

    private final String PAGE_URL = "https://tutorialsninja.com/demo/index.php";
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By loginButton = By.linkText("Login");
    private By searchInput = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");
    private By logoutButton = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        super(driver);

        driver.get(PAGE_URL);
    }

    public LoginPage openLoginPage() {
        click(myAccountDropdown);
        click(loginButton);

        return new LoginPage(driver);
    }

    public SearchResultPage search(String productName) {
        type(searchInput, productName);
        click(searchButton);

        return new SearchResultPage(driver);
    }

    public void logout() {
        click(logoutButton);
    }
}