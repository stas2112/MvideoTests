package Tests;

import Steps.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CasesForImplementationTests {
    private String baseURL = "https://www.mvideo.ru";

    @Test
    public void checkingHeaderMvideoHomePage() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        homePageSteps.checkNavTabElementIsDisplayed("Статус заказа");
        homePageSteps.checkNavTabElementIsNotDisabled("Статус заказа");
        // У кнопки "Войти" title- "Личный кабинет"
        homePageSteps.checkNavTabElementIsDisplayed("Личный кабинет");
        homePageSteps.checkNavTabElementIsNotDisabled("Личный кабинет");
        homePageSteps.checkNavTabElementIsDisplayed("Сравнение");
        homePageSteps.checkNavTabElementIsDisabled("Сравнение");
        homePageSteps.checkNavTabElementIsDisplayed("Избранное");
        homePageSteps.checkNavTabElementIsDisabled("Избранное");
        homePageSteps.checkNavTabElementIsDisplayed("Корзина");
        homePageSteps.checkNavTabElementIsDisabled("Корзина");
    }

    @Test
    public void checkingActivationShoppingCartButton() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        homePageSteps.checkProductOfDayContainerIsVisible();
        homePageSteps.addProductOfDayToCart();
        homePageSteps.compareNumberAddedProductsEquallyProductsInCart("1");
        homePageSteps.checkNavTabElementIsNotDisabled("Корзина");
    }

    @Test
    public void goToShoppingCartTest() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        CartPageSteps cartPageSteps = new CartPageSteps();
        homePageSteps.checkProductOfDayContainerIsVisible();
        homePageSteps.addProductOfDayToCart();
        homePageSteps.clickOnCart();
        CommonSteps.compareLinkIncludes("/cart");
        cartPageSteps.compareTitleIsMyCartInCart();
        cartPageSteps.checkAllProductCardAddedInCart();
        cartPageSteps.checkButtonGoToDesignIsVisibleInCart();
        cartPageSteps.checkProductCountLineInGoToDesignBlockInCart("В корзине 1 товар");
        cartPageSteps.compareSummProductInCartEquallyCalculatedFromGoToDesignBlock();

    }

    @Test
    public void addingTwoProductsToCartTest() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        CartPageSteps cartPageSteps = new CartPageSteps();
        homePageSteps.checkProductOfDayContainerIsVisible();
        homePageSteps.addProductsFromMostViewedInCart(2);
        homePageSteps.clickOnCart();
        cartPageSteps.checkAllProductCardAddedInCart();
        cartPageSteps.compareSummProductInCartEquallyCalculatedFromGoToDesignBlock();
    }

    @Test
    public void productSearchTest() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        ListingPageSteps listingPageSteps = new ListingPageSteps();
        homePageSteps.checkSearchLineIsDisplayed();
        homePageSteps.setValueInSearchLine("apple");
        homePageSteps.clickSearchButton();
        CommonSteps.compareLinkIncludes("/product-list-page");
        listingPageSteps.saveAllProductCardsFromListingPage(24);
        listingPageSteps.compareAllProductsHaveWordInName("apple");
    }

    @Test
    public void SortingProductsInListingTest() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        ListingPageSteps listingPageSteps = new ListingPageSteps();
        homePageSteps.checkSearchLineIsDisplayed();
        homePageSteps.setValueInSearchLine("apple");
        homePageSteps.clickSearchButton();
        CommonSteps.compareLinkIncludes("/product-list-page");
        listingPageSteps.selectOptionInDropDown("Сначала дороже");
        listingPageSteps.saveAllProductCardsFromListingPage(24);
        listingPageSteps.compareAllProductsHaveWordInName("apple");
        listingPageSteps.comparePriceOfProductDescendingOrder();

    }

    @Test
    public void checkingClientsModelAutorisationWindowTest() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        homePageSteps.clickOnNavTabElement("Личный кабинет");
        homePageSteps.checkLoginModalWindowDisplayed();
        homePageSteps.checkExitLoginModalWindowDisplayed();
        homePageSteps.checkTitleLoginModalWindowDisplayed();
        homePageSteps.checkInputNumberPhoneDisplayed();
        homePageSteps.checkForLegalEntitiesDisplayed();
        homePageSteps.checkContinueButtonInLoginModalWindow();
    }

    @Test
    public void checkingAdditionProductsToFavourites() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        ListingPageSteps listingPageSteps = new ListingPageSteps();
        FavouritesPageSteps favouritesProductSteps = new FavouritesPageSteps();
        homePageSteps.checkSearchLineIsDisplayed();
        homePageSteps.setValueInSearchLine("apple");
        homePageSteps.clickSearchButton();
        CommonSteps.compareLinkIncludes("/product-list-page");
        listingPageSteps.addCertainAmountInFavourites(3);
        homePageSteps.clickOnNavTabElement("Избранное");
        CommonSteps.compareLinkIncludes("/wish-list");
        favouritesProductSteps.checkTitleFavouritesProductsDisplayed();
        favouritesProductSteps.allAddedProductCardExist();
    }

    @Test
    public void checkingAdditionProductsToComparison() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        ListingPageSteps listingPageSteps = new ListingPageSteps();
        ComparisonPageSteps comparisonPageSteps = new ComparisonPageSteps();
        homePageSteps.checkSearchLineIsDisplayed();
        homePageSteps.setValueInSearchLine("apple");
        homePageSteps.clickSearchButton();
        CommonSteps.compareLinkIncludes("/product-list-page");
        listingPageSteps.addCertainAmountInComparison(3);
        homePageSteps.clickOnNavTabElement("Сравнение");
        CommonSteps.compareLinkIncludes("/product-comparison");
        comparisonPageSteps.checkTitleComparisonProductsDisplayed();
        comparisonPageSteps.allAddedProductCardExist();
    }

    @Test
    public void checkingCityChange() {
        CommonSteps.selenideOpen(baseURL);
        HomePageSteps homePageSteps = new HomePageSteps();
        homePageSteps.clickCurrentCity();
        homePageSteps.checkModalWindowChoseCityHaveTitle();
        homePageSteps.clickOnSelectedCity("Краснодар");
        homePageSteps.checkModalWindowNotVisible();
        homePageSteps.checkCurrentNameCity("Краснодар");
    }

    @BeforeSuite
    public void bTest() {
        Configuration.pageLoadTimeout = 600000;
        Configuration.browserSize = "1920x1080";
    }

    @AfterMethod
    public void aClass() {
        CommonSteps.deleteInformationAboutAllInformationAboutProduct();
        WebDriverRunner.closeWebDriver();
    }

}
