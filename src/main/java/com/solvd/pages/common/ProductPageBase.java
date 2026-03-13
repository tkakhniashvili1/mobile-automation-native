package com.solvd.pages.common;

import com.solvd.components.common.HeaderComponentBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {

    protected HeaderComponentBase header;

    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductCardDisplayed();

    public abstract boolean areFirstProductCardsContentDisplayed(int count);

    public abstract String getFirstProductTitleText();

    public abstract ProductDetailPageBase openFirstProductDetails();

    public abstract void addFirstProductToCart();

    public abstract boolean isFirstProductButtonUpdatedToRemoveState();

    public abstract String getFirstProductPriceText();

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