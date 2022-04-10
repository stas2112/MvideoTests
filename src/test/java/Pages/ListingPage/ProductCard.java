package Pages.ListingPage;

import Pages.CommonPage;
import Pages.ProductInformationForComparison;
import Steps.CommonSteps;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private SelenideElement productName;
    private SelenideElement productPrice;
    private String productPriceString;
    private String productNameString;

    public ProductCard(SelenideElement selenideElement) {
        productName = selenideElement;
        if (productName.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]"
                + "/following-sibling::div/following-sibling::div//span[contains(@class,'price__main-value')]").isDisplayed()) {
            productPrice = selenideElement.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]"
                    + "/following-sibling::div/following-sibling::div//span[contains(@class,'price__main-value')]");
            productPriceString = productPrice.getText();
            productNameString = productName.getText();
        } else {
            productPriceString = "null";
            productNameString = productName.getText();
        }
    }
    public String getProductPrice(){
        if(productPriceString.equals("null")) return "0";
        else return productPriceString;
    }
    public ProductInformationForComparison getProductInformationForComparison() {
        return new ProductInformationForComparison("ProductsOfDayBlock", productNameString,getProductPrice());
    }
}
