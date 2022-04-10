package Pages.CartPage;

import Pages.ProductInformationForComparison;
import Steps.HomePageSteps;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private SelenideElement productCardContainer;
    private SelenideElement productName;
    private SelenideElement productPrice;

    public ProductCard(SelenideElement selenideElement) {
        productCardContainer = selenideElement;
        productName = selenideElement.$x(".//a[contains(@class,' c-cart-item__title')]");
        productPrice = selenideElement.$x(".//span[contains(@class,'c-cart-item__price')]");
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductInformationForComparison getProductInformationForComparison() {
        return new ProductInformationForComparison("ProductsOfDayBlock", getProductName(), getProductPrice());
    }
}
