import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

class ContactPage extends PageBase {

    private By nameInput = By.id("input-name");
    private By emailInput = By.id("input-email");
    private By enquiryTextarea = By.id("input-enquiry");
    private By submitButton = By.cssSelector("input[value='Submit']");

    public ContactPage(WebDriver driver) {

        super(driver);

        driver.get(
            "https://tutorialsninja.com/demo/index.php?route=information/contact"
        );
    }

    public void fillContactForm(
        String name,
        String email,
        String message
    ) {

        type(nameInput, name);
        type(emailInput, email);
        type(enquiryTextarea, message);

        click(submitButton);
    }
}