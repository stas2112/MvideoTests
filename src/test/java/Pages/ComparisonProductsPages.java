package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComparisonProductsPages {
    private static ComparisonProductsPages comparisonProductsCard;
    List<ProductCard> productCard = new ArrayList<>();
    /**
     * Если перестаёт работать этот xpath, то проверить //div[contains(@style,'height: 48px')]
     */
    @FindBy(xpath = "//div[contains(@style,'height: 48px')]/h3/a[contains(@class,'sel-product-tile-title')]")
    private ElementsCollection nameProductsCard;
    @FindBy(xpath = "//h1[contains(@data-sel,'new_comparison-h1-page_title')]")
    private SelenideElement titleComparisonProducts;

    public static ComparisonProductsPages getComparisonProductsCard(){
        if (Objects.isNull(comparisonProductsCard)){
            comparisonProductsCard = Selenide.page(new ComparisonProductsPages());
        }
        return comparisonProductsCard;
    }
    public void addProductsCardWithComparisonInList(){
    nameProductsCard.stream().peek(item-> productCard.add(new ProductCard(item,5))).toArray();
    }
    public List<ProductCard> getProductCard(){
        return productCard;
    }
    public boolean checkTitleComparisonProductsDisplayed(){
        return titleComparisonProducts.isDisplayed() && titleComparisonProducts.is(Condition.text("Сравнение товаров"));
    }
    public void clearComparisonData(){
        productCard.clear();
    }

    }

