package com.solvd.components.android;

import com.solvd.components.common.CartItemComponentBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartItemComponentBase.class)
public class CartItemComponent extends CartItemComponentBase {

    @AndroidFindBy(uiAutomator =
            "new UiSelector().description(\"test-Description\")" +
                    ".childSelector(new UiSelector().classNameMatches(\".*Text.*\"))")
    private ExtendedWebElement productTitle;

    @AndroidFindBy(uiAutomator =
            "new UiSelector().description(\"test-Price\")" +
                    ".childSelector(new UiSelector().classNameMatches(\".*Text.*\"))")
    private ExtendedWebElement productPrice;

    @AndroidFindBy(accessibility = "test-REMOVE")
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