package Pages.FavouritesProductPage;

import Pages.ProductInformationForComparison;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private SelenideElement elementOfCard;
    private SelenideElement productName;
    private SelenideElement productPrice;

    public ProductCard(SelenideElement selenideElement) {
        elementOfCard = selenideElement;
        productName = elementOfCard;
        productPrice = productName.$x("./ancestor::div[contains(@class,'wishlist-product-info-header')]" +
                "/following::div[contains(@class,'wishlist-price__current')]");
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
