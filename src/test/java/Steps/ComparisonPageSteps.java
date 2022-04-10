package Steps;

import Pages.ComparisonProductsPage.ComparisonProductsPage;
import Pages.ProductInformationForComparison;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ComparisonPageSteps {
    private static InformationAboutAddedProductCards informationAboutAddedProductCards = new InformationAboutAddedProductCards();
    private ComparisonProductsPage comparisonProductsPage;

    public ComparisonPageSteps() {
        comparisonProductsPage = ComparisonProductsPage.getComparisonProductsPage();
    }

    @Step("Отображается заголовок \"Сравнение товаров\"")
    public void checkTitleComparisonProductsDisplayed() {
        Assert.assertTrue(comparisonProductsPage.checkTitleComparisonProductsDisplayed());
    }

    public void saveProductCardInFavouritesPage() {
        informationAboutAddedProductCards.addListProductCard(comparisonProductsPage.addProductsCardWithComparisonInList());

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
