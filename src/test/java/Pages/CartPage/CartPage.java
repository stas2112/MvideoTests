package Pages.CartPage;

import Steps.CommonSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartPage {
    private static CartPage cartPage;
    @FindBy(xpath = "//span[contains(@class, 'c-header-checkout__logo') and text()='Моя корзина']")
    private SelenideElement cartTitle;
    @FindBy(xpath = "//div[contains(@class, 'c-orders-list__content')]//input[@data-action]")
    private SelenideElement buttonGoToDesign;
    @FindBy(xpath = "//div[contains(@class,'c-cost-line_multiple-lines')]//span[contains(@class,'c-cost-line__title')]")
    private SelenideElement inTheProductCartLine;
    @FindBy(xpath = "//div[contains(@class,'c-cart__order')]/ul")
    private ElementsCollection productCardContainer;
    @FindBy(xpath = "//div[contains(@class,'c-cost-line_multiple-lines')]//span[contains(@class,'c-cost-line__text')]")
    private SelenideElement costInTheGoToDesignBlock;
    @FindBy(xpath = "//span[contains(@class,'c-cost-line__text')]")
    private SelenideElement summProductsInGoToDesignBlock;

    public boolean compareTitleIsMyCart() {
        return cartTitle.getText().equals("Моя корзина");
    }

    public boolean checkButtonGoToDesignIsVisible() {
        return buttonGoToDesign.is(Condition.visible);
    }

    public boolean checkProductCountLineInGoToDesignBlock(String expectedLine) {
        return inTheProductCartLine.getText().equals(expectedLine);
    }

    public static CartPage getCartPage() {
        if (Objects.isNull(cartPage)) cartPage = Selenide.page(new CartPage());
        return cartPage;
    }

    public List<ProductCard> getListProductCard() {
        return productCardContainer.stream().map(item -> {
            return new ProductCard(item);
        }).collect(Collectors.toList());
    }

    public int getProductsummProductsInGoToDesignBlock() {
        return CommonSteps.stringToInt(summProductsInGoToDesignBlock.getText());
    }
}
