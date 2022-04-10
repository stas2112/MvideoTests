package Steps;

import Pages.CartPage.CartPage;
import Pages.CartPage.ProductCard;
import Pages.HomePage.HomePage;
import Pages.HomePage.ProductsOfDayBlock;
import Pages.ProductInformationForComparison;
import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.jsontype.impl.AsExternalTypeSerializer;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPageSteps {
    private List<ProductInformationForComparison> productCardInCart = new ArrayList<>();
    private List<ProductInformationForComparison> productsCardFromHomePage = new ArrayList<>();
    private HomePageSteps homePageSteps;
    CartPage cartPage;

    public CartPageSteps() {
        cartPage = CartPage.getCartPage();
    }

    @Step("Отображается заголовок \"Моя корзина\"")
    public void compareTitleIsMyCartInCart() {
        Assert.assertTrue(cartPage.compareTitleIsMyCart());
    }

    @Step("Отображается кнопка \"Перейти к оформлению\"")
    public void checkButtonGoToDesignIsVisibleInCart() {
        Assert.assertTrue(cartPage.checkButtonGoToDesignIsVisible());
    }

    @Step("Отображается текст \"В корзине \"Указанная переменная\" товар\" ")
    public void checkProductCountLineInGoToDesignBlockInCart(String expectedValue) {
        Assert.assertTrue(cartPage.checkProductCountLineInGoToDesignBlock(expectedValue));
    }

    /**
     * Проверяем по имени, существует ли Product card в корзине
     */
    @Step("цена совпадает с ценой товара")
    private boolean compareProductCartInCartEquallyAddedProductCard(String nameElement) {
        List<ProductInformationForComparison> temporaryProductCardInCart = cartPage.getListProductCard().stream().map(item -> {
            return item.getProductInformationForComparison();
        }).collect(Collectors.toList());
        List<ProductInformationForComparison> actualProductCardList = temporaryProductCardInCart.stream().filter(item -> item.getProductName()
                .equals(nameElement)).collect(Collectors.toList());
        return actualProductCardList.size() == 1;

    }

    @Step("Отображаются карточки с наименованиями добавленных товаров")
    public void checkAllProductCardAddedInCart() {
        productsCardFromHomePage = HomePageSteps.getInformationAboutAddedProductCards().getAllInformationProductCard();
        productCardInCart = getProductCardInCart();
        Assert.assertTrue(productsCardFromHomePage.stream().filter(item ->
                compareProductCartInCartEquallyAddedProductCard(item.getProductName())).collect(Collectors.toList()).size()
                == productsCardFromHomePage.size());
    }

    @Step("Сумма всей корзины равна сумме цен добавленных товаров")
    public void compareSummProductInCartEquallyCalculatedFromGoToDesignBlock() {
        int summProductInCart = 0;
        productCardInCart = getProductCardInCart();
        List<Integer> priceProductInCart = productCardInCart.stream().map(item -> {
            return item.getProductPrice();
        }).collect(Collectors.toList());
        for (int temporaryInt : priceProductInCart) {
            summProductInCart = summProductInCart + temporaryInt;
        }
        Assert.assertTrue(summProductInCart == cartPage.getProductsummProductsInGoToDesignBlock());
    }

    private List<ProductInformationForComparison> getProductCardInCart() {
        return productCardInCart = cartPage.getListProductCard().stream().map(item -> {
            return item.getProductInformationForComparison();
        }).collect(Collectors.toList());
    }
}
