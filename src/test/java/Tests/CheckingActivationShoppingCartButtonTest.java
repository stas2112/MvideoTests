package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CheckingActivationShoppingCartButtonTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void checkingActivationShoppingCartButton(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkProductsOfDayIsDisplayed();
        steps.clickOnButtonAddToCartInProductsOfDay();
        steps.compareNumberOfProductsInCart(1);
        steps.checkNavTabElementIsNotDisabled("Корзина") ;
    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }

}
