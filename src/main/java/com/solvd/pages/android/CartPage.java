package com.solvd.pages.android;

import com.solvd.pages.common.CartPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

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

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(continueShoppingButton);
    }

    @Override
    public boolean isCartPageDisplayed() {
        return continueShoppingButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public boolean isAddedProductDisplayedInCart(String expectedProductName) {
        return !getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + expectedProductName + "\"]")
        ).isEmpty();
    }

    @Override
    public String getFirstCartItemTitleText() {
        firstCartItemTitle.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstCartItemTitle.getText().trim().replaceAll("\\s+", " ");
    }

    @Override
    public String getFirstCartItemPriceText() {
        firstCartItemPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
        return firstCartItemPrice.getText().trim();
    }

    @Override
    public void removeFirstProductFromCart() {
        firstRemoveButton.click();
    }

    @Override
    public boolean isCartBadgeNotDisplayed() {
        return !cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public boolean isRemovedItemNotDisplayed(String removedProductName) {
        return getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + removedProductName + "\"]")
        ).isEmpty();
    }
}