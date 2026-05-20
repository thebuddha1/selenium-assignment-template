import org.openqa.selenium.*;

class SearchResultPage extends PageBase {

    private By firstProduct = By.cssSelector(".product-thumb h4 a");

    public SearchResultPage(WebDriver driver) {

        super(driver);
    }

    public String getFirstProductText() {
        return waitAndReturnElement(firstProduct).getText();
    }

    public CartPage openFirstProduct() {
        click(firstProduct);

        return new CartPage(driver);
    }
}