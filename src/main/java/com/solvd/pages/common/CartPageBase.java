package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPage {

    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isCartPageOpened();

    public abstract boolean isAddedProductDisplayedInCart(String expectedProductName);

    public abstract String getFirstCartItemTitleText();

    public abstract String getFirstCartItemPriceText();

    public abstract void removeFirstProductFromCart();

    public abstract boolean isCartBadgeNotDisplayed();

    public abstract boolean isRemovedItemNotDisplayed(String removedProductName);
}