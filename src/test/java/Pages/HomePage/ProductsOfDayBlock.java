package Pages.HomePage;

import Pages.ProductInformationForComparison;
import Steps.HomePageSteps;
import com.codeborne.selenide.SelenideElement;

public class ProductsOfDayBlock {
    private SelenideElement productName;
    private SelenideElement productPrice;
    private SelenideElement productCardContainer;

    public ProductsOfDayBlock(SelenideElement selenideElement) {
        productCardContainer = selenideElement;
        productName = selenideElement.$x(".//div[contains(@class, 'title')]/a");
        productPrice = selenideElement.$x(".//span[contains(@class,'price__main-value')]");
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
        return new ProductInformationForComparison("ProductsOfDayBlock", getProductName(), getProductPrice());
    }
}
