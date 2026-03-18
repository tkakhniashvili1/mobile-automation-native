package com.solvd.components.common;

import com.solvd.pages.common.CartPageBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HeaderComponentBase extends AbstractPage {

    public HeaderComponentBase(WebDriver driver) {
        super(driver);
    }

    public abstract CartPageBase openCart();

    public abstract boolean isCartBadgeDisplayed();

    public abstract int getCartBadgeCount();

    public abstract boolean isCartBadgeCountDisplayed(int expectedCount);

    public abstract MenuComponentBase openMenu();
}