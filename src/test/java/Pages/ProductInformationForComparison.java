package Pages;


import Steps.CommonSteps;
import Steps.HomePageSteps;

/**
 * Описание значения товара, которые подлежат сравнению (цена, наименование и т.д.), а также название класса, откуда был
 * добавлен товар, по надобности класс можно расширять.
 */
public class ProductInformationForComparison {
    private String productClass;
    private String productName;
    private String productPrice;

    public ProductInformationForComparison(String nameProductClass, String nameProduct, String priceProduct) {
        productClass = nameProductClass;
        productName = nameProduct;
        productPrice = priceProduct;
    }

    public String getProductClass() {
        return productClass;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return CommonSteps.stringToInt(productPrice);
    }
}
