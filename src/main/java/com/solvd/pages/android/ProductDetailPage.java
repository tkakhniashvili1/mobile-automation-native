package com.solvd.pages.android;

import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private ExtendedWebElement backToProductsButton;

    @AndroidFindBy(accessibility = "test-Price")
    private ExtendedWebElement productPrice;

    @AndroidFindBy(accessibility = "test-Description")
    private ExtendedWebElement productDescription;

    @AndroidFindBy(accessibility = "test-Image Container")
    private ExtendedWebElement productImage;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(backToProductsButton);
    }

    @Override
    public boolean isProductDetailsPageOpened() {
        return backToProductsButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    @Override
    public boolean isSelectedProductNameDisplayed(String expectedProductName) {
        return !getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + expectedProductName + "\"]")
        ).isEmpty();
    }

    @Override
    public boolean isProductImagePriceAndDescriptionDisplayed() {
        return productImage.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productDescription.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }
}