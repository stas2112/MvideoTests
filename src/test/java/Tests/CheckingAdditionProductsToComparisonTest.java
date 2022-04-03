package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CheckingAdditionProductsToComparisonTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void checkingAdditionProductsToComparison() {
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkSearchLineDisplayed();
        steps.setSearchLineValue("apple");
        steps.clickSearchButton();
        Steps.verificationLinkIncludes("/product-list-page");
        steps.addCertainAmountInComparison(3);
        steps.clickOnNavTabElement("Сравнение");
        Steps.verificationLinkIncludes("/product-comparison");
        steps.checkTitleComparisonProductsDisplayed();
        steps.checkNameAddedInComparison();
    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
