import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        return driver.findElement(locator);
    }

    protected void click(By locator) {
        waitAndReturnElement(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element =waitAndReturnElement(locator);

        element.clear();
        element.sendKeys(text);
    }

    protected void scrollToBottom() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getBodyText() {
        return waitAndReturnElement(By.tagName("body")).getText();
    }
}