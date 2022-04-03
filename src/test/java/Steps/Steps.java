package Steps;

import Pages.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class Steps {
    private HomePage homePage;
    private CartPage cartPage;
    private ListingPage listingPage;
    private ComparisonProductsPages comparisonProductsPages;
    private FavouritesProductsPage favouritesProductsPage;
    private List<ProductCard> productCardFromHomePage;
    private List<ProductCard> productCardFromCart;
    /**
     * Инициализируем наши pages
     */
    public Steps(){
        homePage = HomePage.getHomePage();
        cartPage = CartPage.getCartPage();
        listingPage = ListingPage.getListingPage();
        comparisonProductsPages = ComparisonProductsPages.getComparisonProductsCard();
        favouritesProductsPage = FavouritesProductsPage.getFavouritesProductsCard();
    }
    /**
     * отключен ли элемент в хедере (элемент ищется по названию)
     */
    public void checkNavTabElementIsDisabled(String nameTabElement){
       Assert.assertTrue(homePage.checkNavTabElementIsDisabled(nameTabElement));
    }

    /**
     * По названию иконки в хедере проверяет элемент на  Disabled
     */
    public void checkNavTabElementIsNotDisabled(String nameTabElement){
        Assert.assertFalse(homePage.checkNavTabElementIsDisabled(nameTabElement));
    }

    /**
     * По названию иконки в хедере проверяет элемент на Displayed
     */
    public void checkNavTabElementIsDisplayed(String nameTabElement){
        Assert.assertTrue(homePage.checkNavTabElementIsDisplayed(nameTabElement));
    }

    /**
     * Отображается ли блок "Товары дня"
     */
    public void checkProductsOfDayIsDisplayed(){
      Assert.assertTrue( homePage.checkProductsOfDayDisplayed());
    }

    /**
     * Нажимаем на Кнопку "В корзине" в блоке "Товары дня"
     */
    public void clickOnButtonAddToCartInProductsOfDay(){
        homePage.clickOnButtonAddToCart();}

    /**
     * Сравнивает введённое и актуальное значение товара в корзине
     * @param countOfProductsInCart Сколько должно отображаться товара в корзине в хедере
     */
    public void compareNumberOfProductsInCart(int countOfProductsInCart){
        Assert.assertTrue( homePage.compareNumberOfProductsInTheCart(countOfProductsInCart));
    }

    /**
     * Нажимаем на "корзину"
     */
    public void clickOnCartOnHomePage(){
        homePage.clickOnCartOnHomePage();
    }

    /**
     * Сравнивает предпологаемый URL с актуальным
     * @param linkName часть, либо весь URL, на котором мы должны находится
     */
    public static void verificationLinkIncludes(String linkName){
        Assert.assertTrue( WebDriverRunner.url().contains(linkName));
    }

    /**
     *Проверяем на странице "Корзина" отображение заголовка "Моя корзина"
     */
    public void checkTitleMyCartDisplayed(){
       Assert.assertTrue( cartPage.checkTitleMyCartDisplayed());
    }

    /**
     * Проверяет надпись напротив цены всех товаров в Корзине в блоке "Перейти к оформлению"
     * lineValue - вводить в формате "В корзине n товара", где n- число товара, которое должно быть
     */
    public void checkInTheProductCartLine(String lineValue){
        Assert.assertTrue( cartPage.checkInTheProductCartLine(lineValue));
    }

    /**
     * Сверяет цену Товара(одного) в корзине и цену в блоке "Перейти к оформлению"
     */
    public void compareActualAndCostInTheDesignBlock(){
        productCardFromCart = cartPage.getProductsCardWithCart();
        Assert.assertTrue( cartPage.compareActualAndCostInTheDesignBlock(productCardFromCart.get(0).getPriceElementOfCard()));
    }

    /**
     * Проверяет кнопку "Перейти к оформлению" в блоке "Перейти к оформлению" в корзине
     */
    public void checkButtonGoToDesignDisplayed(){
        Assert.assertTrue( cartPage.checkButtonGoToDesignDisplayed());
    }

    /**
     * Принимаем имя конкретного товара, который должен отображаться и находим его карточку, с которой можно будет дальше
     * проводить какие либо действия
     */
    public ProductCard checkNameAddedWithProductsDayInProductIn(String nameElement){
        productCardFromCart = cartPage.getProductsCardWithCart();
        List<ProductCard> actualProductCardList= productCardFromCart.stream().filter(item-> item
                .getNameElementOfCard().equals(nameElement)).collect(Collectors.toList());
        ProductCard actualProductCard = actualProductCardList.get(0);
        return actualProductCard;
    }

    /**
     * @param nameElement  Принимаем искомый элемент
     * @return Возвращаем, что элемент с добавленным названием в корзине
     */
    public boolean compareByNameAddedWithCart(String nameElement){
        productCardFromCart = cartPage.getProductsCardWithCart();
        List<ProductCard> actualProductCardList= productCardFromCart.stream().filter(item-> item
                .getNameElementOfCard().equals(nameElement)).collect(Collectors.toList());
        return actualProductCardList.size()==1;
    }


    /**
     * сопоставляем названия, добавленные и которые в карточке, чтобы они совпадали
     *
     */
    public void checkNameAddedInCart(){
        productCardFromHomePage = homePage.getListProductCardFromProductsDay();
        productCardFromCart = cartPage.getProductsCardWithCart();
     Assert.assertTrue( productCardFromCart.stream().filter(item->compareByNameAddedWithCart(
                     (item.getNameElementOfCard()))).collect(Collectors.toList()).size()== productCardFromCart.size());
    }

    /**
     * Собираем все карточки в "Сравнении" в лист и сверяем, что все добавленные товары находится в сравнении
     */
    public void checkNameAddedInComparison(){
        comparisonProductsPages.addProductsCardWithComparisonInList();
        productCardFromHomePage = listingPage.getProductCard();
        productCardFromCart = comparisonProductsPages.getProductCard();
        Assert.assertTrue( productCardFromCart.stream().filter(item->compareByNameAddedWithCart(
                (item.getNameElementOfCard()))).collect(Collectors.toList()).size()== productCardFromCart.size());
    }
    /**
     * Собираем все карточки в "Сравнении" в лист и сверяем, что все добавленные товары находится в сравнении
     */
    public void checkNameAddedInFavourites(){
        favouritesProductsPage.addProductsCardWithFavouritesInList();
        productCardFromHomePage = listingPage.getProductCard();
        productCardFromCart = favouritesProductsPage.getProductCard();
        Assert.assertTrue( productCardFromCart.stream().filter(item->compareByNameAddedWithCart(
                (item.getNameElementOfCard()))).collect(Collectors.toList()).size()== productCardFromCart.size());
    }

    /**
     * Нажимаем на "Добавить в корзину" в блоке Самые просматриваемые и добавляем их в наш List<ProductCard>
     * @param countOfAddProduct Кол-во добавляемых товаров(добавляются по очереди)
     */
    public void clickOnButtonAddToCartInMostView(int countOfAddProduct){
        homePage.clickOnButtonAddToCartInMostView(countOfAddProduct);
        homePage.addInListProductCardFromMostViewed(countOfAddProduct);
    }

    /**
     * Проверяет Блок "Самые просматриваемые" на главной странице
     */
    public void checkBlockMostViewedDisplayed(){
        Assert.assertTrue(homePage.checkBlockMostViewedDisplayed());
    }

    /**
     * Строка поиска отображается
     */
    public void checkSearchLineDisplayed(){
        Assert.assertTrue(homePage.checkSearchLineDisplayed());
    }
    /**
     * Вводим в строку поиска значение
     */

    public void setSearchLineValue(String value){
        homePage.setSearchLineValue(value);
    }

    public void clickSearchButton(){
        homePage.clickSearchButton();
        listingPage.checkFilterDisplayed();
    }
    public void checkDivFilterDisplayed(){
        listingPage.checkFilterDisplayed();
    }

    /**
     * Сверяем сумму добавленных товаров с суммой товаров в корзине и в блоке "Перейти к оформлению"
     */
    public void checkSumAddedProductsEquallySummProductsInCart(){
        int productCardSumm= 0;
        int productAddedProductsSumm= 0;
        productCardFromHomePage = homePage.getListProductCardFromProductsDay();
        productCardFromCart = cartPage.getProductsCardWithCart();
        for(int i=0; i<productCardFromCart.size();i++){
            productCardSumm = productCardSumm + stringToInt(productCardFromCart.get(i).getPriceElementOfCard());
        }
        for(int i=0; i<productCardFromHomePage.size();i++){
            productAddedProductsSumm = productAddedProductsSumm + stringToInt(productCardFromHomePage.get(i).getPriceElementOfCard());
        }
        Assert.assertTrue(productAddedProductsSumm==productCardSumm&& productAddedProductsSumm== cartPage.getSummProductsInCart());

    }


    /**
     * Функция переводит формат String в int (Использовать вместо parseInt, т.к в строках встречаются символы)
     */
    public static int stringToInt(String string){
        String newString= "";
        for (int i=0; i<string.length();i++){
            if(string.charAt(i)=='0'||string.charAt(i)=='1'||string.charAt(i)=='2'||string.charAt(i)=='3'
                    ||string.charAt(i)=='4'||string.charAt(i)=='5'||string.charAt(i)=='6'||string.charAt(i)=='7'
                    ||string.charAt(i)=='8'||string.charAt(i)=='9') {
             newString =newString + string.charAt(i);
            }
            }
        if (newString.equals("")){return 0;}
        return Integer.parseInt(newString);
        }

    /**
     * Получаем все ProductPage со всех страниц (нужно для проверки фильтрации)
     */
    public void getAllProductCardsInListPage(int ProductQuantityPerPage){
        for(int i= 1; i<listingPage.getPaginationPagesCount(); i++){
        listingPage.setExpectationProductCard();
        listingPage.scrollToProductPages(ProductQuantityPerPage);
        listingPage.getAllProductsOnPageInList(ProductQuantityPerPage);
        listingPage.setPageNumber(i+1);
        }
    }

    /**
     * @param word Слово, которое содержится в каждом productCard
     */
    public void compareAllProductsHaveWordInName(String word){
        List<ProductCard> productCard= listingPage.getProductCard();
        productCard.stream().peek(item->System.out.println(item.getNameElementOfCard())).toArray();
       Assert.assertTrue(productCard.stream().filter(item->{String temporaryString= item.getNameElementOfCard();
           return temporaryString.toLowerCase(Locale.ROOT).contains(word.toLowerCase(Locale.ROOT));
       }).collect(Collectors.toList()).size() == productCard.size());
    }

    /**
     * Получаем массив чисел и проверяем, что массив идёт по порядку
     */
    public void compareAllProductsPriceOnTheDecline(){
        int maxValue= 1000000000;
        List<ProductCard> productCard= listingPage.getProductCard();
       List<Integer> countPrice = productCard.stream().filter(item-> !item.getPriceElementOfCard().equals("null")).map(item->
                stringToInt(item.getPriceElementOfCard())).collect(Collectors.toList());
       for(Integer i: countPrice){
           if(i<=maxValue){
               maxValue = i;
           }
           else {Assert.assertTrue(false);}
       }
       Assert.assertTrue(true);
    }

    /**
     * Возвращаемся к первой страницу
     */
    public void backToFirstPaginationPages(){
        listingPage.setPageNumber(1);
    }

    /**
     * Выбираем определённый параметр из выпадающего списка
     */
    public void selectOptionInDropDown(String optionName){
        listingPage.selectOptionInDropDown(optionName);
    }

    /**
     * Нажимаем на элемент хедера, необходимо указать его название
     */
    public void clickOnNavTabElement(String navTabElementName){
        homePage.clickOnNavTabElement(navTabElementName);
    }

    /**
     * Отображается ли модальное окно (использовать только после нажатия на елемент "Войти")
     */
    public void checkLoginModalWindowDiplayed(){
        Assert.assertTrue(homePage.checkLoginModalWindowDiplayed());
    }

    /**
     * Отображается кнопка закрытия модального окна у модального окна "Войти"
     */
    public void checkExitLoginModalWindowDisplayed(){
        Assert.assertTrue(homePage.checkExitLoginModalWindowDisplayed());
    }

    /**
     * Заголовок у модального окна "Войти" "Вход и регистрация"
     */
    public void checkTitleLoginModalWindowDisplayed(){
        Assert.assertTrue(homePage.checkTitleLoginModalWindowDisplayed());
    }

    /**
     * Проверяет, что есть поле ввода телефона (проверяет, что в нём написано "Телефон") в модальном окне "Войти"
     */
    public void checkInputNumberPhoneDisplayed(){
        Assert.assertTrue(homePage.checkInputNumberPhoneDisplayed());
    }

    /**
     * Проверяет, что кнопка "Продолжить" отображается и не активна в модальном окне "Войти"
     */
    public void checkContinueButtonInLoginModalWindow(){
        Assert.assertTrue(homePage.checkContinueButtonInLoginModalWindow());
    }

    /**
     * Добавляем определённое кол-во карточек товара в "Сравнение" и добавленные карточки в лист карточек
     */
    public void addCertainAmountInComparison(int countAddedProductCard) {
        listingPage.addCertainAmountInListProductCard(countAddedProductCard);
        listingPage.addCertainAmountInComparison(countAddedProductCard);
    }
    /**
     * Добавляем определённое кол-во карточек товара в "Сравнение" и добавленные карточки в лист карточек
     */
    public void addCertainAmountInFavourites(int countAddedProductCard) {
        listingPage.addCertainAmountInListProductCard(countAddedProductCard);
        listingPage.addCertainAmountInFavourites(countAddedProductCard);
    }

    /**
     * Проверяет, что заголовок "Сравнение товаров" есть на странице и в нём текст "Сравнение товаров"
     */
    public void checkTitleComparisonProductsDisplayed(){
        Assert.assertTrue(comparisonProductsPages.checkTitleComparisonProductsDisplayed());
    }
    /**
     * Проверяет, что заголовок "Избранное" есть на странице и в нём текст "Избранное"
     */
    public void checkTitleFavouritesProductsDisplayed(){
        Assert.assertTrue(favouritesProductsPage.checkTitleFavouritesProductsDisplayed());
    }

    /**
     * Нажимаем на кнопку выбора города (в хедере)
     */
    public void clickCurrentCity(){
        homePage.clickCurrentCity();
    }

    /**
     * Проверяем, что модальное окно появилось и содержит в себе Title
     */
    public void checkModalWindowChoseCityHaveTitle(){
        homePage.checkModalWindowChoseCityHaveTitle();
    }
    /**
     * Выбираем город по названию аттрибута метода
     */

    public void clickOnSelectedCity(String cityName){
        homePage.clickOnSelectedCity(cityName);
    }
    /**
     * Проверяем, что модальное окно закрылось
     */
    public void checkModalWindowNotDisplayed(){
        homePage.checkModalWindowNotDisplayed();
    }
    /**
     * Проверяем текущее местоположение
     */
    public void checkCurrentNameCity(String cityName){
        homePage.checkCurrentNameCity(cityName);
    }

    /**
     * Очищаем все данные со всех пейджей
     */
    public void clearAllData(){
        homePage.clearHomePageData();
        cartPage.clearCartPageData();
        listingPage.clearListingPageData();
        comparisonProductsPages.clearComparisonData();
        if(!Objects.isNull(productCardFromHomePage)) productCardFromCart.clear();
        if(!Objects.isNull(productCardFromHomePage)) productCardFromHomePage.clear();}

    }



