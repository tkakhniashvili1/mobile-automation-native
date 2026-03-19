package com.solvd.components.ios;

import com.solvd.components.common.CartItemComponentBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = CartItemComponentBase.class)
public class CartItemComponent extends CartItemComponentBase {

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'test-Description'")
    private ExtendedWebElement productTitle;

    @iOSXCUITFindBy(iOSNsPredicate = "name == 'test-Price'")
    private ExtendedWebElement productPrice;

    @iOSXCUITFindBy(accessibility = "test-REMOVE")
    private ExtendedWebElement removeButton;

    public CartItemComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public boolean isCartItemDisplayed() {
        return isUIObjectPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public String getProductTitle() {
        return productTitle.getText().trim().replaceAll("\\s+", " ");
    }

    @Override
    public String getProductPrice() {
        return productPrice.getText().trim();
    }

    @Override
    public void removeProduct() {
        removeButton.click();
    }
}