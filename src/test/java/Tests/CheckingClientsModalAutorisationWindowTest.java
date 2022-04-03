package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CheckingClientsModalAutorisationWindowTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void checkingClientsModalAutorisationWindow(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.clickOnNavTabElement("Личный кабинет");
        steps.checkLoginModalWindowDiplayed();
        steps.checkExitLoginModalWindowDisplayed();
        steps.checkTitleLoginModalWindowDisplayed();
        steps.checkInputNumberPhoneDisplayed();
        steps.checkContinueButtonInLoginModalWindow();
    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
