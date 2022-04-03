package Pages;

import Steps.Steps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartPage {
    List<ProductCard> productCardInCarts= new ArrayList<>();
    private static CartPage cartPage;
    @FindBy (xpath = "//div[contains(@class,'c-cart__order')]//li[contains(@class, 'c-cart-item')]")
    private ElementsCollection allProductsInCart;
    @FindBy(xpath = "//span[contains(@class, 'c-header-checkout__logo') and text()='Моя корзина']")
    private SelenideElement titleMyCart;
    @FindBy (xpath = "//div[contains(@class, 'c-orders-list__content')]//input[@data-action]")
    private SelenideElement buttonGoToDesign;
    @FindBy (xpath = "//div[contains(@class,'c-cost-line_multiple-lines')]//span[contains(@class,'c-cost-line__title')]")
    private SelenideElement inTheProductCartLine;
    @FindBy (xpath = "//div[contains(@class,'c-cost-line_multiple-lines')]//span[contains(@class,'c-cost-line__text')]")
    private SelenideElement costInTheGoToDesignBlock;
    @FindBy (xpath = "//span[contains(@class,'c-cost-line__text')]")
    private SelenideElement summProductsInCart;
    public boolean checkTitleMyCartDisplayed(){
        return titleMyCart.shouldBe(Condition.visible).isDisplayed();
    }

    public static CartPage getCartPage(){
        if(Objects.isNull(cartPage)) cartPage = Selenide.page(new CartPage());
        return cartPage;
    }
    public boolean checkButtonGoToDesignDisplayed(){
       return buttonGoToDesign.shouldBe(Condition.visible).isDisplayed();
    }
    public boolean checkInTheProductCartLine(String expectedLine){
        return inTheProductCartLine.getText().equals(expectedLine);
    }
    public boolean compareActualAndCostInTheDesignBlock(String actualCost){
        return costInTheGoToDesignBlock.getText().equals(actualCost);
    }

    /**
     * Все карточки товаров, которые есть в корзине
     */
    public List<ProductCard> getProductsCardWithCart(){
         productCardInCarts = allProductsInCart.stream().map(item->
        {return new ProductCard(item, 1);}).collect(Collectors.toList());
        return productCardInCarts;
    }
    public int getSummProductsInCart(){
        return Steps.stringToInt(summProductsInCart.getText());
    }
    public void clearCartPageData(){
       productCardInCarts.clear();
    }

}
