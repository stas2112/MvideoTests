package Tests;

import Steps.Steps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;

public class AddingTwoProductsToCartTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void addingTwoProductsToCart(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkBlockMostViewedDisplayed();
        steps.clickOnButtonAddToCartInMostView(2);
        steps.clickOnCartOnHomePage();
        steps.checkNameAddedInCart();
        steps.checkSumAddedProductsEquallySummProductsInCart();
    }
    @BeforeSuite
    public void bTest(){
        Configuration.pageLoadTimeout = 600000;
        Configuration.browserSize="1920x1080";}
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
