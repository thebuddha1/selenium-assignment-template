import org.openqa.selenium.*;

class CartPage extends PageBase {

    private By addToCartButton = By.id("button-cart");
    private By cartButton = By.id("cart-total");
    private By viewCartButton = By.xpath("//strong[contains(text(),'View Cart')]");
    private By productName = By.cssSelector(".table-responsive tbody tr td.text-left a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        click(addToCartButton);
    }

    public void openCart() {
        click(cartButton);

        click(viewCartButton);
    }

    public String getCartProductText() {

        return waitAndReturnElement(productName).getText();
    }
}