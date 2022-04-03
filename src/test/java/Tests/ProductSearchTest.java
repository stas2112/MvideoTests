package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProductSearchTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void productSearch(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkSearchLineDisplayed();
        steps.setSearchLineValue("apple");
        steps.clickSearchButton();
        Steps.verificationLinkIncludes("/product-list-page");
        steps.getAllProductCardsInListPage(24);
        steps.compareAllProductsHaveWordInName("apple");

    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
