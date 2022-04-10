package Steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

public class CommonSteps {
    private static int tryCount = 0;

    public static void compareLinkIncludes(String linkName) {
        Assert.assertTrue(WebDriverRunner.url().contains(linkName));
    }

    /**
     * От очень странной ошибки, которая не даёт запустится драйверу, чтобы из-за неё тесты не падали
     * Вместо стандартного Selenide.open запускаться через этот метод
     */
    public static void selenideOpen(String link) {
        try {
            Selenide.open(link);
        } catch (Exception e) {
            WebDriverRunner.closeWebDriver();
            tryCount++;
            if (tryCount < 5) selenideOpen(link);
        }
    }
    /**
     * String парсим в Int, убирая при этом всё, кроме чисел (использовать везде, где нужно достать только число из DOM)
     */
    public static int stringToInt(String string){
        return Integer.parseInt(string.replaceAll("\\D", ""));
    }

    /**
     * Для использования данных, содержащихся в классе InformationAboutAddedProductsCard создаём статичную переменную этого
     * класса, чтобы получать данные из других классов, после каждого теста очищаем все данные.
     */
    public static void deleteInformationAboutAllInformationAboutProduct(){
        FavouritesPageSteps.deleteInformationAboutAddedProductCards();
        HomePageSteps.deleteInformationAboutAddedProductCards();
        ListingPageSteps.deleteInformationAboutAddedProductCards();
        ComparisonPageSteps.deleteInformationAboutAddedProductCards();
    }
}
