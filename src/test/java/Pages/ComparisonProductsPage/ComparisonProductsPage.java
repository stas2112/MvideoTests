package Pages.ComparisonProductsPage;

import Pages.FavouritesProductPage.ProductCard;
import Pages.ProductInformationForComparison;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComparisonProductsPage {
    private static ComparisonProductsPage comparisonProductsPage;
    /**
     * Если перестаёт работать этот xpath, то проверить //div[contains(@style,'height: 48px')]
     */
    @FindBy(xpath = "//div[contains(@style,'height: 48px')]/h3/a[contains(@class,'sel-product-tile-title')]")
    private ElementsCollection nameProductsCard;
    @FindBy(xpath = "//h1[contains(@data-sel,'new_comparison-h1-page_title')]")
    private SelenideElement titleComparisonProducts;

    public static ComparisonProductsPage getComparisonProductsPage() {
        if (Objects.isNull(comparisonProductsPage)) comparisonProductsPage = Selenide.page(new ComparisonProductsPage());
        return comparisonProductsPage;
    }

    public List<ProductInformationForComparison> addProductsCardWithComparisonInList() {
        return nameProductsCard.stream().map(item -> {
                    return new ProductCard(item).getProductInformationForComparison();
                }).collect(Collectors.toList());
    }

    public boolean checkTitleComparisonProductsDisplayed() {
        return titleComparisonProducts.isDisplayed() && titleComparisonProducts.is(Condition.text("Сравнение товаров"));
    }

}

