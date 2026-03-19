package com.solvd.pages.common;

import com.solvd.components.common.HeaderComponentBase;
import com.solvd.components.common.ProductListItemComponentBase;
import com.solvd.pages.android.LoginPage;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class ProductPageBase extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);

    protected HeaderComponentBase header;

    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract List<ProductListItemComponentBase> getProductItems();

    public ProductListItemComponentBase getFirstProductItem() {
        return getProductItems().get(0);
    }

    public boolean areFirstProductsValid(int count) {
        List<ProductListItemComponentBase> items = getProductItems();

        if (items.size() < count) {
            LOGGER.warn("Expected at least {} products, but found {}", count, items.size());
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (!items.get(i).isValidProductCard()) {
                LOGGER.warn("Product at index {} is not valid", i);
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