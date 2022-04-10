package Steps;

import Pages.ListingPage.ListingPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class ListingPageSteps {
    private ListingPage listingPage;
    private static InformationAboutAddedProductCards informationAboutAddedProductCards = new InformationAboutAddedProductCards();

    public ListingPageSteps() {
        listingPage = ListingPage.getListingPage();
    }

    public void saveAllProductCardsFromListingPage(int numberOfProductsOnThePage) {
        int countPaginationPage = listingPage.getPaginationPagesCount();
        int countProductLine = listingPage.getCountProductLine(numberOfProductsOnThePage);
        for (int i = 1; i < countPaginationPage; i++) {
            for (int j = 1; j < countProductLine; j++) {
                listingPage.productRowVisible(j);
                listingPage.scrollToProductRow(j);
                listingPage.productInProductRowVisible(j);
                informationAboutAddedProductCards.addListProductCard(listingPage.getListProductInformationFromOneLine(j));
            }
            listingPage.clickOnPaginationNumber(i + 1);
        }
    }

    /**
     * @param word Проверяем, что в каждой карточке товара после фильтрации есть указанное слово
     */
    @Step("Отображаются только товары содержащие в названии слово \"переменная\" в любом регистре")
    public void compareAllProductsHaveWordInName(String word) {
        Assert.assertTrue(informationAboutAddedProductCards.getAllInformationProductCard().stream().filter(item -> {
                    String temporaryString = item.getProductName();
                    return temporaryString.toLowerCase(Locale.ROOT).contains(word.toLowerCase(Locale.ROOT));
                })
                .collect(Collectors.toList()).size() == informationAboutAddedProductCards.getAllInformationProductCard().size());
    }

    @Step("Выбираем в списке значение \"переменная\"")
    public void selectOptionInDropDown(String nameOption) {
        listingPage.clickOnDropDown();
        listingPage.selectOptionInDropDown(nameOption);
    }

    @Step("цена следующего (слева-направо) меньше или равна текущего товара")
    public void comparePriceOfProductDescendingOrder() {
        List<Integer> allPrice = informationAboutAddedProductCards.getAllInformationProductCard().stream().map(item -> {
            System.out.println(item.getProductPrice());
            return item.getProductPrice();
        }).collect(Collectors.toList());
        Assert.assertTrue(allPrice.equals(allPrice.stream().sorted(Comparator.reverseOrder()).peek(item -> System.out.println(item)).collect(Collectors.toList())));

    }

    @Step("Добавляем \"Переменная\" товара в избранное")
    public void addCertainAmountInFavourites(int countAddedProducts) {
        listingPage.addCertainAmountInFavourites(countAddedProducts);
        informationAboutAddedProductCards.addListProductCard(listingPage.getListProductCardInOrder(countAddedProducts));
    }

    @Step("Добавляем \"Переменная\" товара в сравнение")
    public void addCertainAmountInComparison(int countAddedProducts) {
        listingPage.addCertainAmountInComparison(countAddedProducts);
        informationAboutAddedProductCards.addListProductCard(listingPage.getListProductCardInOrder(countAddedProducts));
    }

    public static InformationAboutAddedProductCards getInformationAboutAddedProductCards() {
        return informationAboutAddedProductCards;
    }

    public static void deleteInformationAboutAddedProductCards() {
        informationAboutAddedProductCards.clearAllInformationAboutProductCard();
    }
}
