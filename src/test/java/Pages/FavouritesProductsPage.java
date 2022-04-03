package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavouritesProductsPage {
    private static FavouritesProductsPage favouritesProductsCard;
    private List<ProductCard> productCard = new ArrayList<>();
    /**
     * Если перестаёт работать этот xpath, то проверить //div[contains(@style,'height: 48px')]
     */
    @FindBy(xpath = "//h3[contains(@class,'wishlist-product-title')]/a")
    private ElementsCollection nameProductsCard;
    @FindBy(xpath = "//h1[contains(@class,'wishlist-title')]")
    private SelenideElement titleComparisonProducts;

    public static FavouritesProductsPage getFavouritesProductsCard(){
        if (Objects.isNull(favouritesProductsCard)){
            favouritesProductsCard = Selenide.page(new FavouritesProductsPage());
        }
        return favouritesProductsCard;
    }
    public void addProductsCardWithFavouritesInList(){
        nameProductsCard.stream().peek(item-> productCard.add(new ProductCard(item,6))).toArray();
    }
    public List<ProductCard> getProductCard(){
        return productCard;
    }
    public boolean checkTitleFavouritesProductsDisplayed(){
        return titleComparisonProducts.shouldBe(Condition.visible).isDisplayed()
                && titleComparisonProducts.is(Condition.text("Избранное"));
    }
    public void clearFavouritesData(){
        productCard.clear();
    }
}
