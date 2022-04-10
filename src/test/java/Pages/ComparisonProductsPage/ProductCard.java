package Pages.ComparisonProductsPage;

import Pages.ProductInformationForComparison;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private SelenideElement elementOfCard;
    private SelenideElement productName;
    private SelenideElement productPrice;

    public ProductCard(SelenideElement selenideElement) {
        elementOfCard = selenideElement;
        productName = elementOfCard;
        productPrice = productPrice.$x("./ancestor::div[contains(@class, " +
                "'fl-product-tile__description' )]/following-sibling::div/following-sibling::div" +
                "//span[contains(@class,'fl-product-tile-price__current')]");
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public ProductInformationForComparison getProductInformationForComparison() {
        return new ProductInformationForComparison("ProductCardFavourites", getProductName(), getProductPrice());
    }
}
