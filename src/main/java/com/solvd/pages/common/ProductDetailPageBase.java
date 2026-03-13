package com.solvd.pages.common;

import com.solvd.utils.TimeoutConstants;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPageBase extends AbstractPage {

    @FindBy(xpath = "//*[@content-desc='test-BACK TO PRODUCTS']")
    private ExtendedWebElement backToProductsButton;

    @FindBy(xpath = "//*[@content-desc='test-Price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//*[@content-desc='test-Description']")
    private ExtendedWebElement productDescription;

    @FindBy(xpath = "//*[@content-desc='test-Image Container']")
    private ExtendedWebElement productImage;

    public ProductDetailPageBase(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(backToProductsButton);
    }

    public boolean isProductDetailsPageDisplayed() {
        return backToProductsButton.isElementPresent(TimeoutConstants.LONG_TIMEOUT);
    }

    public boolean isSelectedProductNameDisplayed(String expectedProductName) {
        return !getDriver().findElements(
                By.xpath("//android.widget.TextView[@text=\"" + expectedProductName + "\"]")
        ).isEmpty();
    }

    public boolean isProductImagePriceAndDescriptionDisplayed() {
        return productImage.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productPrice.isElementPresent(TimeoutConstants.SHORT_TIMEOUT)
                && productDescription.isElementPresent(TimeoutConstants.SHORT_TIMEOUT);
    }
}