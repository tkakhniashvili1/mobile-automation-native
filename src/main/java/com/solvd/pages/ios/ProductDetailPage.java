package com.solvd.pages.ios;

import com.solvd.pages.common.ProductDetailPageBase;
import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductDetailPageBase.class)
public class ProductDetailPage extends ProductDetailPageBase {

    @FindBy(xpath = "//*[@name='test-BACK TO PRODUCTS']")
    private ExtendedWebElement backToProductsButton;

    @FindBy(xpath = "//*[@name='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//*[@name='test-Description']")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//*[@name='test-Image Container']")
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
                By.xpath("//*[@name=\"" + expectedProductName + "\"]")
        ).isEmpty();
    }

    @Override
    public boolean isProductImagePriceAndDescriptionDisplayed() {
        return productImage.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productDescription.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }
}