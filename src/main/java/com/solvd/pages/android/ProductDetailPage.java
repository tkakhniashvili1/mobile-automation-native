package com.solvd.pages.android;

import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;

import java.util.List;

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

    @ExtendedFindBy(
            androidUIAutomator = "new UiSelector().className(\"android.widget.TextView\")"
    )
    private List<ExtendedWebElement> productTexts;

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
        return productTexts.stream()
                .anyMatch(element -> element.getText().trim().equals(expectedProductName));
    }

    @Override
    public boolean isProductImageDisplayed() {
        return productImage.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public boolean isProductPriceDisplayed() {
        return productPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }

    @Override
    public boolean isProductDescriptionDisplayed() {
        return productDescription.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }
}