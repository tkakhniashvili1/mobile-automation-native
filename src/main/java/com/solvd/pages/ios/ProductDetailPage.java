package com.solvd.pages.ios;

import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {

    @iOSXCUITFindBy(accessibility = "test-BACK TO PRODUCTS")
    private ExtendedWebElement backToProductsButton;

    @iOSXCUITFindBy(accessibility = "test-Price")
    private ExtendedWebElement productPrice;

    @iOSXCUITFindBy(accessibility = "test-Description")
    private ExtendedWebElement productDescription;

    @iOSXCUITFindBy(accessibility = "test-Image Container")
    private ExtendedWebElement productImage;

    @FindBy(xpath = "//XCUIElementTypeStaticText")
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