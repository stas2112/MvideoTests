package Pages.FavouritesProductPage;

import Pages.ProductInformationForComparison;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FavouritesProductPage {
    private static FavouritesProductPage favouritesProductPage;
    /**
     * Если перестаёт работать этот xpath, то проверить //div[contains(@style,'height: 48px')]
     */
    @FindBy(xpath = "//h3[contains(@class,'wishlist-product-title')]/a")
    private ElementsCollection nameProductsCard;
    @FindBy(xpath = "//h1[contains(@class,'wishlist-title')]")
    private SelenideElement titleComparisonProducts;

    public static FavouritesProductPage getFavouritesProductsCard() {
        if (Objects.isNull(favouritesProductPage)) favouritesProductPage = Selenide.page(new FavouritesProductPage());
        return favouritesProductPage;
    }

    public List<ProductInformationForComparison> addProductsCardWithFavouritesInList() {
        return nameProductsCard.stream().map(item -> {
            return new ProductCard(item).getProductInformationForComparison();}).collect(Collectors.toList());
    }

    public boolean checkTitleFavouritesProductsDisplayed() {
        return titleComparisonProducts.shouldBe(Condition.visible).isDisplayed()
                && titleComparisonProducts.is(Condition.text("Избранное"));
    }

}
