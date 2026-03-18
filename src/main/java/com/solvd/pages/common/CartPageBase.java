package com.solvd.pages.common;

import com.solvd.components.common.CartItemComponentBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPageOpened();

    public abstract List<CartItemComponentBase> getCartItems();

    public abstract CartItemComponentBase getCartItem(int index);

    public abstract CartItemComponentBase getCartItemByTitle(String productTitle);

    public abstract boolean isAddedProductDisplayedInCart(String expectedProductName);

    public abstract boolean isCartBadgeNotDisplayed();

    public abstract boolean isRemovedItemNotDisplayed(String removedProductName);
}