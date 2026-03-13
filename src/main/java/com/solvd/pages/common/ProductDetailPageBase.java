package com.solvd.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductDetailPageBase extends AbstractPage {

    public ProductDetailPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductDetailsPageOpened();

    public abstract boolean isSelectedProductNameDisplayed(String expectedProductName);

    public abstract boolean isProductImagePriceAndDescriptionDisplayed();
}