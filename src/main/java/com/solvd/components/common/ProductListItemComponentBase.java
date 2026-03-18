package com.solvd.components.common;

import com.solvd.pages.common.ProductDetailPageBase;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class ProductListItemComponentBase extends AbstractUIObject {

    public ProductListItemComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract boolean isProductCardOpened();

    public abstract boolean isProductTitleDisplayed();

    public abstract boolean isProductPriceDisplayed();

    public abstract boolean isProductImageDisplayed();

    public abstract String getProductTitleText();

    public abstract String getProductPriceText();

    public abstract ProductDetailPageBase openProductDetails();

    public abstract void addProductToCart();

    public abstract boolean isButtonUpdatedToRemoveState();
}