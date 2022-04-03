package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CheckingAdditionOfProductToFavoritesListTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void checkingAdditionOfProductToFavoritesList(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.clickCurrentCity();
        steps.checkModalWindowChoseCityHaveTitle();
        steps.clickOnSelectedCity("Краснодар");
        steps.checkModalWindowNotDisplayed();
        steps.checkCurrentNameCity("Краснодар");

    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
