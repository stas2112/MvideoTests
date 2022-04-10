package Pages.HomePage;

import Pages.CommonPage;
import Pages.ProductInformationForComparison;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomePage {
    private static HomePage homePage;
    private final String XPATH_NAV_TAB_ELEMENT = "//div[contains(@class,  'nav-tab' )]//a[contains(@title, '%s')]";
    private final String XPATH_NAV_TAB_ELEMENT_ICON = "//div[contains(@class,  'nav-tab' )]//a[contains(@title, '%s')]/parent::div/parent::mvid-header-icon";
    @FindBy(xpath = "//div[@class ='product-container']")
    private SelenideElement productsOfDayContainer;
    @FindBy(xpath = "//mvid-day-product[contains(@class, 'main')]")
    private SelenideElement productCardFromProductsOfDay;
    @FindBy(xpath = "//div[contains(@class, 'tab-cart')]")
    private SelenideElement cartContainer;
    @FindBy(xpath = "//h2[contains(text(),'Самые просматриваемые')]")
    private SelenideElement titleMostViewedBlock;
    @FindBy(xpath = "//h2[contains(text(),'Самые просматриваемые')]/following-sibling::mvid-carousel//div[contains(@class,'title')]//div")
    private ElementsCollection nameProductWithMostViewedBlock;
    @FindBy(xpath = "//input")
    private SelenideElement searchLine;
    @FindBy(xpath = "//div[contains(@class,'search-icon-wrap')]")
    private SelenideElement searchButton;
    @FindBy(xpath = "//div[contains(@class,'modal-layout__content')]")
    private SelenideElement loginModalWindow;
    @FindBy(xpath = "//mvid-icon[contains(@type,'geo')and contains(@class,'location-icon_animate')]/parent::div")
    private SelenideElement buttonCurrentCity;
    @FindBy(xpath = "//div[contains(@class,'modal-layout')]")
    private SelenideElement modalWindowChoseCity;
    @FindBy(xpath = "//mvid-icon[contains(@type,'geo')]/following-sibling::span")
    private SelenideElement currentNameCity;


    public static HomePage getHomePage() {
        if (Objects.isNull(homePage)) homePage = Selenide.page(new HomePage());
        return homePage;
    }

    public boolean checkNavTabElementIsDisplayed(String nameElement) {
        return Selenide.$x(String.format(XPATH_NAV_TAB_ELEMENT, nameElement)).isDisplayed();
    }

    public boolean checkNavTabElementIsDisabled(String nameElement) {
        return Selenide.$x(String.format(XPATH_NAV_TAB_ELEMENT_ICON, nameElement)).getAttribute("class").contains("disabled");
    }

    public boolean checkProductOfDayContainerIsVisible() {
        return productsOfDayContainer.is(Condition.visible);
    }

    public boolean compareNumberAddedProductsEquallyProductsInCart(String countProductsInCart) {
        return cartContainer.$x(".//mvid-bubble").getText().equals(countProductsInCart);
    }

    public void clickOnCart() {
        cartContainer.$x(".//a[@class='link']").shouldBe(Condition.visible).click();
    }

    public ProductsOfDayBlock getProductsOfDay() {
        return new ProductsOfDayBlock(productCardFromProductsOfDay);
    }

    public boolean checkBlockMostViewedVisible() {
        CommonPage.searchElementInPage(titleMostViewedBlock);
        return titleMostViewedBlock.is(Condition.visible);
    }

    public void clickAddProductsInMostView(int countOfAddedProduct) {
        CommonPage.searchElementInPage(titleMostViewedBlock);
        titleMostViewedBlock.scrollTo();
        nameProductWithMostViewedBlock.stream().limit(countOfAddedProduct).forEach(item -> item.$x
                ("./ancestor::div[contains(@class, 'product-mini-card__name ')]/following::div"
                        + "//mvid-product-actions-tooltip[contains(@class,'tooltip-container__cart')]").shouldBe(Condition.visible).click());

    }

    public void checkAddToCartButtonInMostViewIsActive(int countOfAddedProduct) {
        nameProductWithMostViewedBlock.stream().limit(countOfAddedProduct).forEach(item -> item.$x("./ancestor::div[contains(@class, 'product-mini-card__name ')]"
                + "/following::div//button[contains(@class,'mv-main-button__active')]").shouldBe(Condition.visible));
    }

    public List<ProductInformationForComparison> getListProductInformation(int countOfAddedProduct) {
        return nameProductWithMostViewedBlock.stream().limit(countOfAddedProduct).map(item -> {
            return new ProductCardForProductsFromCarousel(item).getProductInformationForComparison();
        }).collect(Collectors.toList());
    }

    public boolean checkSearchLineIsDisplayed() {
        return searchLine.isDisplayed();
    }

    public void setSearchLineValue(String value) {
        searchLine.setValue(value);
    }

    public void clickSearchButton() {
        searchButton.shouldBe(Condition.visible).click();
    }

    public boolean checkLoginModalWindowIsDisplayed() {
        return loginModalWindow.isDisplayed();
    }

    public boolean checkForLegalEntitiesDisplayed() {
        return loginModalWindow.$x(".//button[contains(@class,'login-form__link')]").shouldBe(Condition.visible)
                .is(Condition.visible);
    }

    public boolean checkExitLoginModalWindowDisplayed() {
        return loginModalWindow.$x(".//mvid-icon[@type='close']").is(Condition.visible);
    }

    public boolean checkTitleLoginModalWindowDisplayed() {
        return loginModalWindow.$x(".//h2").is(Condition.text("Вход или регистрация"));
    }

    public boolean checkInputNumberPhoneDisplayed() {
        return loginModalWindow.$x(".//input[contains(@class,'form-field__input')]/following-sibling::div//span")
                .is(Condition.text("Телефон")) && loginModalWindow.$x(".//input[contains(@class,'form-field__input')]")
                .isDisplayed();
    }

    public boolean checkContinueButtonInLoginModalWindow() {
        return loginModalWindow.$x(".//button[contains(@disabled, 'true')and contains(@class,'login-form__button')]")
                .isDisplayed();
    }

    public void clickOnNavTabElement(String nameNavTabElement) {
        Selenide.$x(String.format(XPATH_NAV_TAB_ELEMENT, nameNavTabElement)).shouldBe(Condition.visible).click();
    }

    public void clickCurrentCity() {
        buttonCurrentCity.click();
    }

    public boolean checkModalWindowChoseCityHaveTitle() {
        return modalWindowChoseCity.isDisplayed() && modalWindowChoseCity.$x("./h3").is(Condition.text("Выберите город"));
    }

    public boolean checkModalWindowNotVisible() {
        return modalWindowChoseCity.is(Condition.not(Condition.visible));
    }

    public void clickOnSelectedCity(String nameCity) {
        modalWindowChoseCity.$x(".//div[contains(@class,'modal-layout')]//ul/li[contains(text(),'" + nameCity + "')]").click();
    }

    public boolean checkCurrentNameCity(String nameCity) {
        return currentNameCity.is(Condition.text(nameCity));
    }
}
