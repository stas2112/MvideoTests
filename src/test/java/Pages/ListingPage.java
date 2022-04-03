package Pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListingPage {
    private List<ProductCard> productCard = new ArrayList<>();
    private static ListingPage listingPage;
    private String currentlyPageNumber= "//a[contains(@class,'page-link') and text()='%s']";
    @FindBy(xpath = "//mvid-filters-list")
    private SelenideElement divFilter;
    @FindBy(xpath = "//div[contains(@class,'product-card__title-line-container')]//div/a")
    private ElementsCollection nameOfProductForProductCard;
    @FindBy (xpath = "//div[contains(@class, 'footer__link-group ng-star-inserted')]")
    private SelenideElement footerLinkOfficialInformation;
    @FindBy(xpath = "//a[contains(@class, 'page-link')]")
    private ElementsCollection paginationNumbers;
    @FindBy (xpath = "//div[contains(@class,'product-cards-row')]")
    private ElementsCollection listOfProductLine;
    @FindBy (xpath = "//div[contains(@class, 'dropdown__title')]")
    private SelenideElement dropdownInListingPage;

    public void checkFilterDisplayed(){
        divFilter.shouldBe(Condition.exist);
    }

    /**
     * Добавляем все карточки найденные на странице
     */
    public void getAllProductsOnPageInList(int ProductQuantityPerPage){
        nameOfProductForProductCard.stream().peek(item->{
            productCard.add( new ProductCard(item, 4));}).toArray();
    }
    public List<ProductCard> getProductCard(){return productCard;}

    /**
     * Инициализация для Steps
     */
    public static ListingPage getListingPage(){
        if(Objects.isNull(listingPage)) listingPage = Selenide.page(new ListingPage());
        return listingPage;
    }

    /**
     * @return Возвращаем сколько всего страниц пагинации
     */
    public int getPaginationPagesCount(){
        List<SelenideElement> allPaginationNumber;
        paginationNumbers.get(0).shouldBe(Condition.exist);
        allPaginationNumber=  paginationNumbers.stream().filter(item-> item!=null && !item.getText().equals("...")&&
                !item.getText().equals("")).collect(Collectors.toList());
        return Integer.parseInt(allPaginationNumber.get(allPaginationNumber.size()-1).getText());
    }

    public void selectOptionInDropDown(String nameOption){
        dropdownInListingPage.click();
        Selenide.$x("//div[contains(@class, 'dropdown__options')]/div[contains(text(),'"+nameOption+"')]")
                .shouldBe(Condition.visible).click();
    }

    /**
     * Возвращаем число страниц на list-page
     */
    public void setPageNumber(int pageNumber){
        Selenide.$x(String.format(currentlyPageNumber,pageNumber)).click();
    }

    /**
     * Делаем видимыми все карточки товаров
     * @param ProductQuantityPerPage Параметр "Показывать по", сколько показывает продуктов на 1 странице
     */
    public void scrollToProductPages(int ProductQuantityPerPage){
        listOfProductLine.stream().peek(item-> {item.shouldBe(Condition.visible).scrollTo();
            item.$$x(".//div[contains(@class,'product-title')]/a").stream().peek(item1->item1.shouldBe(Condition.visible));})
                .toArray();
    }
    public void setExpectationProductCard(){
        Configuration.timeout = 20000;
        listOfProductLine.shouldBe(CollectionCondition.size(6));
   }

    /**
     * Добавляем определённое количество Карточек продукта в список
     */
   public void addCertainAmountInListProductCard(int countAddedProductCard){
       nameOfProductForProductCard.stream().limit(countAddedProductCard).peek(item->{
           productCard.add(new ProductCard(item, 4));}).collect(Collectors.toList());
   }

    /**
     * @param countAddedProductCard Кол-во добавляемых товаров в сравнение(до 12)
     */
   public void addCertainAmountInComparison(int countAddedProductCard){
       nameOfProductForProductCard.stream().limit(countAddedProductCard).peek(item->
              item.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]/following-sibling::div" +
                      "/following-sibling::div/following-sibling::div/following-sibling::mvid-plp-product-checkout" +
                      "//button[contains(@title,'Добавить в сравнение')]").shouldBe(Condition.visible).click())
               .collect(Collectors.toList());
   }

   public void addCertainAmountInFavourites(int countAddedProductCard){
       nameOfProductForProductCard.stream().limit(countAddedProductCard).peek(item->
                       item.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]/following-sibling::div" +
                               "/following-sibling::div/following-sibling::div/following-sibling::mvid-plp-product-checkout" +
                               "//button[contains(@title,'Добавить в избранное')]").shouldBe(Condition.visible).click())
               .collect(Collectors.toList());

   }

   public void clearListingPageData(){
       productCard.clear();
   }


}

