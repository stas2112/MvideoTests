package Tests;

import Steps.Steps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CheckingHeaderMvideoHomePageTest {
    private String baseUrl = "https://www.mvideo.ru";
    @Test
    public void checkingHeaderMvideoHomePage(){
        Selenide.open(baseUrl);
        Steps steps = new Steps();
        steps.checkNavTabElementIsDisplayed("Статус заказа");
        steps.checkNavTabElementIsNotDisabled("Статус заказа");
        //У кнопки "Войти" title- "Личный кабинет"
        steps.checkNavTabElementIsDisplayed("Личный кабинет");
        steps.checkNavTabElementIsNotDisabled("Личный кабинет");
        steps.checkNavTabElementIsDisplayed("Сравнение");
        steps.checkNavTabElementIsDisabled("Сравнение");
        steps.checkNavTabElementIsDisplayed("Избранное");
        steps.checkNavTabElementIsDisabled("Избранное");
        steps.checkNavTabElementIsDisplayed("Корзина");
        steps.checkNavTabElementIsDisabled("Корзина");
    }
    @AfterClass
    public void aClass(){
        Steps steps= new Steps();
        steps.clearAllData();
        WebDriverRunner.closeWebDriver();
    }
}
