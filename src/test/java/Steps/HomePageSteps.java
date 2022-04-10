package Steps;

import Pages.HomePage.HomePage;
import Pages.HomePage.ProductsOfDayBlock;
import Pages.ListingPage.ListingPage;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

public class HomePageSteps {
    private HomePage homePage;

    private static InformationAboutAddedProductCards informationAboutAddedProductCards = new InformationAboutAddedProductCards();

    public HomePageSteps() {
        homePage = HomePage.getHomePage();
    }

    @Step("Элемент не активен")
    public void checkNavTabElementIsDisplayed(String nameElement) {
        Assert.assertTrue(homePage.checkNavTabElementIsDisplayed(nameElement));
    }

    @Step("Элемент отображается")
    public void checkNavTabElementIsDisabled(String nameElement) {
        Assert.assertTrue(homePage.checkNavTabElementIsDisabled(nameElement));
    }

    @Step("Элемент активен")
    public void checkNavTabElementIsNotDisabled(String nameElement) {
        Assert.assertFalse(homePage.checkNavTabElementIsDisabled(nameElement));
    }

    @Step("На странице отображается блок \"Товары дня\"")
    public void checkProductOfDayContainerIsVisible() {
        Assert.assertTrue(homePage.checkProductOfDayContainerIsVisible());
    }

    @Step("Нажимаем у товара на кнопку \"В корзину\"")
    public void addProductOfDayToCart() {
        ProductsOfDayBlock productsOfDayBlock = homePage.getProductsOfDay();
        informationAboutAddedProductCards.addOneProductCard(productsOfDayBlock.getProductInformationForComparison());
        productsOfDayBlock.addProductToCart();
    }

    @Step("Нажимаем на кнопку \"Корзина\"")
    public void clickOnCart() {
        homePage.clickOnCart();
    }

    @Step("На кнопке \"Корзина\" отображается число \"Переменная\"")
    public void compareNumberAddedProductsEquallyProductsInCart(String countProductsInCart) {
        Assert.assertTrue(homePage.compareNumberAddedProductsEquallyProductsInCart(countProductsInCart));
    }

    /**
     * Возвращаем товары, добавленные на странице HomePage
     */
    public static InformationAboutAddedProductCards getInformationAboutAddedProductCards() {
        return informationAboutAddedProductCards;
    }

    public static void deleteInformationAboutAddedProductCards() {
        informationAboutAddedProductCards.clearAllInformationAboutProductCard();
    }

    public void checkMostViewedBlockIsVisible() {
        Assert.assertTrue(homePage.checkBlockMostViewedVisible());
    }

    public void addProductsFromMostViewedInCart(int countAddedProducts) {
        homePage.clickAddProductsInMostView(countAddedProducts);
        informationAboutAddedProductCards.addListProductCard(homePage.getListProductInformation(countAddedProducts));
        homePage.checkAddToCartButtonInMostViewIsActive(countAddedProducts);
    }

    @Step("В шапке отображается строка поиска товаров")
    public void checkSearchLineIsDisplayed() {
        Assert.assertTrue(homePage.checkSearchLineIsDisplayed());
    }

    @Step("Вводим в  строку поиска товаров \"Переменную\"")
    public void setValueInSearchLine(String value) {
        homePage.setSearchLineValue(value);
    }

    @Step("Нажимаем кнопку поиска")
    public void clickSearchButton() {
        homePage.clickSearchButton();
        ListingPage listingPage = new ListingPage();
        listingPage.checkFilterVisible();
    }

    public void checkLoginModalWindowDisplayed() {
        Assert.assertTrue(homePage.checkLoginModalWindowIsDisplayed());
    }

    @Step("Отображается ссылка \"Для юридических лиц\"")
    public void checkForLegalEntitiesDisplayed() {
        Assert.assertTrue(homePage.checkForLegalEntitiesDisplayed());
    }

    @Step("Отображается кнопка закрытия модального окна")
    public void checkExitLoginModalWindowDisplayed() {
        Assert.assertTrue(homePage.checkExitLoginModalWindowDisplayed());
    }

    @Step("Отображается модальное окно с заголовком Вход или регистрация")
    public void checkTitleLoginModalWindowDisplayed() {
        Assert.assertTrue(homePage.checkTitleLoginModalWindowDisplayed());
    }

    @Step("Отображается поле для ввода текста с плейсхолдером \"Телефон\"")
    public void checkInputNumberPhoneDisplayed() {
        Assert.assertTrue(homePage.checkInputNumberPhoneDisplayed());
    }

    @Step("Отображается неактивная кнопка \"Продолжить\"")
    public void checkContinueButtonInLoginModalWindow() {
        Assert.assertTrue(homePage.checkContinueButtonInLoginModalWindow());
    }

    @Step("Нажимаем на элемент хедера \"Переменная\"")
    public void clickOnNavTabElement(String nameNavTabElement) {
        homePage.clickOnNavTabElement(nameNavTabElement);
    }

    @Step("Нажимаем на ссылку с текущим городом")
    public void clickCurrentCity() {
        homePage.clickCurrentCity();
    }

    @Step("Открыто модальное окно с заголовком “Выберите город”")
    public void checkModalWindowChoseCityHaveTitle() {
        homePage.checkModalWindowChoseCityHaveTitle();
    }

    @Step("Нажимаем на строку с городом \"Переменная\"")
    public void clickOnSelectedCity(String cityName) {
        homePage.clickOnSelectedCity(cityName);
    }

    @Step("Модальное окно с заголовком “Выберите город” не отображается")
    public void checkModalWindowNotVisible() {
        homePage.checkModalWindowNotVisible();
    }

    @Step("В хедере отображается ссылка с городом \"Переменная\"")
    public void checkCurrentNameCity(String cityName) {
        homePage.checkCurrentNameCity(cityName);
    }

}
