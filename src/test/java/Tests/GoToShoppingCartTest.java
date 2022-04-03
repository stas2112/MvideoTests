package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class GoToShoppingCartTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void testCaseThree(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkProductsOfDayIsDisplayed();
        steps.clickOnButtonAddToCartInProductsOfDay();
        steps.clickOnCartOnHomePage();
        Steps.verificationLinkIncludes("/cart");
        steps.checkTitleMyCartDisplayed();
        steps.checkTitleMyCartDisplayed();
        steps.checkButtonGoToDesignDisplayed();
        steps.checkInTheProductCartLine("В корзине 1 товар");
        steps.compareActualAndCostInTheDesignBlock();
    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}

