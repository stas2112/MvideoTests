package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage {
    @FindBy(xpath = "//div[contains(@class, 'footer__link-group ng-star-inserted')]")
    private static SelenideElement footerLinkOfficialInformation;

    public static void searchElementInPage(SelenideElement selenideElement) {
        while (!selenideElement.isDisplayed()) {
            if (!selenideElement.isDisplayed()) Selenide.executeJavaScript("window.scrollBy(0, 230);");
        }
    }

}

