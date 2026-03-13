package com.solvd.pages.common;

import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartPageBase extends AbstractPage {

    @FindBy(xpath = "//*[@content-desc='test-CONTINUE SHOPPING']")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(xpath = "(//*[@content-desc='test-Description']/android.widget.TextView)[1]")
    private ExtendedWebElement firstCartItemTitle;

    @FindBy(xpath = "(//*[@content-desc='test-Price']//android.widget.TextView)[1]")
    private ExtendedWebElement firstCartItemPrice;

    @FindBy(xpath = "(//*[@content-desc='test-REMOVE'])[1]")
    private ExtendedWebElement firstRemoveButton;

    @FindBy(xpath = "//*[@content-desc='test-Cart badge']")
    private ExtendedWebElement cartBadge;

    public CartPageBase(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(continueShoppingButton);
    }

    public boolean isCartPageDisplayed() {
        return continueShoppingButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    public boolean isAddedProductDisplayedInCart(String expectedProductName) {
        return !getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + expectedProductName + "\"]")
        ).isEmpty();
    }

    public String getFirstCartItemTitleText() {
        firstCartItemTitle.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstCartItemTitle.getText().trim().replaceAll("\\s+", " ");
    }

    public String getFirstCartItemPriceText() {
        firstCartItemPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstCartItemPrice.getText().trim();
    }

    public void removeFirstProductFromCart() {
        firstRemoveButton.click();
    }

    public boolean isCartBadgeNotDisplayed() {
        return !cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    public boolean isRemovedItemNotDisplayed(String removedProductName) {
        return getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + removedProductName + "\"]")
        ).isEmpty();
    }
}