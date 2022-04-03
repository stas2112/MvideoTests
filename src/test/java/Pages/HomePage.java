package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.IsDisplayed;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class HomePage {
    private List<ProductCard> productCardInCart = new ArrayList<>();;
    private static HomePage homePage;
    private String xpathNavTabElement = "//div[contains(@class,  'nav-tab' )]//a[contains(@title, '%s')]";
    private String xpathMvidHeaderIcon = "//div[contains(@class,  'nav-tab' )]//a[contains(@title, '%s')]" +
            "/parent::div/parent::mvid-header-icon";
    //Xpath на основной контейнер блока "товары дня"
    @FindBy(xpath = "//div[@class ='product-container']")
    private SelenideElement productsOfDay;
    @FindBy (xpath = "//mvid-day-product[contains(@class,'main')]")
    private SelenideElement productsOfDayForCompileProductCard;
    private String xpathCartContainer = "//div[contains(@class, 'tab-cart')]%s";
    @FindBy (xpath = "//div[contains(@class, 'footer__link-group ng-star-inserted')]")
    private static SelenideElement footerLinkOfficialInformation;
    @FindBy (xpath = "//h2[contains(text(),'Самые просматриваемые')]/following-sibling::mvid-carousel//div[contains(@class,'title')]//div")
    private ElementsCollection nameProductWithMostViewedBlock;
    @FindBy (xpath = "//h2[contains(text(),'Самые просматриваемые')]")
    private SelenideElement titleMostViewedBlock;
    @FindBy (xpath = "//input")
    private SelenideElement searchLine;
    @FindBy (xpath = "//mvid-icon[contains(@type,'search')]")
    private SelenideElement searchButton;
    @FindBy (xpath = "//div[contains(@class,'modal-layout__content')]")
    private SelenideElement loginModalWindow;
    @FindBy(xpath = "//mvid-icon[contains(@type,'geo')and contains(@class,'location-icon_animate')]/parent::div")
    private SelenideElement buttonCurrentCity;
    @FindBy (xpath = "//div[contains(@class,'modal-layout')]")
    private SelenideElement modalWindowChoseCity;
    @FindBy(xpath = "//mvid-icon[contains(@type,'geo')]/following-sibling::span")
    private SelenideElement currentNameCity;


    /**
     * @param nameElement Название элемента из шапки, который проверяем на "отображение"
     */
    public boolean checkNavTabElementIsDisplayed(String nameElement){
        if(!checkExistElement(Selenide.$x(String.format(xpathMvidHeaderIcon, nameElement)))) {
            System.out.println("Элемент не существует, Либо неправильно введено название|" +
                    " название вводится с учётом регистра");
            return false;
        }
        return Selenide.$x(String.format(xpathNavTabElement, nameElement)).isDisplayed();
    }

    /**
     * @param nameElement Название элемента из шапки, который проверяем на "активность"
     */
    public boolean checkNavTabElementIsDisabled(String nameElement){
        if(!checkExistElement(Selenide.$x(String.format(xpathMvidHeaderIcon, nameElement)))) {
            System.out.println("Элемент не существует, Либо неправильно введено название|" +
                    " название вводится с учётом регистра");
            return false;
        }
            return Selenide.$x(String.format(xpathMvidHeaderIcon, nameElement)).getAttribute("class")
                    .contains("disabled");

    }
    /**
     * Проверяем существует ли элемент
     */
    public boolean checkExistElement(SelenideElement element){
        return element.exists();
    }

    public boolean checkProductsOfDayDisplayed(){
        return productsOfDay.shouldBe(Condition.visible).isDisplayed();
    }

    public void clickOnCartOnHomePage(){
        Selenide.$x(String.format(xpathCartContainer,"//a")).should(Condition.visible).click();
    }
    public void clickOnButtonAddToCart(){
        addInListProductCardFromProductsDay();
         SelenideElement buttonAddToCart =   Selenide.$x("//mvid-day-product[contains(@class, 'main')]//button");
         buttonAddToCart.shouldBe(Condition.visible).click();

    }

    /**
     * @param countProductsInCart предполагаемое количество товара в корзине
     * Сверяем отображаемое количество товаров в корзине, с предполагаемым
     */
    public boolean compareNumberOfProductsInTheCart(int countProductsInCart){
        return Selenide.$x(String.format(xpathCartContainer,"//mvid-bubble")).should(Condition.visible).
                shouldBe(Condition.text("1")).getText().equals(Integer.toString(countProductsInCart));
    }

    /**
     * @return передаём наш массив со всеми карточками товара
     */
    public List<ProductCard> getListProductCardFromProductsDay(){
        return productCardInCart;
    }

    /**
     * При клике На элемент "В корзину" в блоке "Товары дня" создаём для этого товара Его карточку товара и сохраняем
     */
    private void addInListProductCardFromProductsDay(){
        productCardInCart.add(new ProductCard(productsOfDayForCompileProductCard, 2));
    }

    public static HomePage getHomePage(){
        if (Objects.isNull(homePage)){
           homePage = Selenide.page(new HomePage());
        }
        return homePage;
    }

    /**
     * Листает до футера, Если не работает, то нужно поменять xpath прикреплённого элемента(футера)
     * @param selenideElement Искомый элемент
     */
    public static boolean searchElementInPage(SelenideElement selenideElement){
        while (!footerLinkOfficialInformation.isDisplayed() && !selenideElement.isDisplayed()){
            if(!selenideElement.isDisplayed()) {
                Selenide.executeJavaScript("window.scrollBy(0, 230);");
            }
        }
        if(selenideElement.isDisplayed()) return true;
        return false;
    }

    /**
     * Добавляем элементы, которые добавили в корзину(из "Самые просматриваемые"), в List ProductCard
     * @param countOfAddedProduct
     */
    public void addInListProductCardFromMostViewed(int countOfAddedProduct){
        //Ищем блок "самые просматриваемые"
        searchElementInPage(titleMostViewedBlock);
        nameProductWithMostViewedBlock.stream().limit(countOfAddedProduct).peek(item->{
            productCardInCart.add(new ProductCard(item, 3));}).collect(Collectors.toList());
    }

    /**
     * @param countOfAddedProduct сколько элементов добавляем в корзину(по порядку)
     */
    public void clickOnButtonAddToCartInMostView(int countOfAddedProduct){
        //Ищем блок "самые просматриваемые"
        if(searchElementInPage(titleMostViewedBlock)){
            titleMostViewedBlock.scrollTo();
        nameProductWithMostViewedBlock.stream().limit(countOfAddedProduct).peek(item->{
          SelenideElement selenideElement= item.$x("./ancestor::div[contains(@class, 'product-mini-card__name ')]/following-sibling::div" +
                   "/following-sibling::div/following-sibling::div/following-sibling::div/following-sibling::div" +
                   "//mvid-product-actions-tooltip[contains(@class,'tooltip-container__cart')]");
          selenideElement.shouldBe(Condition.visible).click();
          //Проверяем, что кнопка нажалась и поменялось на active, а то в корзине не добавится
          selenideElement.$x(".//button[contains(@class,'mv-main-button__active')]").should(Condition.exist);

        }).collect(Collectors.toList());
    }}
    public boolean checkBlockMostViewedDisplayed(){
        searchElementInPage(titleMostViewedBlock);
        return titleMostViewedBlock.isDisplayed();
    }
    public boolean checkSearchLineDisplayed(){
        return searchLine.isDisplayed();
    }
    public void setSearchLineValue(String value){
        searchLine.setValue(value);
    }
    public void clickSearchButton(){
        searchButton.click();
    }
    public void clickOnNavTabElement(String nameNavTabElement){
        Selenide.$x(String.format(xpathNavTabElement, nameNavTabElement)).shouldBe(Condition.visible).click();
    }
    public boolean checkLoginModalWindowDiplayed(){
       return loginModalWindow.isDisplayed();
    }
    public boolean checkForLegalEntitiesDisplayed(){
        return loginModalWindow.$x(".//button[contains(@class,'login-form__link')]").shouldBe(Condition.visible)
                .is(Condition.visible);
    }
    public boolean checkExitLoginModalWindowDisplayed(){
        return loginModalWindow.$x(".//mvid-icon[@type='close']").is(Condition.visible);
    }

    public boolean checkTitleLoginModalWindowDisplayed(){
        return loginModalWindow.$x(".//h2").is(Condition.text("Вход или регистрация"));
    }
    public boolean checkInputNumberPhoneDisplayed(){
        return loginModalWindow.$x(".//input[contains(@class,'form-field__input')]/following-sibling::div//span")
                .is(Condition.text("Телефон")) && loginModalWindow.$x(".//input[contains(@class,'form-field__input')]")
                .isDisplayed();
    }
    public boolean checkContinueButtonInLoginModalWindow(){
        return loginModalWindow.$x(".//button[contains(@disabled, 'true')and contains(@class,'login-form__button')]")
                .isDisplayed();
    }
    public void clearHomePageData(){
        productCardInCart.clear();
    }

    public void clickCurrentCity(){
        buttonCurrentCity.click();
    }
    public boolean checkModalWindowChoseCityHaveTitle(){
        return modalWindowChoseCity.isDisplayed()&& modalWindowChoseCity.$x("./h3").is(Condition.text("Выберите город"));
    }
    public boolean checkModalWindowNotDisplayed(){
        return modalWindowChoseCity.shouldBe(Condition.not(Condition.exist)).is(Condition.not(Condition.exist));
    }
    public void clickOnSelectedCity(String nameCity){
        modalWindowChoseCity.$x(".//div[contains(@class,'modal-layout')]//ul/li[contains(text(),'"+nameCity+"')]").click();
    }
    public boolean checkCurrentNameCity(String nameCity){
      return   currentNameCity.is(Condition.text(nameCity));
    }
}
