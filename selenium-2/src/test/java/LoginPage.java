import org.openqa.selenium.*;

class LoginPage extends PageBase {

    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.cssSelector("input[value='Login']");
    private By warningMessage = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginSuccessfully(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);

        click(loginButton);
    }

    public String loginFail(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);

        click(loginButton);

        return waitAndReturnElement(
            warningMessage
        ).getText();
    }
}