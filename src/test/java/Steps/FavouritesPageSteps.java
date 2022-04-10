package Steps;

import Pages.FavouritesProductPage.FavouritesProductPage;
import Pages.ProductInformationForComparison;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class FavouritesPageSteps {
    private static InformationAboutAddedProductCards informationAboutAddedProductCards = new InformationAboutAddedProductCards();
    private FavouritesProductPage favouritesProductPage;

    public FavouritesPageSteps() {
        favouritesProductPage = FavouritesProductPage.getFavouritesProductsCard();
    }

    @Step("Отображается заголовок \"Избранное\"")
    public void checkTitleFavouritesProductsDisplayed() {
        Assert.assertTrue(favouritesProductPage.checkTitleFavouritesProductsDisplayed());
    }

    public void saveProductCardInFavouritesPage() {
        informationAboutAddedProductCards.addListProductCard(favouritesProductPage.addProductsCardWithFavouritesInList());

    }

    private boolean compareProductCartInCartEquallyAddedProductCard(String nameElement) {
        List<ProductInformationForComparison> actualProductCardList = informationAboutAddedProductCards.getAllInformationProductCard()
                .stream().filter(item -> item.getProductName().equals(nameElement)).collect(Collectors.toList());
        return actualProductCardList.size() == 1;

    }

    @Step("Отображаются карточки с именами добавленных товаров")
    public void allAddedProductCardExist() {
        saveProductCardInFavouritesPage();
        InformationAboutAddedProductCards productsCardAdded = ListingPageSteps.getInformationAboutAddedProductCards();
        Assert.assertTrue(productsCardAdded.getAllInformationProductCard().stream().filter(item ->
                compareProductCartInCartEquallyAddedProductCard(item.getProductName())).collect(Collectors.toList()).size()
                == informationAboutAddedProductCards.getAllInformationProductCard().size());

    }

    public static void deleteInformationAboutAddedProductCards() {
        informationAboutAddedProductCards.clearAllInformationAboutProductCard();
    }
}
