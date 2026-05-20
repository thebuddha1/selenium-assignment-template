import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

class RegisterPage extends PageBase {

    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By subscribeYesRadio =
        By.xpath("//input[@name='newsletter' and @value='1']");
    private By privacyCheckbox =
        By.name("agree");

    public RegisterPage(WebDriver driver) {

        super(driver);

        driver.get(
            "https://tutorialsninja.com/demo/index.php?route=account/register"
        );
    }

    public void fillRegistrationForm() {

        type(firstNameInput, "Test");
        type(lastNameInput, "User");

        type(
            emailInput,
            "test" + System.currentTimeMillis() + "@test.com"
        );

        type(telephoneInput, "123456");

        type(passwordInput, "Test123");
        type(confirmPasswordInput, "Test123");

        click(subscribeYesRadio);

        click(privacyCheckbox);
    }
}