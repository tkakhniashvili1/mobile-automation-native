package com.solvd.pages.ios;

import com.solvd.pages.common.CartPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    @FindBy(xpath = "//*[@name='test-CONTINUE SHOPPING']")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(xpath = "(//*[@name='test-Item title'])[1]")
    private ExtendedWebElement firstCartItemTitle;

    @FindBy(xpath = "(//*[@name='test-Price'])[1]")
    private ExtendedWebElement firstCartItemPrice;

    @FindBy(xpath = "(//*[@name='test-REMOVE'])[1]")
    private ExtendedWebElement firstRemoveButton;

    @FindBy(xpath = "//*[@name='test-Cart badge']")
    private ExtendedWebElement cartBadge;

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(continueShoppingButton);
    }

    @Override
    public boolean isCartPageOpened() {
        return continueShoppingButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public boolean isAddedProductDisplayedInCart(String expectedProductName) {
        return isCartItemWithTitlePresent(expectedProductName);
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
        LOGGER.info("Removing first product from cart");
        firstRemoveButton.click();
        LOGGER.info("Remove button clicked for first cart item");
    }

    @Override
    public boolean isCartBadgeNotDisplayed() {
        return !cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public boolean isRemovedItemNotDisplayed(String removedProductName) {
        return !isCartItemWithTitlePresent(removedProductName);
    }

    private boolean isCartItemWithTitlePresent(String productName) {
        return !getDriver().findElements(
                By.xpath("//*[@name=\"" + productName + "\"]")
        ).isEmpty();
    }
}