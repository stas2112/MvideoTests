package Pages;

import com.codeborne.selenide.SelenideElement;

public class ProductCard {
    private SelenideElement elementOfCard;
    private SelenideElement nameElementOfCard;
    private SelenideElement priceElementOfCard;
    private SelenideElement countElementOfCard;
    private String nameElementOfCardString;
    private String priceElementOfCardString;
    private String countElementOfCardString;


    /**
     * Формируем одинаковые по запрашиваемым из них значениям карточки с разных блоков сайта.
     * @param selenideElement
     * @param i Указывать только доступные параметры, описанные ниже 
     * i= 1 - Формируется Product card для элемента из "корзины"
     * i= 2 - Формируется Product card для элемента из "Товары дня"
     * i= 3 - Формируется Product card для элемента из "Самые просматриваемые"
     * i=4 - Формируется Product card для элемента из "product-list-page"
     * i=5 - Формируется Product Card для элемента из "Сравнение"
     * i=6 - Формируется Product Card для элемента из "Избранное"
     */
    public ProductCard(SelenideElement selenideElement, int i){
        if(i==1){
            elementOfCard = selenideElement;
            nameElementOfCard = elementOfCard.$x(".//div[contains(@class, 'c-cart-item__header')]/a[contains(@class, 'c-link')]");
            priceElementOfCard = elementOfCard.$x(".//span[contains(@class, 'c-cart-item__price')]");
            countElementOfCard = elementOfCard.$x(".//input[contains(@class, 'c-counter-input__input')]");
            priceElementOfCardString = priceElementOfCard.getText();
            nameElementOfCardString = nameElementOfCard.getText();
            countElementOfCardString = countElementOfCard.getValue();
        }
         if (i==2){
            elementOfCard = selenideElement;
            nameElementOfCard = elementOfCard.$x(".//div[contains(@class, 'title')]/a");
            priceElementOfCard = elementOfCard.$x(".//span[contains(@class, 'price__main-value')]");
            priceElementOfCardString = priceElementOfCard.getText();
            nameElementOfCardString = nameElementOfCard.getText();
        }
         if (i==3){
             elementOfCard= selenideElement;
             nameElementOfCard= elementOfCard;
             priceElementOfCard= selenideElement.$x("./ancestor::div[contains(@class, 'product-mini-card__name ')]" +
                     "/following-sibling::div/following-sibling::div//span[contains (@class, 'price__main-value')]");
             priceElementOfCardString = priceElementOfCard.getText();
             nameElementOfCardString = nameElementOfCard.getText();
         }
         if (i==4){
             elementOfCard = selenideElement;
             nameElementOfCard = elementOfCard;

             if(nameElementOfCard.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]" +
                     "/following-sibling::div/following-sibling::div//span[contains(@class,'price__main-value')]").isDisplayed()){
                 priceElementOfCard = selenideElement.$x("./ancestor::div[contains(@class,'product-card__title-line-container')]" +
                         "/following-sibling::div/following-sibling::div//span[contains(@class,'price__main-value')]");
                 priceElementOfCardString = priceElementOfCard.getText();
                 nameElementOfCardString = nameElementOfCard.getText();
             }
             else{
                 priceElementOfCardString= "null";
                 nameElementOfCardString = nameElementOfCard.getText();
             }
             if(i==5){
                 elementOfCard = selenideElement;
                 nameElementOfCard= elementOfCard;
                 priceElementOfCard = nameElementOfCard.$x("./ancestor::div[contains(@class, " +
                         "'fl-product-tile__description' )]/following-sibling::div/following-sibling::div" +
                         "//span[contains(@class,'fl-product-tile-price__current')]");
                 priceElementOfCardString = priceElementOfCard.getText();
                 nameElementOfCardString = nameElementOfCard.getText();
             }
             if(i==6){
                 elementOfCard = selenideElement;
                 nameElementOfCard= elementOfCard;
                 priceElementOfCard= nameElementOfCard.$x("./ancestor::div[contains(@class,'wishlist-item__main-content')" +
                         "]/following-sibling::div//div[contains(@itemprop,'price')]");
                 priceElementOfCardString = priceElementOfCard.getText();
                 nameElementOfCardString = nameElementOfCard.getText();
             }

         }
    }
    public String getNameElementOfCard(){
        return nameElementOfCardString;
    }
    public String getPriceElementOfCard(){
        return priceElementOfCardString;
    }
    public String getCountElementOfCard(){
        return countElementOfCardString;
    }
}
