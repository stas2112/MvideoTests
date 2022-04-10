package Pages.ListingPage;

import Pages.ProductInformationForComparison;
import com.codeborne.selenide.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListingPage {
    private static ListingPage listingPage;
    private final String XPATH_PRODUCTS_ROW = "//div[contains(@class, 'product-cards-row')]";
    private final String XPATH_PRODUCTS_ROW_WITH_NUM = XPATH_PRODUCTS_ROW + "[%s]";
    private String currentlyPageNumber = "//a[contains(@class,'page-link') and text()='%s']";
    private String filterContainer = "//mvid-filters-list";
    @FindBy(xpath = "//div[contains(@class,'product-card__title-line-container')]//div/a")
    private ElementsCollection nameOfProductForProductCard;
    @FindBy(xpath = "//a[contains(@class, 'page-link')]")
    private ElementsCollection paginationNumbers;
    @FindBy(xpath = "//div[contains(@class,'product-cards-row')]")
    private ElementsCollection listOfProductLine;
    @FindBy(xpath = "//div[contains(@class, 'dropdown__title')]")
    private SelenideElement dropdownInListingPage;


    /**
     * @return Возвращаем сколько всего страниц пагинации
     */
    public int getPaginationPagesCount() {
        List<SelenideElement> allPaginationNumber;
        paginationNumbers.get(0).shouldBe(Condition.exist);
        allPaginationNumber = paginationNumbers.stream().filter(item -> item != null && !item.getText().equals("...") &&
                !item.getText().equals("")).collect(Collectors.toList());
        return Integer.parseInt(allPaginationNumber.get(allPaginationNumber.size() - 1).getText());
    }

    public void checkFilterVisible() {
        Selenide.$x(filterContainer).shouldBe(Condition.visible);
    }

    /**
     * На какую страницу переключаемся
     */
    public void clickOnPaginationNumber(int pageNumber) {
        Selenide.$x(String.format(currentlyPageNumber, pageNumber)).click();
    }

    public static ListingPage getListingPage() {
        if (Objects.isNull(listingPage)) listingPage = Selenide.page(new ListingPage());
        return listingPage;
    }

    public void scrollToProductRow(int numberProductRow) {
        Selenide.$x(String.format(XPATH_PRODUCTS_ROW_WITH_NUM, numberProductRow)).scrollTo();
    }

    public void productRowVisible(int nubmerRow) {
        Selenide.$x(String.format(XPATH_PRODUCTS_ROW_WITH_NUM, nubmerRow)).shouldBe(Condition.visible);
    }

    public void productInProductRowVisible(int numberProductRow) {
        Selenide.$x(String.format(XPATH_PRODUCTS_ROW_WITH_NUM, numberProductRow) + "//a[contains(@class, 'product-title__text')]")
                .shouldBe(Condition.visible);
    }

    public int getCountProductLine(int numberOfProductsOnThePage) {
        return listOfProductLine.shouldBe(CollectionCondition.sizeGreaterThan((numberOfProductsOnThePage / 4) - 1)).size();
    }

    public List<ProductInformationForComparison> getListProductInformationFromOneLine(int numberProductRow) {
        return Selenide.$$x(String.format(XPATH_PRODUCTS_ROW_WITH_NUM, numberProductRow)
                + "//a[contains(@class, 'product-title__text')]").stream().map(item ->
        {
            return new ProductCard(item).getProductInformationForComparison();
        }).collect(Collectors.toList());
    }

    public void clickOnDropDown() {
        dropdownInListingPage.click();
    }

    public void selectOptionInDropDown(String nameOption) {
        Selenide.$x("//div[contains(@class, 'dropdown__options')]/div[contains(text(),'" + nameOption + "')]")
                .shouldBe(Condition.visible).click();
    }

    public void addCertainAmountInFavourites(int countAddedProductCard) {
        nameOfProductForProductCard.stream().limit(countAddedProductCard).forEach(item ->
                        item.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]/following::div" +
                                "//button[contains(@title,'Добавить в избранное')]").click());
    }
    public void addCertainAmountInComparison(int countAddedProductCard){
        nameOfProductForProductCard.stream().limit(countAddedProductCard).forEach(item->
                        item.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]/following::div" +
                                "//button[contains(@title,'Добавить в сравнение')]").click());
    }

    public List<ProductInformationForComparison> getListProductCardInOrder(int countAddedProductCard){
      return nameOfProductForProductCard.stream().limit(countAddedProductCard).map(item->
        {return new ProductCard(item).getProductInformationForComparison();}).collect(Collectors.toList());
    }
}
