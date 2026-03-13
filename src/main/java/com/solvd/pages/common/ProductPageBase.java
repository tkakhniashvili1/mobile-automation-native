package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {

    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductCardDisplayed();

    public abstract boolean areFirstProductCardsContentDisplayed(int count);

    public abstract String getFirstProductTitleText();

    public abstract ProductDetailPageBase openFirstProductDetails();

    public abstract void addFirstProductToCart();

    public abstract boolean isFirstProductButtonUpdatedToRemoveState();

    public abstract boolean isCartBadgeCountDisplayed(int expectedCount);

    public abstract String getFirstProductPriceText();

    public abstract CartPageBase openCart();

    public abstract LoginPageBase logoutFromApplication();
}