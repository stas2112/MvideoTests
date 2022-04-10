package Pages.HomePage;

import Pages.ProductInformationForComparison;
import com.codeborne.selenide.SelenideElement;

public class ProductCardForProductsFromCarousel {
    private SelenideElement productCardContainer;
    private SelenideElement productName;
    private SelenideElement productPrice;

    public ProductCardForProductsFromCarousel(SelenideElement selenideElement) {
        productCardContainer = selenideElement;
        productName = selenideElement;
        productPrice = selenideElement.$x("./ancestor::div[contains(@class, 'product-mini-card__name ')]/following-sibling::div" +
                "/following-sibling::div/following-sibling::div/following-sibling::div/following-sibling::div" +
                "//mvid-product-actions-tooltip[contains(@class,'tooltip-container__cart')]");
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void addProductToCart() {
        productCardContainer.$x(".//button").click();
    }

    public ProductInformationForComparison getProductInformationForComparison() {
        return new ProductInformationForComparison("ProductCardForProductsFromCarousel"
                , getProductName(), getProductPrice());
    }
}
