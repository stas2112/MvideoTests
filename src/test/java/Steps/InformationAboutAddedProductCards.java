package Steps;

import Pages.ProductInformationForComparison;

import java.util.ArrayList;
import java.util.List;

/**
 * Храним все данные о добавленных товарах, либо товарах в корзине в виде List<ProductInformationForComparison>
 */
public class InformationAboutAddedProductCards {
    private List<ProductInformationForComparison> allInformationProductCard = new ArrayList();

    public void addListProductCard(List<ProductInformationForComparison> listProductCard) {
        allInformationProductCard.addAll(listProductCard);
    }

    public void addOneProductCard(ProductInformationForComparison productCard) {
        allInformationProductCard.add(productCard);
    }

    public List<ProductInformationForComparison> getAllInformationProductCard() {
        return allInformationProductCard;
    }

    public void clearAllInformationAboutProductCard() {
        allInformationProductCard.clear();
    }


}
