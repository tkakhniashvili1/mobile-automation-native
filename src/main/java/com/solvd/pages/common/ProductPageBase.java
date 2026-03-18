package com.solvd.pages.common;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.ProductListItemComponentBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class ProductPageBase extends AbstractPage {

    protected HeaderComponentBase header;

    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract List<ProductListItemComponentBase> getProductItems();

    public ProductListItemComponentBase getFirstProductItem() {
        return getProductItems().get(0);
    }

    public boolean areFirstProductCardsContentDisplayed(int count) {
        List<ProductListItemComponentBase> items = getProductItems();

        if (items.size() < count) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            ProductListItemComponentBase item = items.get(i);
            if (!item.isProductCardOpened()
                    || !item.isProductTitleDisplayed()
                    || !item.isProductPriceDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public boolean isCartBadgeCountDisplayed(int expectedCount) {
        return header.isCartBadgeCountDisplayed(expectedCount);
    }

    public CartPageBase openCart() {
        return header.openCart();
    }

    public LoginPageBase logoutFromApplication() {
        return header.openMenu().logout();
    }
}