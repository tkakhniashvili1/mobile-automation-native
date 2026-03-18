package com.solvd.components.common;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class CartItemComponentBase extends AbstractUIObject {

    public CartItemComponentBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract boolean isCartItemDisplayed();

    public abstract String getProductTitle();

    public abstract String getProductPrice();

    public abstract void removeProduct();
}