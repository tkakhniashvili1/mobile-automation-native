package com.solvd.pages.android;

import com.solvd.components.android.CartItemComponent;
import com.solvd.components.common.CartItemComponentBase;
import com.solvd.pages.common.CartPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @AndroidFindBy(accessibility = "test-CONTINUE SHOPPING")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(xpath = "//*[@content-desc='test-Item']")
    private List<CartItemComponent> cartItems;

    @AndroidFindBy(accessibility = "test-Cart badge")
    private ExtendedWebElement cartBadge;

    public CartPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(continueShoppingButton);
    }

    @Override
    public boolean isPageOpened() {
        return continueShoppingButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public List<CartItemComponentBase> getCartItems() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TimeoutConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='test-Item']")));

        return new ArrayList<>(cartItems);
    }

    @Override
    public CartItemComponentBase getCartItem(int index) {
        return cartItems.get(index);
    }

    @Override
    public CartItemComponentBase getCartItemByTitle(String productTitle) {
        return getCartItems().stream()
                .filter(item -> item.getProductTitle().equals(productTitle))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isAddedProductDisplayedInCart(String expectedProductName) {
        return getCartItemByTitle(expectedProductName) != null;
    }

    @Override
    public boolean isCartBadgeHidden() {
        return !cartBadge.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public boolean isRemovedItemNotDisplayed(String removedProductName) {
        return getCartItemByTitle(removedProductName) == null;
    }
}